
public class CollisionHandler extends MainGame{

    public static void hasBorderCollision(){
        int x = snakeBody.getFirst().getX();
        int y = snakeBody.getFirst().getY();
        if((x < 0 || x >= 300) || (y < 0 || y >= 300)){
            System.exit(0);
            //GameOver
        }
    }

    public static void noBorderCollision(){
        int x = snakeBody.getFirst().getX();
        int y = snakeBody.getFirst().getY();
        if(x >= 300) snakeBody.getFirst().setX(0);
        if(x < 0)   snakeBody.getFirst().setX(300);
        if(y >= 300) snakeBody.getFirst().setY(0);
        if(y < 0)   snakeBody.getFirst().setY(300);
    }

    public static void checkBodyCollision(){
        int x = snakeBody.getFirst().getX();
        int y = snakeBody.getFirst().getY();

        for(int i = 1; i < snakeBody.size(); ++i){
            if(snakeBody.get(i).getX() == x && snakeBody.get(i).getY() == y){
                System.exit(0);
                //Game Over
            }
        }
    }
}
