import java.awt.*;

public class Wall_Line {
    private int[] x_size;   //x[0] ~ x[1]の窓サイズ
    private int[] y_size;   //y[0] ~ y[1]の窓サイズ
    Color color;
    private  int height;    //フレームサイズ
    private  int width;

    /*
       3
     0   2
       1
       時計回り
     */
    public Line[] line = new Line[4];

    public Wall_Line(int height,int width,Color color){
        this.height = height;
        this.width = width;
        this.color = color;
    }
    public void set_wallSize(int[] x_size,int[] y_size){
        this.x_size = x_size;
        this.y_size = y_size;
        this.line[0] = new Line(x_size[0],y_size[1],x_size[0],y_size[0]);
        this.line[1] = new Line(x_size[0],y_size[0],x_size[1],y_size[0]);
        this.line[2] = new Line(x_size[1],y_size[0],x_size[1],y_size[1]);
        this.line[3] = new Line(x_size[1],y_size[1],x_size[0],y_size[1]);
    }

    public void reflect(Ball ball){
        boolean flag = true;
        do {
            double min_distance = 1000;
            Line min_line = null;
            System.out.println("-----");
            for (Line line : this.line) {
                double distance = line.inter_section_distance(ball.ball_line);
                if (distance < min_distance) {
                    min_line = line;
                    min_distance = distance;
                }
            }

            //min_line と　反射させて　ballを更新する
            if (min_line != null) {
                min_line.reflect(ball);
            }else{
                flag = false;
            }
            System.out.println(ball.ball_velocity_toString());
        }while(flag);

    }

    public void draw(Graphics g){
        g.setColor(color);
        g.fillRect(0,0,this.width,this.y_size[0]);
        g.fillRect(0,this.y_size[1],this.width,this.height -this.y_size[1]);
        g.fillRect(0,this.y_size[0],this.x_size[0],this.y_size[1]-this.y_size[0]);
        g.fillRect(this.x_size[1],this.y_size[0],this.width-this.x_size[1],this.y_size[1]-this.y_size[0]);
    }
}
