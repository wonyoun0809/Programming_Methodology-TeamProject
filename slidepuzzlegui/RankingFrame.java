package slidepuzzlegui;

import javax.swing.*;
import java.io.*;

public class RankingFrame extends JFrame{

    public RankingFrame() {
        setTitle("Ranking");
        setVisible(true);
        setSize(500, 500);
    }

    public void showRanking() throws IOException {
        FileReader reader = new FileReader("ranking.txt");
        BufferedReader fileRead = new BufferedReader(reader);

        while (fileRead.ready()) {
            String line = fileRead.readLine();

        }
    }

}
