import java.awt.*;

public class Test {
    Ball ball;
    /*
    public static void main(String[] args) {
        System.out.println("Start");
        Line line2 = new Line(0,0,1,2);
        Line line1 = new Line(12,0,12,20);
        Test test = new Test();

        System.out.println(test.ball.ball_position_toString());
        System.out.println(test.ball.ball_nposition_toString());

        int sum = 0;
        for(int i=0;i<1000;i++){
            sum = sum + i;
        }

        test.ball.move();
        System.out.println(test.ball.ball_position_toString());
        System.out.println(test.ball.ball_nposition_toString());


        double distance = line1.inter_section_distance(test.ball.ball_line);
        line1.reflect(test.ball);
        System.out.println(test.ball.ball_velocity_toString());

        System.out.println(distance);

        line2.e_vec();

    }
    */
    public Test(){
        this.ball = new Ball(10,10,100,0, Color.red);
    }
}
