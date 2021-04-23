
public class Game extends Thread{

    public static Window w;
    Ball b;
    Bar[] bars = new Bar[2];
    int[] input = new int[]{1, 1};
    int[] scores = new int[]{0,0};

    boolean gameOver = false;


    public void run(){

        //Create Window
        w = new Window("Pong", 1000, 1000);

        createAssets();

        //Start window and program
        w.open();

        while(w.isOpen()){

            moveElements();
            drawElements();

            if (gameOver){
                restart();
            }

            w.refreshAndClear(15);
        }
    }

    //Return keys pressed.
    //0 is down
    //1 is nothing
    //2 is up
    public void parseAndApplyMovement(String s1, String s2){

        //0 is left movement
        //2 is right movement

        //Player 1
        if (s1.contains("noob")){
            int msg = Integer.parseInt(s2.substring(0, 1));
            //System.out.println("Noob: " + msg);

            if (msg == 0){
                input[0] = msg;
            }else if (msg == 2){
                input[0] = msg;
            }else{
                input[0] = 1;
            }
            input[0] = Integer.parseInt(s2.substring(0, 1));



        //Player 2
        }else if (s1.contains("imon")){

            int msg = Integer.parseInt(s2.substring(0, 1));
            //System.out.println("Simon: " + msg);

            if (msg == 0){
                input[1] = msg;
            }else if (msg == 2){
                input[1] = msg;
            }else{
                input[1] = 1;
            }
        }

/*
        if (w.isKeyPressed("w")){
            input[0] = 2;
        }else if (w.isKeyPressed("s")){
            input[0] = 0;
        }else{
            input[0] = 1;
        }
        if (w.isKeyPressed("up") && bars[1].pos.y > 0){
            input[1] = 2;
        }
        if (w.isKeyPressed("down") && bars[1].pos.y < w.getHeight() - bars[1].height){
            input[1] = 0;
        }

 */

    }

    public void moveElements(){
        b.move(w);
        bars[0].move(input[0]);
        bars[1].move(input[1]);
    }

    public void drawElements(){
        b.draw(w);
        for (Bar b : bars){
            b.draw(w);
        }
    }

    public Bar[] getBars(){
        return bars;
    }

    public Window getWindow(){
        return w;
    }


    public void restart(){
        createAssets();
        gameOver = false;
        System.out.println("Player 1: " + scores[0]);
        System.out.println("Player 2: " + scores[1]);
        System.out.println("----------------------");
    }

    public void createAssets(){
        //Create Ball
        b = new Ball(20, 80,2.5);

        //Create Bars
        int barheight = 200;
        int barwidth = 30;
        double barspeed = 5;
        bars[0] = new Bar(barwidth, (w.getHeight() - barheight)/2, barwidth, barheight, barspeed);
        bars[1] = new Bar(w.getWidth() - 2 * barwidth, (w.getHeight() - barheight)/2, barwidth, barheight, barspeed);
        //bars[2] = new Bar((w.getWidth() - barheight) /2, 2 * barwidth, barheight, barwidth, barspeed);
        //bars[3] = new Bar((w.getWidth() - barheight) /2, w.getHeight() - 2 * barwidth, barheight, barwidth, barspeed);

    }

}
