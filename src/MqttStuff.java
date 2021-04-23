import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttStuff extends Thread{

    public static void log(String s) {
        System.out.println(s);
    }

    public static void log(Exception e) {
        e.printStackTrace();
    }

    public MqttClient mqttClient = null;

    public void run() {

        int qos             = 2;
        String broker       = "tcp://cloud.tbz.ch:1883";
        String clientId     = "Itsa mee!!! Marioo :)";
        String username     = null;
        String password     = null;
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            mqttClient = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            if(username != null) {
                connOpts.setUserName(username);;
                connOpts.setPassword(password.toCharArray());;
                connOpts.setConnectionTimeout(4);
                connOpts.setAutomaticReconnect(true);
            }
            connOpts.setCleanSession(true);
            log("Connecting to broker: "+broker);
            mqttClient.connect(connOpts);
            log("Connected");
            mqttClient.subscribe("#");
            mqttClient.setCallback(new CustomMqttCallback());

            Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {
                    try {
                        mqttClient.disconnect();
                    } catch (MqttException e) {
                        log(e);
                    }
                    System.out.println("Disconnected");
                }
            });

        } catch(MqttException me) {
            System.out.println("reason "+me.getReasonCode());
            System.out.println("msg "+me.getMessage());
            System.out.println("loc "+me.getLocalizedMessage());
            System.out.println("cause "+me.getCause());
            System.out.println("excep "+me);
            me.printStackTrace();
        }
    }
}
