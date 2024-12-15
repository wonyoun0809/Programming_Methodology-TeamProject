package slidepuzzlegui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RankingButton extends JButton implements ActionListener {

    public RankingButton() {
        super("Ranking");
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        new RankingFrame(); // 랭킹 창 띄우기
    }
}