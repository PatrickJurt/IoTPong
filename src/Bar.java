public class Bar {

    Position pos;
    int width;
    int height;
    double velocity;

    public Bar(double x, double y, int width, int height, double velocity){
        this.pos = new Position(x, y);
        this.width = width;
        this.height = height;
        this.velocity = velocity;
    }


    public void draw(Window w){
        w.fillRect(pos.x, pos.y, width, height);
    }

    public void move(int input){
        if (input == 0 && pos.y < Main.g.getWindow().getHeight() - this.height){
            pos.y += velocity;
        }else if (input == 2 && pos.y > 0){
            pos.y -= velocity;
        }
    }

    /*
    0 - No hit
    1 - Top
    2 - Right
    3 - Bottom
    4 - Left
    5 - TopRight
    6 - BottomRight
    7 - BottomLeft
    8 - TopLeft
    */
    public int isHit(Ball ball){
        double ballX = ball.pos.x;
        double ballY = ball.pos.y;

        double barX = pos.x;
        double barY = pos.y;

        //Hits sides
        if (ballY > barY && ballY < barY + height && ballX + ball.radius > barX && ballX - ball.radius < barX + width) {
            if (ballX + ball.radius - barX - ballX - ball.radius - barX - width < 0) {
                return 4;
            } else {
                return 2;
            }
        }else if(ballX > barX && ballX < barX + width && ballY + ball.radius > barY && ballY - ball.radius < barY + height){
            if (ballY - ball.radius < pos.y + height) {
                return 3;
            } else {
                return 1;
            }
        }

        return 0;
    }

}
