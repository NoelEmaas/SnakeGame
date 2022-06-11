import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.Random;

public class MainGame extends JPanel implements ActionListener, KeyListener {
    Timer time = new Timer(100, this);
    LinkedList<Coordinates> snakeBody = new LinkedList<>();
    int velx = 10, vely = 0;
    int foodX, foodY;
    char direction = 'r';

    public MainGame(){
        time.start();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        snakeBody.add(new Coordinates(100, 100, 90, 100));
        snakeBody.add(new Coordinates(90, 100, 80, 100));
        snakeBody.add(new Coordinates(80, 100, 70, 100));
        generateFood();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.RED);
        for (Coordinates coordinates : snakeBody) {
            g.fillRect(coordinates.getX(), coordinates.getY(), 10, 10);
        }

        g.setColor(Color.black);
        g.fillRect(foodX, foodY, 10, 10);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        //printLocation();
        checkCollision();

        snakeBody.getFirst().setPrev_x(snakeBody.getFirst().getX());
        snakeBody.getFirst().setPrev_y(snakeBody.getFirst().getY());
        snakeBody.get(0).setX(snakeBody.get(0).getX() + velx);
        snakeBody.get(0).setY(snakeBody.get(0).getY() + vely);

        for(int i = 1; i < snakeBody.size(); ++i){
            snakeBody.get(i).setPrev_x(snakeBody.get(i).getX());
            snakeBody.get(i).setPrev_y(snakeBody.get(i).getY());
            snakeBody.get(i).setX(snakeBody.get(i-1).getPrev_x());
            snakeBody.get(i).setY(snakeBody.get(i-1).getPrev_y());
        }

        if(snakeBody.getFirst().getX() == foodX && snakeBody.getFirst().getY() == foodY){
            generateFood();
            ateFood();
        }

        repaint();
    }

    public void generateFood(){
        Random rand = new Random();
        boolean valid = false;

        while(!valid){
            valid = true;

            foodX = (rand.nextInt(30 - 1) + 1) * 10;
            foodY = (rand.nextInt(30 - 1) + 1) * 10;

            for(Coordinates coordinates : snakeBody){
                if (coordinates.getX() == foodX && coordinates.getX() == foodY) {
                    valid = false;
                    break;
                }
            }
        }
    }

    public void checkCollision(){
        int x = snakeBody.getFirst().getX();
        int y = snakeBody.getFirst().getY();

        for(int i = 1; i < snakeBody.size(); ++i){
            if(snakeBody.get(i).getX() == x && snakeBody.get(i).getY() == y){
                System.exit(0);
            }
        }
    }

    public void printLocation(){
        for(int i = 0; i < snakeBody.size(); ++i){
            System.out.println(i+1 + ": " + snakeBody.get(i).getX() + " " + snakeBody.get(i).getY());
        }
    }

    public void ateFood(){
        if(direction == 'l') snakeBody.add(new Coordinates(snakeBody.getLast().getPrev_x(), snakeBody.getLast().getPrev_y(), snakeBody.getLast().getPrev_x()+10, snakeBody.getLast().getPrev_y()));
        if(direction == 'r') snakeBody.add(new Coordinates(snakeBody.getLast().getPrev_x(), snakeBody.getLast().getPrev_y(), snakeBody.getLast().getPrev_x()-10, snakeBody.getLast().getPrev_y()));
        if(direction == 'u') snakeBody.add(new Coordinates(snakeBody.getLast().getPrev_x(), snakeBody.getLast().getPrev_y(), snakeBody.getLast().getPrev_x(), snakeBody.getLast().getPrev_x()+10));
        if(direction == 'd') snakeBody.add(new Coordinates(snakeBody.getLast().getPrev_x(), snakeBody.getLast().getPrev_y(), snakeBody.getLast().getPrev_x()+10, snakeBody.getLast().getPrev_x()-10));
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {}
    @Override
    public void keyReleased(KeyEvent keyEvent) {}

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int key = keyEvent.getKeyCode();

        if(key == keyEvent.VK_LEFT  && direction != 'r') { velx = -10; vely = 0;   direction = 'l';}
        if(key == keyEvent.VK_RIGHT && direction != 'l') { velx = 10;  vely = 0;   direction = 'r';}
        if(key == keyEvent.VK_UP    && direction != 'd') { velx = 0;   vely = -10; direction = 'u';}
        if(key == keyEvent.VK_DOWN  && direction != 'u') { velx = 0;   vely = 10;  direction = 'd';}
    }

    public static void main(String[] args){
        MainGame game = new MainGame();
        JFrame frame = new JFrame();
        frame.setTitle("Snake Game");
        frame.setSize(300, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(game);
    }
}
