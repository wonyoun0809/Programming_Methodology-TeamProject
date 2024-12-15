package slidepuzzlegui;

import java.io.*;
import java.util.StringTokenizer;

public class RankingRecord {

    private final String new_name;  // 새로운 플레이어
    private final int new_level;    // 새로운 플레이어가 고른 레벨
    private String label = "name, level, time";

    // 작성을 위해 파일 열기
    private PrintWriter wfile;

    private String[] rank = new String[11];

    /**
     * 생성자
     *
     * @param n 이름: String
     * @param l 난이도(레벨): int
     */
    public RankingRecord(String n, int l) throws IOException {
        FileRead();
//        System.out.println("1. 생성자 시작");
        rank[0] = label;
//        System.out.println("2. rank[0] = " + rank[0]);
        new_name = n;
        new_level = l;
//        System.out.println("3. new_name = " + new_name + "new_level = " + new_level);

        System.out.println("5. rank 배열에 저장된 내용 확인");
        for (int i = 0; i < rank.length; i++) {
            System.out.println("rank[" + i + "] = " + rank[i]);
        }
    }

    /**ranking.txt 라는 파일 읽어서 rank 배열에 저장*/
    private void FileRead() {
        try {
            // 읽기 겸 만들기
            BufferedReader rfile = new BufferedReader(new FileReader("ranking.txt"));
            int i = 1;
            while (rfile.ready()) {
                String s = rfile.readLine();
                if (s == null || s.equals("null")) {
                    rank[i] = null;
                } else {
                    StringTokenizer t = new StringTokenizer(s, ",");
                    rank[i] = s;
                }
                i++;
            }
            rfile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**랭킹 기록*/
    public void Record(long duration) {
        Compare(duration);
        System.out.println("Compare 종료");

        System.out.println("Compare 종료 후 rank 배열: ");
        for (int i = 0; i < rank.length; i++)
            System.out.println("rank[" + i + "] = " + rank[i]);

        try {
            wfile = new PrintWriter(new FileWriter("ranking.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("ranking 파일에 쓰기 시작");
        for (int i = 0; i < rank.length; i++) {
            wfile.println(rank[i]);
        }
        wfile.close();
        System.out.println("ranking 파일에 쓰기 종료");
    }

    /**rank 배열에 새로운 기록 추가, 저장되어 있는 값을 순서에 맞게 정렬*/
    private void Compare(long duration) {
        System.out.println("Compare 시작");
        // 기존 기록의 rank,name,level,time 저장
        int old_level;
        long old_time;

//        while (true) {
//            //
//        }


        for (int i = 1; i < 11; i++) { // i: 1~10
            // 랭킹의 기록들 가져와서 name,level,time 순으로 저장
            System.out.println("rank[" + i + "] = " + rank[i]);
            System.out.print(i + ". ");
            if (rank[i] == null || rank[i].equals("null")) {
                System.out.println("if - rank[" + i + "] = null");
                rank[i] = (new_name + "," + new_level + "," + duration);
                return;
            } else {
                StringTokenizer t = new StringTokenizer(rank[i], ",");
                old_level = Integer.parseInt(t.nextToken());
                old_time = Long.parseLong(t.nextToken());
                System.out.println(old_level + ", " + old_time);
            }

            System.out.println("비교 시작");
            // 처음이 아닐 경우 난이도 비교
            if (old_level < new_level) { // 새로운 기록이 기존 기록보다 어려운 난이도를 했을 시
                System.out.println("1번 조건 change 시작");
                change(i, duration);
            } else if (old_level == new_level && old_time >= duration) {
                System.out.println("2번 조건 change 시작");
                // 새로운 기록과 기존 기록의 난이도가 같을 때, 새로운 기록이 더 빠를때 기록
                change(i, duration);
            }
            System.out.println("비교 끝");
        }
    }

    /**랭킹 변경 및 기록
     * @param TargetRank 추가할 기록이 들어가야하는 순위
     * @param duration 걸린 시간*/
    private void change(int TargetRank, long duration) {
        System.out.println("----change 시작");
        String[] copy = rank.clone();   // 배열 복제

        // rank 배열 출력
        System.out.print("rank 배열: ");
        for (int i = 0; i < rank.length; i++) {
            System.out.print(rank[i] + ", ");
        } System.out.println("");

        // copy 배열 출력
        System.out.print("copy 배열: ");
        for (int i = 0; i < copy.length; i++) {
            System.out.print(copy[i] + ", ");
        } System.out.println("");


        String New = new_name + "," + new_level + "," + duration; // 기록할 새로운 기록
        System.out.println("New: " + New);

        rank[TargetRank] = New; // 기록
        System.out.println("제대로 잘 들어갔나? " + rank[TargetRank]);

        // 나머지 순위 뒤로 하나씩 미루기
        for (int i = TargetRank; i < rank.length-1; i++) {
            rank[i + 1] = copy[i];
        }

        // 순위 뒤 미룬 후, rank 배열 출력
        System.out.print("미룬이 rank 배열: ");
        for (int i = 0; i < rank.length; i++) {
            System.out.print(rank[i] + ", ");
        } System.out.println("");

        // 순위 뒤 미룬 후, copy 배열 출력
        System.out.print("미룬이 copy 배열: ");
        for (int i = 0; i < copy.length; i++) {
            System.out.print(copy[i] + ", ");
        } System.out.println("");
    }

    private String toString(String[] a) { return a[0] + a[1] + a[2]; }

}
