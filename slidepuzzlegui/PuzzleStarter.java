package slidepuzzlegui;

import java.io.IOException;

public class PuzzleStarter {
    public static void main(String[] args) throws IOException {
        InputPlayerName p = new InputPlayerName();
        String name = p.playerName();

        InputLevel l = new InputLevel();
        int level = l.chooseLevel();

        // 퍼즐 보드 생성 후 프레임에 넘김.
        new PuzzleFrame(new SlidePuzzleBoard(level), level, name);
    }
}