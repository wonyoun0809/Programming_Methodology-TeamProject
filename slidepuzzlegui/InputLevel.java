package slidepuzzlegui;

import javax.swing.*;

public class InputLevel {

    private int level;

    /**난이도 선택 받음*/
    public int chooseLevel() {
        String input;
        while (!(level == 4 || level == 3)) { // 4또는 3을 입력할 때 까지 반복
            input = JOptionPane.showInputDialog("하드 : 4, 이지 : 3");
            level = Integer.parseInt(input);
        }
        return level;
    }
}