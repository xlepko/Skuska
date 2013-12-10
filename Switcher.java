
import java.awt.Color;
import java.util.concurrent.ConcurrentLinkedQueue;
import net.useobjects.draw.drawable.CircleView;
import net.useobjects.draw.drawable.GroupView;
import net.useobjects.draw.drawable.ImageView;
import net.useobjects.geom.Position;
import net.useobjects.mouse.MouseChangedAdapter;
import net.useobjects.mouse.MouseChangedEvent;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Martin
 */
public class Switcher implements GetObject {

    private CircleView switcher;
    private ImageView arrow;
    private GetObject[] objects;
    private int indexOfObject;
    private ConcurrentLinkedQueue<Package> list;

    public Switcher(GroupView group, double positionX, double positionY, GetObject... objects) {
        switcher = new CircleView(group, positionX, positionY, 20, Color.BLACK, false);
        switcher.addMouseChangedListener(new MouseClicker());

        arrow = new ImageView(group, positionX, positionY, objects[0].getAngle(), 1.0, "files//arrow40x40.png", 20, 20);

        this.objects = new GetObject[objects.length];
        System.arraycopy(objects, 0, this.objects, 0, objects.length);

        list = new ConcurrentLinkedQueue();
        indexOfObject = 0;
    }

    @Override
    public Position getPosition() {
        return switcher.getPosition();
    }

    @Override
    public GetObject getNextObject() {
        return objects[indexOfObject];
    }

    @Override
    public void addPackageToList(Package pack) {
        list.add(pack);
    }

    @Override
    public double getAngle() {
        return arrow.getRotation();
    }

    @Override
    public boolean Track() {
        return false;
    }

    private class MouseClicker extends MouseChangedAdapter {

        @Override
        public void onMouseClicked(MouseChangedEvent event) {
            indexOfObject = (indexOfObject + 1) % objects.length;
            arrow.setRotation(objects[indexOfObject].getAngle());
        }
    }
}
