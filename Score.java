
import net.useobjects.draw.drawable.GroupView;
import net.useobjects.draw.drawable.TextView;

public class Score {

    private TextView score;
    private int good;
    private int bad;

    public Score(GroupView group, double positionX, double positionY) {
        good = 0;
        bad = 0;
        score = new TextView(group, "Dobre: " + good + " Zle: " + bad, positionX, positionY);
    }

    public void countGood() {
        good++;
        score.setText("Dobre: " + good + " Zle: " + bad);
    }

    public void countBad() {
        bad++;
        score.setText("Dobre: " + good + " Zle: " + bad);
    }
}
