import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Pong extends JPanel implements ActionListener, KeyListener {
    private Timer timer;
    private int ballX, ballY, ballSpeedX, ballSpeedY;
    private int player1Y, player2Y, playerSpeed;
    private int score1, score2;
    private boolean up1, down1, up2, down2;
    private static final int BOARD_WIDTH = 800;
    private static final int BOARD_HEIGHT = 600;
    private static final int BALL_SIZE = 20;
    private static final int PADDLE_WIDTH = 20;
    private static final int PADDLE_HEIGHT = 100;

    public Pong() {
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);

        ballX = BOARD_WIDTH / 2;
        ballY = BOARD_HEIGHT / 2;
        ballSpeedX = 5;
        ballSpeedY = 5;

        player1Y = BOARD_HEIGHT / 2 - PADDLE_HEIGHT / 2;
        player2Y = BOARD_HEIGHT / 2 - PADDLE_HEIGHT / 2;
        playerSpeed = 10;

        score1 = 0;
        score2 = 0;

        timer = new Timer(10, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRect(ballX, ballY, BALL_SIZE, BALL_SIZE);
        g.fillRect(0, player1Y, PADDLE_WIDTH, PADDLE_HEIGHT);
        g.fillRect(BOARD_WIDTH - PADDLE_WIDTH, player2Y, PADDLE_WIDTH, PADDLE_HEIGHT);
        g.drawString("Player 1: " + score1, 50, 50);
        g.drawString("Player 2: " + score2, BOARD_WIDTH - 100, 50);
    }

    public void actionPerformed(ActionEvent e) {
        update();
        repaint();
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP) {
            up2 = true;
        } else if (key == KeyEvent.VK_DOWN) {
            down2 = true;
        } else if (key == KeyEvent.VK_W) {
            up1 = true;
        } else if (key == KeyEvent.VK_S) {
            down1 = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP) {
            up2 = false;
        } else if (key == KeyEvent.VK_DOWN) {
            down2 = false;
        } else if (key == KeyEvent.VK_W) {
            up1 = false;
        } else if (key == KeyEvent.VK_S) {
            down1 = false;
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void update() {
        if (ballY < 0 || ballY > BOARD_HEIGHT - BALL_SIZE) {
            ballSpeedY = -ballSpeedY;
        }

        if (ballX < PADDLE_WIDTH && ballY > player1Y && ballY < player1Y + PADDLE_HEIGHT) {
            ballSpeedX = -ballSpeedX;
        } else if (ballX + BALL_SIZE > BOARD_WIDTH - PADDLE_WIDTH && ballY > player2Y && ballY < player2Y + PADDLE_HEIGHT) {
            ballSpeedX = -ballSpeedX;
        } else if (ballX < 0 || ballX > BOARD_WIDTH - BALL_SIZE) {
            if (ballX < 0) {
                score2++;
            } else {
                score1++;
            }
            ballX = BOARD_WIDTH / 2;
            ballY = BOARD_HEIGHT / 2;
            ballSpeedX = 5;
            ballSpeedY = 5;
        }

        if (up1) {
            player1Y -= playerSpeed;
        }
        if (down1) {
            player1Y += playerSpeed;
        }
        if (up2) {
            player2Y -= playerSpeed;
        }
        if (down2) {
            player2Y += playerSpeed;
        }

        if (player1Y < 0) {
            player1Y = 0;
        }
        if (player1Y + PADDLE_HEIGHT > BOARD_HEIGHT) {
            player1Y = BOARD_HEIGHT - PADDLE_HEIGHT;
        }
        if (player2Y < 0) {
            player2Y = 0;
        }
        if (player2Y + PADDLE_HEIGHT > BOARD_HEIGHT) {
            player2Y = BOARD_HEIGHT - PADDLE_HEIGHT;
        }

        ballX += ballSpeedX;
        ballY += ballSpeedY;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Pong");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Pong game = new Pong();
        frame.getContentPane().add(game);
        frame.pack();
        frame.setVisible(true);
    }
}
