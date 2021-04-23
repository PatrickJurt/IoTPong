
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.nio.charset.StandardCharsets;

public class CustomMqttCallback
        implements MqttCallback
{
    @Override
    public void connectionLost(Throwable throwable) {

    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) {
        String s1 = "%s => %s".formatted(s, new String(mqttMessage.getPayload(), StandardCharsets.UTF_8));
        Main.g.parseAndApplyMovement(s, new String(mqttMessage.getPayload(), StandardCharsets.UTF_8));

    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}










