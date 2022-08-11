import java.awt.*;

public class Square {
    Color color;
    private int[] x_size;  //x[0] ~ x[1]の窓サイズ
    private int[] y_size;  //y[0] ~ y[1]の窓サイズ

    /*
       3
     0   2
       1
       時計回り
     */
    public Line[] line;

    public Square(int[] x_size,int[] y_size,Color color){
        this.x_size = x_size;
        this.y_size = y_size;
        this.color = color;
        this.line[0] = new Line(x_size[0],y_size[1],x_size[0],y_size[0]);
        this.line[1] = new Line(x_size[0],y_size[0],x_size[1],y_size[0]);
        this.line[2] = new Line(x_size[1],y_size[0],x_size[1],y_size[1]);
        this.line[3] = new Line(x_size[1],y_size[1],x_size[0],y_size[1]);

    }

    public void draw(Graphics g){
        g.setColor(color);
        g.fillRect(x_size[0], y_size[0],x_size[1]-x_size[0], y_size[1]-y_size[0]);
    }
}
