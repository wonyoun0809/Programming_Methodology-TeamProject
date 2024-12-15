package slidepuzzlegui;

import java.io.*;


public class ZZZTest {

    private Time timer;
    private RankingRecord recordRanking;
    private long Time_taken;

    public ZZZTest() throws IOException {
        this.timer = new Time();
        this.recordRanking = new RankingRecord("test", 3);
        Time_taken = 200;
    }

    public void recordtest() {
        recordRanking.Record(Time_taken);
    }

//    private void size() {
//
//        int width = 500;
//        int height = 100;
//
//        // 프레임 생성
//        JFrame f = new JFrame();
//        f.setSize(width, height);
//        f.setVisible(true);
//        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        // 프레임의 크기 구하기(창의 제목 표시줄과 테두리를 전부 보여줌)
//        int frameWidth = f.getWidth();
//        int frameHeight = f.getHeight();
//        System.out.println("- 프레임의 크기");
//        System.out.println("frame 넓이 = " + frameWidth + "\nframe 높이 = " + frameHeight);
//
//        // 창의 제목 표시줄과 테두리의 크기 구하기
//        System.out.println("\n- 창의 제목 표시줄과 테두리의 크기");
//        Insets insets = f.getInsets();
//        System.out.println("Top inset (Title bar): " + insets.top);
//        System.out.println("Left inset (Border): " + insets.left);
//        System.out.println("Right inset (Border): " + insets.right);
//        System.out.println("Bottom inset (Border): " + insets.bottom);
//
//        // 패널 생성 후 프레임에 추가
//        JPanel panel = new JPanel();
//        panel.setSize(width, height);
//        f.add(panel);
//
//        // 패널의 크기 구하기
//        System.out.println("\n- 패널의 크기");
//        int panelWidth = panel.getWidth();
//        int panelHeight = panel.getHeight();
//        System.out.println("panel 넓이 = " + panelWidth + "\npanel 높이 = " + panelHeight);
//    }

    private int[] i = new int[11];
    private void hihi() {
        for (int j = 0; j < 11; j++)
            i[j] = j;
        System.out.println(i.length);
    }

    public static void main(String[] args) throws IOException {
//        ZZZTest test = new ZZZTest();
//        test.recordtest();
//        test.size();
        new ZZZTest().hihi();
    }
}
