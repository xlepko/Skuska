
import java.awt.Color;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import net.useobjects.draw.drawable.GroupView;
import net.useobjects.draw.drawable.RectangleView;
import net.useobjects.geom.Position;
import net.useobjects.timer.EventLoopTimer;
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
public class Track implements GetObject {

    private RectangleView track;
    private GetObject nextObject;
    private EventLoopTimer timer;
    private ConcurrentLinkedQueue<Package> list;

    public Track(GroupView group, double positionX, double positionY, double lengthOfTrack, double angle, GetObject nextObject) {
        track = new RectangleView(group, positionX, positionY, lengthOfTrack, 30, angle, Color.GRAY, true);

        //       this.nextObjects = new GetObject[nextObjects.length];
        //       System.arraycopy(nextObjects, 0, this.nextObjects, 0, nextObjects.length);
        this.nextObject = nextObject;

        list = new ConcurrentLinkedQueue();
        timer = new EventLoopTimer(15, new Moving());
        timer.start();

    }

    @Override
    public Position getPosition() {
        Position ret = new Position((track.getPositionX()-(track.getWidth()/2)), track.getPositionY());
        return ret;
    }
    

    @Override
    public GetObject getNextObject() {
        return nextObject;
    }

    @Override
    public void addPackageToList(Package pack) {
   //     list.addLast(pack);
        list.add(pack);
    }

    @Override
    public double getAngle() {
        return track.getRotation();
    }

    @Override
    public boolean Track() {
        return true;
    }

    private class Moving implements TimerListener {

        @Override
        public void onTimerEvent(TimerEvent event) {

            for (Iterator<Package> it = list.iterator(); it.hasNext();) {
                Package temp = it.next();
                temp.moveToPosition(nextObject.getPosition());

                if (Position.distance(temp.getPosition(), nextObject.getPosition()) <= 0) {
                    if (nextObject.getNextObject() == null) {
                        nextObject.addPackageToList(temp);
                    } else {
                        if (nextObject.Track() == true) {
                            temp.moveToPosition(nextObject.getPosition());
                            nextObject.addPackageToList(temp);
                        } else {
                            temp.moveToPosition(nextObject.getNextObject().getPosition());
                            nextObject.getNextObject().addPackageToList(temp);
                        }
                    }
                    list.remove(temp);
                }
            }
            /*       if (!list.isEmpty()) {

             list.getFirst().moveToPosition(nextObject.getPosition());

             if (Position.distance(list.getFirst().getPosition(), nextObject.getPosition()) <= 0) {

                    
             if (nextObject.getNextObject() == null) {
             nextObject.addPackageToList(list.getFirst());
             } else {
             list.getFirst().moveToPosition(nextObject.getNextObject().getPosition());
             nextObject.getNextObject().addPackageToList(list.getFirst());
             }
             list.removeFirst();
             }
             }*/
        }
    }
}
