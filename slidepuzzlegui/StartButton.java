package slidepuzzlegui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartButton extends JButton implements ActionListener {

    private SlidePuzzleBoard board;
    private PuzzleFrame frame;
    private Time time;


    public StartButton(SlidePuzzleBoard b, PuzzleFrame f, Time t) {
        super("Start");
        this.board = b;
        this.frame = f;
        this.time = t;
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
//        System.out.println("-----시작");
        board.createPuzzleBoard();
        frame.update();
//        System.out.println("시작 시간 측정");
        time.startGame();
    }
}
