
import net.useobjects.geom.Position;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Martin
 */
public interface GetObject {
    public Position getPosition();
    public GetObject getNextObject();
    public void addPackageToList(Package pack);
    public double getAngle();
    public boolean Track();
}
