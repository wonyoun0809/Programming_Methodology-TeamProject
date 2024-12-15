package slidepuzzlegui;

import java.awt.*;
import java.io.IOException;
import javax.swing.*;

public class PuzzleFrame extends JFrame {
	
	private SlidePuzzleBoard board;			// 퍼즐 보드
	private PuzzleButton[][] button_board;	// 퍼즐 버튼
	private int size;						// 보드의 크기 겸 레벨
	private String name;					// 사용자 이름

	private Time timer;						// 시간 측정
	private RankingRecord rank;				// 랭킹 기록

	/**퍼즐 프레임 생성자
	 * @param b 퍼즐보드
	 * @param size 크기, 난이도
	 * @param name 이름 */
	public PuzzleFrame(SlidePuzzleBoard b, int size, String name) throws IOException {

		// 받은 파라미터로 변수 초기화
		board = b;
		this.size = size;
		this.name = name;

		// 새로운 객체 생성
		button_board = new PuzzleButton[size][size];
		timer = new Time();
		rank = new RankingRecord(name, size);

		// 패널들을 담을 container 생성, 상하좌우중으로 구분
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());

		// 시작 버튼, 랭킹 버튼을 p1이라는 패널에 추가
		JPanel p1 = new JPanel(new FlowLayout());
		p1.add(new StartButton(board, this, timer));
		p1.add(new RankingButton());

		// p2라는 패널을 생성하고, 4*4의 그리드 형식으로 구분
		// 버튼으로 이루어진 보드 판을 생성 후 p2에 추가
		JPanel p2 = new JPanel(new GridLayout(size,size));
		for (int row = 0; row < button_board.length; row++)
			for (int col = 0; col < button_board.length; col++) {
				button_board[row][col] = new PuzzleButton(board,this);
				p2.add(button_board[row][col]);
			}

		// 상하좌우로 구분한 container에 p1(버튼들)을 상단에, p2(보드 판)를 중앙에 추가
		cp.add(p1, BorderLayout.NORTH);
		cp.add(p2, BorderLayout.CENTER);

		// 출력
		setTitle(name + "'s Slide Puzzle");
		setSize(250,300);
		setVisible(true);
		update();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	/**퍼즐 버튼 클릭 시 퍼즐 보드 업데이트(화면에 변견된 내용을 보여줌).
	 * PuzzleButton, StartButton 이 두 곳에서 사용
	 * @PuzzleButton: 버즐 버튼이 눌렸을 때, 실행되는 actionPerformed 메소드 내부에 있는 if문의 조건을 반별하는 과정에서 내부적으로 버튼을 움직임
	 * 				 그것을 사용자에 시각적으로 보여주 위해 이 메소드가 사용됨
	 * @StartButton: start 버튼을 눌렀을 때, 실행되는 actionPerformed 메소드 내부에 퍼즐 보드를 썪는 createPuzzleBoard 메소드를 사용하고
	 * 				update 메소드를 이용해 화면에 보여줌*/
	public void update() {
		// 퍼즐 보드 업데이트
		// 퍼즐 보드의 조각을 받음
		PuzzlePiece pp;
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				pp = board.getPuzzlePiece(row, col);
				if (pp != null)
					button_board[row][col].setText(Integer.toString(pp.faceValue()));
				else
					button_board[row][col].setText("");
			}
		}

		if (board.gameOver()) {
			finish();
        }

    }

	/**게임이 끝났을 때, 마지막 버튼을 Done으로 바꾸고, 시간 기록 / 바로 위의 update 메소드에서 사용*/
	private void finish() {
		button_board[size-1][size-1].setText("Done");
		timer.endGame();
//		System.out.println("종료 시간 측정");
		long time = timer.getElapsedTime();
		System.out.println("총 소요 시간:" + time);
		System.out.println("---랭킹 업데이트 시작");
		rank.Record(time);
		System.out.println("---랭킹 업데이트 완료");
		System.out.println("-----종료");
	}
}