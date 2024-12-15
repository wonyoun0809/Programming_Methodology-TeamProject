package slidepuzzlegui;

import java.awt.event.*;
import javax.swing.*;

/**퍼즐 버튼 생성*/
public class PuzzleButton extends JButton implements ActionListener {

	private SlidePuzzleBoard board;
	private PuzzleFrame frame;

	/**생성자
	 * @param b 퍼즐 보드
	 * @param f 퍼즐 프레임*/
	public PuzzleButton(SlidePuzzleBoard b, PuzzleFrame f) {
		board = b;
		frame = f;
		addActionListener(this);
	}

	/**퍼즐 버튼이 눌렸을 때 실행됨*/
	public void actionPerformed(ActionEvent e) {
		// 버튼에 적힌 문자를 가져움와서 s에 저장
		String s = getText();
		// s가 Done이 아니거나, 빈칸이 아니거나, 움직인 경우 프레임 업데이트
		// if문의 3번째 조건을 검사하는 과정에서 퍼즐 조각을 움직여줌.
		if (!"Done".equals(s) && !"".equals(s) && board.move(Integer.parseInt(s)))
			frame.update();
	}
	
}