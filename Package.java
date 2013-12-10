
import java.awt.Color;
import net.useobjects.draw.drawable.GroupView;
import net.useobjects.draw.drawable.RectangleView;
import net.useobjects.draw.drawable.TextView;
import net.useobjects.geom.Position;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Martin
 */
public class Package {

    private RectangleView pack;
    private String address;
    private TextView name;

    public Package(GroupView group, double positionX, double positionY, String address) {
        pack = new RectangleView(group, positionX, positionY, 30, 15, 0, Color.RED, true);
        this.address = address;
        name = new TextView(group, this.address, pack.getPositionX(), pack.getPositionY()-7);
    }

    public void setPackagePosition(Position position) {
        pack.setPosition(position);
        name.setPosition(pack.getPositionX(), pack.getPositionY() - 7);
    }
    
    public String getAddress(){
        return address;
    }
    
    public Position getPosition(){
        return pack.getPosition();
    }
    /*  public void moveToPosition(Position position){
     pack.moveTowards(1, position);
     name.setPosition(pack.getPositionX(), pack.getPositionY()-7);
     } */
    public void moveToPosition(Position position) {
        pack.moveTowards(1, position);
        name.setPosition(pack.getPositionX(), pack.getPositionY() - 7);
    }
}
