import java.awt.*;

public class Wall {
    private int[] x_size;   //x[0] ~ x[1]の窓サイズ
    private int[] y_size;   //y[0] ~ y[1]の窓サイズ
    Color color;
    private  int height;    //フレームサイズ
    private  int width;

    public Wall(int height,int width,Color color){
        this.height = height;
        this.width = width;
        this.color = color;
    }
    public void set_wallSize(int[] x_size,int[] y_size){
        this.x_size = x_size;
        this.y_size = y_size;
    }

    public void reflect(Ball ball){
        double[] position;
        position= ball.get_position();
        int x = (int) position[2];
        int y = (int) position[3];
        if(x<x_size[0]){
            x = x_size[0] + (x_size[0] - x);
            ball.reflect_RIGHTLEFT();
        }
        if(x>x_size[1]){
            x = x_size[1] -  (x - x_size[1]);
            ball.reflect_RIGHTLEFT();
        }
        if(y<y_size[0]) {
            y = y_size[0] + (y_size[0] - y);
            ball.reflect_UPDOWN();
        }
        if(y>y_size[1]){
            y = y_size[1] -  (y - y_size[1]);
            ball.reflect_UPDOWN();
        }
        ball.set_position(x,y);
    }

    public void draw(Graphics g){
        g.setColor(color);
        g.fillRect(0,0,this.width,this.y_size[0]);
        g.fillRect(0,this.y_size[1],this.width,this.height -this.y_size[1]);
        g.fillRect(0,this.y_size[0],this.x_size[0],this.y_size[1]-this.y_size[0]);
        g.fillRect(this.x_size[1],this.y_size[0],this.width-this.x_size[1],this.y_size[1]-this.y_size[0]);
    }


}
