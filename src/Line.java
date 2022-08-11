public class Line {
    private double[][] points = new double[2][2];
    public double x_inter;
    public double y_inter;
    public Line e_vec;
    public Line e_vertical_vec;

    public Line(double x1,double y1,double x2,double y2){
        this.points[0][0] = x1;
        this.points[1][0] = x2;
        this.points[0][1] = y1;
        this.points[1][1] = y2;
    }
    public void set(double x1,double y1,double x2,double y2){
        this.points[0][0] = x1;
        this.points[1][0] = x2;
        this.points[0][1] = y1;
        this.points[1][1] = y2;
    }
    public boolean exist_point(double x1,double y1){
        if(this.points[0][0] < this.points[1][0] ){
            if(this.points[0][0] <= x1 && x1 <= this.points[1][0] ){
                if(this.points[0][1] < this.points[1][1] ){
                    if(this.points[0][1] <= y1 && y1 <= this.points[1][1] ){
                        return true;
                    }
                }else{
                    if(this.points[1][1] <= y1 && y1 <= this.points[0][1] ){
                        return true;
                    }
                }
            }
        }else{
            if(this.points[1][0] <= x1 && x1 <= this.points[0][0] ){
                if(this.points[0][1] < this.points[1][1] ){
                    if(this.points[0][1] <= y1 && y1 <= this.points[1][1] ){
                        return true;
                    }
                }else{
                    if(this.points[1][1] <= y1 && y1 <= this.points[0][1] ){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public void reflect(Ball ball){
        //事前に計算された交点ー＞反射先終点　にball_lineを更新
        //速度方向を変換
        this.e_vec();   //単位ベクトル計算
        Line v1;
        v1 = new Line(0,0,
                this.points[1][0] - x_inter,this.points[1][1] - y_inter);
        Line d;
        //d = -e2(e2'v1);
        ball.ball_line.e_vec();
        d = ball.ball_line.e_vertical_vec.product(
                -1*ball.ball_line.e_vertical_vec.dot(v1));
        Line v2;
        //v2 = d + e(e'v1)
        v2 = d.add(ball.ball_line.e_vec.product(
                ball.ball_line.e_vec.dot(v1)));


        //ball オブジェクトの更新
        //ball_line,next,x,y,velocity を　変える
        ball.set_position((int)(this.x_inter + v2.points[1][0])
                                ,(int)(this.y_inter + v2.points[1][1]));
        ball.ball_line.set(this.x_inter,this.y_inter,
                this.x_inter + v2.points[1][0],this.y_inter + v2.points[1][1]);

        double[] velocity = ball.get_velocity();
        double norm_vel = Math.sqrt((velocity[0]*velocity[0] + velocity[1]*velocity[1]));
        v2.e_vec();
        ball.set_velocity(v2.e_vec.points[1][0]*norm_vel,
                            v2.e_vec.points[1][1]*norm_vel);

    }
    public void e_vec(){
        double x1 = this.points[0][0];
        double y1 = this.points[0][1];
        double x2 = this.points[1][0];
        double y2 = this.points[1][1];
        double norm = Math.sqrt((x1-x2) * (x1-x2) + (y1-y2) * (y1-y2));
        this.e_vec = new Line(0,0,(x2-x1)/norm, (y2-y1)/norm);
        this.e_vertical_vec = new Line(0,0,-1*(y2-y1)/norm,(x2-x1)/norm);
    }
    public Line add(Line line){
        Line line2;
        line2 = new Line(this.points[0][0] + line.points[0][0],
                this.points[0][1] + line.points[0][1],
                this.points[1][0] + line.points[1][0],
                this.points[1][1] + line.points[1][1]);
        return line2;
    }
    public Line product(double norm){
        Line line2;
        line2 = new Line(this.points[0][0]*norm,
                this.points[0][1]*norm,
                this.points[1][0]*norm,
                this.points[1][1]*norm);
        return line2;
    }
    public String toString(){
        String text;
        text =  "("
                +String.valueOf(this.points[0][0])
                +" , "
                +String.valueOf(this.points[0][1])
                +") ( "
                +String.valueOf(this.points[1][0])
                +" , "
                +String.valueOf(this.points[1][1])
                +")";
        return text;
    }

    public double dot(Line line){
        double x1 = this.points[1][0] - this.points[0][0];
        double y1 = this.points[1][1] - this.points[0][1];
        double x2 = line.points[1][0] - line.points[0][0];
        double y2 = line.points[1][1] - line.points[0][1];
        double dot;
        dot = (x1*x2 + y1*y2);
        return dot;
    }

    public double inter_section_distance(Line ball_line){
        //交点を計算ー＞交点が両方のラインに含まれるか確認ー＞ボールの始点からの距離を返す
        double x1 = this.points[0][0];
        double y1 = this.points[0][1];
        double x2 = this.points[1][0];
        double y2 = this.points[1][1];
        double x1q = ball_line.points[0][0];
        double y1q = ball_line.points[0][1];
        double x2q = ball_line.points[1][0];
        double y2q = ball_line.points[1][1];

        double deno;
        deno = (x1q - x2q)*(y1-y2) - (x1 - x2)*(y1q - y2q);
        //System.out.println(deno);

        if(deno < 0.0000001 && deno > -0.0000001){  //平行
            return 10000;
        }else{
            this.x_inter = -1 * ((x1q - x2q) * (x1 * y2 - x2 * y1) - (x1 - x2) * (x1q * y2q - x2q * y1q));
            this.x_inter = this.x_inter / deno;
            this.y_inter = (y1 - y2) * (x1q * y2q - x2q * y1q) - (y1q - y2q) * (x1 * y2 - x2 * y1);
            this.y_inter = this.y_inter / deno;
        }

        boolean cross_flag = (this.exist_point(this.x_inter,this.y_inter)) &&
                (ball_line.exist_point(this.x_inter,this.y_inter));

        if(cross_flag == true){
            double distance;
            distance = Math.sqrt((x1q-this.x_inter)*(x1q-this.x_inter)
                    + (y1q-this.y_inter)*(y1q-this.y_inter) );
            return distance;
        }else{
            return 10001;
        }
    }
}

