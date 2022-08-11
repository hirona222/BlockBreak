import java.awt.*;
import java.util.ArrayDeque;
import java.util.Queue;

public class Ball {
    private double x = 0;
    private double y = 0;
    private double next_x;
    private double next_y;
    private double velocity_x = 0;
    private double velocity_y = 0;
    private Color color;
    private Color color_Afterimage;
    private long before_time;
    private long now_time;
    private double radius = 10;
    Queue<Double> que_pos_x;
    Queue<Double> que_pos_y;
    Line ball_line;

    public Ball(int x,int y,double velocity_x,double velocity_y,Color color){
        before_time =  System.currentTimeMillis();
        now_time =  System.currentTimeMillis();
        this.color = color;
        this.color_Afterimage = Color.CYAN;
        this.x = x;
        this.y = y;
        this.velocity_x = velocity_x;
        this.velocity_y = velocity_y;
        que_pos_x = new ArrayDeque<Double>();
        que_pos_y = new ArrayDeque<Double>();
        this.ball_line = new Line(x,y,x,y);
    }

    public void draw(Graphics g){
        if(que_pos_x.size()>= 1) {
            for (int i = 0; i < que_pos_x.size() ; i++) {
                double x = que_pos_x.remove();
                double y = que_pos_y.remove();

                g.setColor(color_Afterimage);
                g.fillOval((int)(x - radius), (int)(y - radius), (int)(radius * 2), (int)(radius * 2));

                que_pos_x.add(x);
                que_pos_y.add(y);
            }
        }

        g.setColor(color);
        g.fillOval((int)(x - radius), (int)(y - radius), (int)(radius * 2), (int)(radius * 2));
        
    }
    public void set_radius(int radius){
        this.radius = radius;
    }


    public void move(){
        //次の場所を計算する
        now_time =  System.currentTimeMillis();
        long elapsed_time = now_time - before_time;
        this.next_x = x + velocity_x*elapsed_time;
        this.next_y = y + velocity_y*elapsed_time;
        before_time = now_time;
        this.ball_line.set(this.x,this.y,next_x,next_y);
    }

    public double[] get_position(){
        //x,y,next_x,next_yの順に返す
        return new double[]{x,y,next_x,next_y};
    }
    public double[] get_velocity(){
        return new double[]{velocity_x,velocity_y};
    }
    public String ball_position_toString(){
        String text;
        text = "("
                +String.valueOf(this.x)
                +" , "
                +String.valueOf(this.y)
                +")";
        return text;
    }
    public String ball_velocity_toString(){
        String text;
        text = "("
                +String.valueOf(this.velocity_x)
                +" , "
                +String.valueOf(this.velocity_y)
                +")";
        return text;
    }
    public String ball_nposition_toString(){
        String text;
        text = "("
                +String.valueOf(this.next_x)
                +" , "
                +String.valueOf(this.next_y)
                +")";
        return text;
    }
    public void reflect_UPDOWN(){
        this.velocity_y = -1 * this.velocity_y;
    }
    public void reflect_RIGHTLEFT(){
        this.velocity_x = -1 * this.velocity_x;
    }
    public void set_position(int next_x,int next_y){
        //nextの位置を変更する
        this.next_x = next_x;
        this.next_y = next_y;
    }
    public void set_velocity(double velocity_x,double velocity_y){
        this.velocity_x = velocity_x;
        this.velocity_y = velocity_y;
    }

    public void update(){
        que_pos_x.add(this.x);
        que_pos_y.add(this.y);
        //次の位置に更新する
        x = next_x;
        y = next_y;

        if(que_pos_x.size()>10){
            //System.out.println("---------");
            //System.out.println(que_pos_x);
            que_pos_x.remove();
            que_pos_y.remove();
            //System.out.println(que_pos_x);
        }
    }


}
