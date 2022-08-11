import java.util.Timer;
import java.util.TimerTask;

public class Timer_task extends TimerTask {
    BlockBreak frame_timer;
    int num = 0;
    Timer timer;

    public void run() {
        frame_timer.repaint();
        num++;

        if(num>1000){
            System.out.println("End");
            cancel();
            frame_timer.dispose();
            timer.cancel();
        }
    }
    public void setFlame(BlockBreak frame){
        frame_timer = frame;
    }
    public void setTimer(Timer time3){
        this.timer = time3;
    }
}


