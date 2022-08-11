import java.awt.*;

public class Obstacle {

    Color color;
    private int[] x_size;  //x[0] ~ x[1]の窓サイズ
    private int[] y_size;  //y[0] ~ y[1]の窓サイズ

    private int[][] edge_vector = new int[4][4];
    private int[][] normal_vector = new int[4][2];
    /*
       3
     0   2
       1
     の順で辺を設定する。
     edge_vector = 辺座標　[x[0],y[0],x[1],y[1]] 左上0ー＞右下1
     normal_vector = 法線ベクトル
     */

    public Obstacle(int[] x_size,int[] y_size,Color color){
        this.x_size = x_size;
        this.y_size = y_size;
        this.color = color;
        this.edge_vector[0] = new int[]{x_size[0], y_size[0], x_size[0], y_size[1]};
        this.edge_vector[1] = new int[]{x_size[0], y_size[1], x_size[1], y_size[1]};
        this.edge_vector[2] = new int[]{x_size[1], y_size[1], x_size[1], y_size[0]};
        this.edge_vector[3] = new int[]{x_size[1], y_size[0], x_size[0], y_size[0]};
        this.normal_vector[0] = new int[]{ 1, 0};
        this.normal_vector[1] = new int[]{ 0,-1};
        this.normal_vector[2] = new int[]{-1, 0};
        this.normal_vector[3] = new int[]{ 0, 1};
    }

    public double[] inter_section(int[] start,int[] end,int[] edge){
        // start->endの直線とedgeの交点を計算、edge内にあるかどうか計算する.start->endの間にあるかは関係ない
        //edge_vector = 辺座標　[x[0],y[0],x[1],y[1]]
        double x1 = start[0];
        double y1 = start[1];
        double x2 = end[0];
        double y2 = end[1];
        double x1q = edge[0];
        double y1q = edge[1];
        double x2q = edge[2];
        double y2q = edge[3];

        double deno;
        deno = (x1q - x2q)*(y1-y2) - (x1 - x2)*(y1q - y2q);
        double x_intersection;
        double y_intersection;
        System.out.println(deno);

        if(deno < 0.0000001 && deno > -0.0000001){
            return new double[]{10001, 10001};
        }else{
            x_intersection = -1 * ((x1q - x2q) * (x1 * y2 - x2 * y1) - (x1 - x2) * (x1q * y2q - x2q * y1q));
            x_intersection = x_intersection / deno;
            y_intersection = (y1 - y2) * (x1q * y2q - x2q * y1q) - (y1q - y2q) * (x1 * y2 - x2 * y1);
            y_intersection = y_intersection / deno;
        }
        if(x1q >= x2q){
            if(x1q >= x_intersection && x_intersection >= x2q){
                if(y1q > y2q){
                    if(y1q >= y_intersection && y_intersection >= y2q){
                        return new double[]{x_intersection,y_intersection};
                    }
                }else{
                    if (y2q >= y_intersection && y_intersection >= y1q) {
                        return new double[]{x_intersection, y_intersection};
                    }
                }
            }
        }else {
            if(x2q >= x_intersection && x_intersection >= x1q){
                if(y1q > y2q){
                    if(y1q >= y_intersection && y_intersection >= y2q){
                        return new double[]{x_intersection,y_intersection};
                    }
                }else {
                    if (y2q >= y_intersection && y_intersection >= y1q) {
                        return new double[]{x_intersection, y_intersection};
                    }
                }
            }
        }
        return new double[]{10000, 10000};
    }

    public boolean inner_product(int[] start,int[] end,int[] normal_vector){
        //内積を計算して、内側への方向であればtrue,外へはfalse
        double product;
        product = (end[0]-start[0]) * normal_vector[0] + (end[1]-start[1]) * normal_vector[1];
        if(product > 0){
            return true;
        }else{
            return false;
        }
    }

    public boolean inner(double[] points){
        //四角形の中に点が含まれるか true->in , false->out
        //points[2] = [x,y]の構造
        if(x_size[0] < points[0] && points[0] < x_size[1]){
            if(y_size[0] < points[1] && points[1] < y_size[1]){
                return true;
            }
        }
        return false;
    }

    public void refrect(Ball ball){

    }

    public void draw(Graphics g){
        g.setColor(color);
        g.fillRect(x_size[0], y_size[0],x_size[1]-x_size[0], y_size[1]-y_size[0]);
    }




}
