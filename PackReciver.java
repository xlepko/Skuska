
import java.awt.Color;
import java.util.Random;
import net.useobjects.draw.drawable.GroupView;
import net.useobjects.draw.drawable.RectangleView;
import net.useobjects.timer.EventLoopTimer;
import net.useobjects.timer.SimpleTimer;
import net.useobjects.timer.TimerEvent;
import net.useobjects.timer.TimerListener;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Martin
 */
public class PackReciver {

    private RectangleView packReciver;
    private GetObject object;
    private Package pack;
    private String[] address;
    private GroupView group;
    private EventLoopTimer timer;

    public PackReciver(GroupView group, double positionX, double positionY, GetObject object, String ... address) {
        packReciver = new RectangleView(group, positionX, positionY, 50, 50, 0, Color.BLACK, false);

        this.object = object;

        this.address = new String[address.length];
        System.arraycopy(address, 0, this.address, 0, address.length);
        
        this.group = group;
        
        timer = new EventLoopTimer(1000,new Creating());
        timer.start();
        
        

    }

    private int delayGenerator(){
        Random rand = new Random();
        
        return ((rand.nextInt(3))+2)*1000;
    }
    private String nameGenerator() {

        Random rand = new Random();
        int randomNum = rand.nextInt(address.length);

        return address[randomNum];
    }

    public void packageGenerator() {
        pack = new Package(group, packReciver.getPositionX(), packReciver.getPositionY(), nameGenerator());
        object.addPackageToList(pack);
    }

    private class Creating implements TimerListener {

        @Override
        public void onTimerEvent(TimerEvent event) {
            packageGenerator();
            timer.setDelay(delayGenerator());
 //           SimpleTimer.sleep(delayGenerator());
        }
    }
}
