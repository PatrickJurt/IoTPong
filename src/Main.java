public class Main {

    static Game g;

    public static void main(String[] args){

        g = new Game();
        g.start();

        MqttStuff mqttStuff = new MqttStuff();
        mqttStuff.start();


        while(true){
            boolean connectionLost = false;
            boolean gameClosed = false;
            try {
                while(true) {
                    if(!mqttStuff.mqttClient.isConnected() && !connectionLost) {
                        mqttStuff.log("Connection lost");
                        connectionLost = true;
                    }else if(mqttStuff.mqttClient.isConnected() && connectionLost) {
                        connectionLost = false;
                    }

                    if(!g.getWindow().isOpen() && !gameClosed){
                        mqttStuff.log("Game closed");
                        gameClosed = true;
                    }else if(g.getWindow().isOpen() && gameClosed){
                        gameClosed = false;
                    }
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                mqttStuff.log(e);
            }
        }

    }
}
