
import java.awt.Color;
import java.util.LinkedList;
import net.useobjects.draw.drawable.GroupView;
import net.useobjects.draw.drawable.RectangleView;
import net.useobjects.draw.drawable.TextView;
import net.useobjects.geom.Position;
import net.useobjects.timer.EventLoopTimer;
import net.useobjects.timer.TimerEvent;
import net.useobjects.timer.TimerListener;

public class PackageStack implements GetObject {

    private RectangleView packageStack;
    private String address;
    private TextView name;
    private LinkedList<Package> list;
    private EventLoopTimer timer;
    private Score score;

    public PackageStack(GroupView group, double positionX, double positionY, String address, Score score) {
        packageStack = new RectangleView(group, positionX, positionY, 50, 50, 0, Color.BLACK, false);
        this.address = address;
        name = new TextView(group, address, positionX, positionY - 30);
        list = new LinkedList();
        this.score = score;
        
        timer = new EventLoopTimer(20,new CheckScore());
        timer.start();

    }

    @Override
    public Position getPosition() {
        return packageStack.getPosition();
    }

    @Override
    public GetObject getNextObject() {
        return null;
    }

    @Override
    public void addPackageToList(Package pack) {
        list.addFirst(pack);
    }

    @Override
    public double getAngle() {
        return packageStack.getRotation();
    }

    @Override
    public boolean Track() {
        return false;
    }
    
    private class CheckScore implements TimerListener {

        @Override
        public void onTimerEvent(TimerEvent event) {
            if(!list.isEmpty()){
                if(list.getFirst().getAddress().equals(address)){
                    score.countGood();
                }else{
                    score.countBad();
                }
                list.removeFirst();
            }
        }
        
    }
}
