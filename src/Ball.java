public class Ball {

    Position pos;
    double direction;
    int radius;
    double velocity;

    public Ball(int radius, double direction, double velocity){
        this.pos = new Position( Game.w.getWidth() / 2,  Game.w.getHeight() / 2);
        this.direction = direction;
        this.radius = radius;
        this.velocity = velocity;
    }

    public void draw(Window w){
        w.setColor(0, 0, 0);
        w.fillCircle(pos.x, pos.y, radius);
    }

    public void move(Window w){
        changeDirection(w);

        pos.x += Math.sin(Math.toRadians(direction)) * velocity;
        pos.y += Math.cos(Math.toRadians(direction)) * velocity;

        //Wacthing for game over
        if (pos.x > w.getWidth()){
            Main.g.gameOver = true;
            Main.g.scores[0]++;
        }else if(pos.x < 0){
            Main.g.gameOver = true;
            Main.g.scores[1]++;
        }
    }

    public void changeDirection(Window w){

        /*
        Frame of window - Hit
        */
        hitFrame(w);

        /*
        Bar - Hit
        */
        hitBar(w);
    }

    public void changeDirection(double a, double b){
        direction = a + b;
        if (direction < 0){
            direction += 360;
        }else if(direction > 360){
            direction -= 360;
        }
    }

    public void hitFrame(Window w){
        //If the ball hits the bottom of the window
        if (pos.y + radius > w.getHeight()){
            changeDirection(180, - direction);
            //If the ball hits the top of the window
        }else if(pos.y - radius < 0){
            changeDirection(180, - direction);
        }
    }

    public void hitBar(Window w){
        Bar[] bars = Main.g.getBars();

        for(Bar b : bars){

            //Right or Left Hit
            if (b.isHit(this) == 2 || b.isHit(this) == 4){
                changeDirection(360, - direction);

                //Get more angle on sides
                //Where hit [-100%, +100%]
                //If hit closer to the top, the value goes towards -1
                //If hit closer to the bottom, the value goes towards +1
                //If hit right in the middle, the value is 0
                double wherehit = (pos.y - (b.pos.y + b.height / 2)) / (b.height/2);
                double newdirection;
                if (b.pos.x > Main.g.getWindow().getWidth() / 2) {
                    newdirection = (direction + (wherehit * 30));
                }else{
                    newdirection = (direction - (wherehit * 30));
                }
                direction = newdirection;

                if (pos.x < b.pos.x + b.width && b.pos.x > b.pos.x){
                    pos.x = b.pos.x + b.width;
                }


            //Top Hit
            }else if(b.isHit(this) == 1){
                //Ball moves up
                if (direction > 90 && direction < 270){
                    pos.y = b.pos.y - radius;
                    System.out.println("jump1");
                //Ball moves down
                }else{
                    changeDirection(180, - direction);
                    System.out.println("jump2");
                }

            //Bottom Hit
            }else if(b.isHit(this) == 3){
                //Ball moves down
                if (direction < 90 && direction > 270){
                    pos.y = b.pos.y + b.height + radius;
                    System.out.println("jump3");
                    //Ball moves up
                }else{
                    changeDirection(180, -direction);
                    System.out.println("jump4");
                }
            }
        }
    }
}
