
import net.useobjects.draw.drawable.GroupView;
import net.useobjects.eventloop.MainActivity;
import net.useobjects.frame.MainWindow;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Martin
 */
public class ProgramActivity implements MainActivity {

    @Override
    public void onInit() {
        MainWindow window = new MainWindow("Simulacia", 1000, 600);
        window.setVisible(true);
        GroupView group = window.getRootGroup();

        Score score = new Score(group, 50, 50);

        PackageStack stack1 = new PackageStack(group, 800, 100, "Brezno", score);
        PackageStack stack2 = new PackageStack(group, 800, 200, "Bratislava", score);
        PackageStack stack3 = new PackageStack(group, 800, 300, "Nitra", score);
        PackageStack stack4 = new PackageStack(group, 800, 400, "Banska Bystrica", score);
        PackageStack stack5 = new PackageStack(group, 800, 500, "Kosice", score);




        Track track1 = new Track(group, 700, 125, 150, -0.25, stack1);
        Track track2 = new Track(group, 700, 175, 150, 0.25, stack2);

        Track track7 = new Track(group, 700, 350, 170, -0.50, stack3);
        Track track8 = new Track(group, 700, 400, 150, 0, stack4);
        Track track9 = new Track(group, 700, 450, 170, 0.50, stack5);

        Switcher switcher1 = new Switcher(group, 610, 150, track1, track2);
        Switcher switcher3 = new Switcher(group, 610, 400, track7, track8, track9);

//        Track track3 = new Track(group, 625, 400, 300, 0, stack3);
        Track track3 = new Track(group, 530, 400, 120, 0, switcher3);
        Track track4 = new Track(group, 475, 325, 150, Math.PI / 2, track3);
        Track track5 = new Track(group, 545, 190, 120, -0.55, switcher1);

        Switcher switcher2 = new Switcher(group, 475, 230, track5, track4);

        Track track6 = new Track(group, 355, 230, 200, 0, switcher2);


        //       PackReciver packReciver = new PackReciver(group, 230, 230, track6, "Brezno", "Bratislava", "Nitra");
        PackReciver packReciver = new PackReciver(group, 230, 230, track6, "Brezno", "Bratislava", "Nitra", "Banska Bystrica", "Kosice");


        //       packReciver.packageGenerator(group);
    }
}
