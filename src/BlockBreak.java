import javax.swing.*;
import java.awt.*;

public class BlockBreak extends JFrame {

    private final int height = 500;
    private final int width = 500;
    private long startTime;

    Wall_Line wall;
    Ball ball;
    Obstacle obstacle1;


    public static void main(String[] args){
        System.out.println("Start");
        BlockBreak frame = new BlockBreak();
        java.util.Timer timer = new Timer();
        Timer_task timer_task = new Timer_task();
        timer_task.setFlame(frame);
        timer_task.setTimer(timer);
        timer.scheduleAtFixedRate(timer_task, 0,30);
    }


    public BlockBreak() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(this.width, this.height);
        setVisible(true);
        this.startTime = System.currentTimeMillis();
        this.ball = new Ball(100,100,1,0.1,Color.red);
        ball.set_radius(10);
        this.wall = new Wall_Line(this.height,this.width,Color.blue);
        this.wall.set_wallSize(new int[]{10, 450}, new int[]{10, 450});
        this.obstacle1 = new Obstacle(new int[]{50,70},new int[]{70,100},Color.green);
    }

    @Override
    public void paint(Graphics g){
        Image imgBuf = createImage(this.width,this.height);
        Graphics gBuf = imgBuf.getGraphics();               //gBufがバッファの画像
        gBuf.setColor(Color.white);
        gBuf.fillRect(0,0,this.width,this.height);
        long now_time = System.currentTimeMillis();

        //ボールを動かす
        this.ball.move();

        //壁とぶつかっているか確認して、ぶつかっていたら反射させる
        this.wall.reflect(this.ball);
        //this.obstacle1.reflect(this.ball);

        //位置を更新する
        this.ball.update();
        double[] position = this.ball.get_position();
        System.out.print(this.ball.ball_position_toString());


        //描画処理開始
        this.ball.draw(gBuf);
        this.wall.draw(gBuf);
        this.obstacle1.draw(gBuf);

        //バッファを画面に表示
        Graphics graphics = getContentPane().getGraphics();
        graphics.drawImage(imgBuf,0,0,this);
    }
}
