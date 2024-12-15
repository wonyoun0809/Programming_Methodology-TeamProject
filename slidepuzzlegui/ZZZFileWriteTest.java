package slidepuzzlegui;

import java.io.*;

public class ZZZFileWriteTest {
    public static void main(String[] args) throws IOException {
//        FileWriter writer = new FileWriter("poem.txt");
//        PrintWriter outfile = new PrintWriter(writer);
//        outfile.println("가을이 오면");
//        outfile.println("학기가 저물고,");
//        outfile.println("시험이 끝나면서");
//        outfile.println("겨울이 온다.");
//        outfile.close();
//        new ZZZFileWriteTest().record();

        FileWriter writer = new FileWriter("test.txt");
        writer.close();
    }


    private PrintWriter outfile;
    private BufferedReader infile;
    private void record() {
        try {
            outfile = new PrintWriter(new FileWriter("ranking.txt", true));
            infile = new BufferedReader(new FileReader("ranking.txt"));

            String s = infile.readLine();
            if(s == null)
                System.out.println("아무것도 없음");
            System.out.println(s);

//            outfile.println("실험");
//            outfile.close();
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.toString());
        }
    }

}
