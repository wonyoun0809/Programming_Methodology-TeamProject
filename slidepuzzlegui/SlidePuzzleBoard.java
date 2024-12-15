package slidepuzzlegui;

import java.util.Random;

/** SlidePuzzleBoard models a slide puzzle. */
public class SlidePuzzleBoard { 
	private final PuzzlePiece[][] board;
	private final int size;

	// 빈칸의 좌표 
	private int empty_row; // 행
	private int empty_col; // 열
	
	/** Constructor - SlidePuzzleBoard 초기 퍼즐 보드 설정 - 감소하는 순으로 나열 
	 * @param s 퍼즐 보드 크기 */
	public SlidePuzzleBoard(int s) {
		size = s;
		// size x size 보드 만들기
		board = new PuzzlePiece[size][size];
		// 퍼즐 조각 1~(size^2 - 1)를 보드에 역순으로 끼우기
		int number = size * size - 1;
		for (int row = 0; row < size; row++)
			for (int col = 0; col < size; col++) {
				board[row][col] = new PuzzlePiece(number);
				number -= 1;
			}
		board[size-1][size-1] = null;
		empty_row = size-1;
		empty_col = size-1;
	}

	/** contents 현재 퍼즐 조각을 리턴
    * @return 퍼즐 조각  */
	public PuzzlePiece getPuzzlePiece(int row, int col) { 
		return board[row][col];
	}
	
	/**이동이 가능하면, 퍼즐 조각을 빈칸으로 이동시킴
	 * @param w 이동하기 원하는 퍼즐 조각의 액면가를 받아옴.
	 * @return 이동 성공시 true 리턴, 이동 실패 시 false 리턴 */
	public boolean move(int w) {

		// 게임이 끝나면 이동 불가능
		if (gameOver()) {
			return false;
		}

		int row, col; // w의 위치
		// 빈칸의 동서남북에 w가 있는지 하나씩 확인
		if (found(w, empty_row - 1, empty_col)) {
			row = empty_row - 1;
			col = empty_col;
		}
		else if (found(w, empty_row + 1, empty_col)) {
			row = empty_row + 1;
			col = empty_col;
		}
		else if (found(w, empty_row, empty_col - 1)) {
			row = empty_row;
			col = empty_col - 1;
		}
		else if (found(w, empty_row, empty_col + 1)) {
			row = empty_row;
			col = empty_col + 1;
		}
		else
			return false;
		// w를 빈칸에 복사
		board[empty_row][empty_col] = board[row][col];
		// 빈칸 위치를 새로 설정하고, w를 제거
		empty_row = row;
		empty_col = col;
		board[empty_row][empty_col] = null;
		return true;
	}
	
	/**빈칸의 동서남북에 퍼즐 조각이 있는지 확인하기 위해서, 바로 위의 move 메소드에서만 사용*/
	private boolean found(int v, int row, int col) { 
		if (row >= 0 && row < size && col >= 0 && col < size)
			return board[row][col].faceValue() == v; 
	    else
	    	return false;
	}

	/**풀 수 있는 퍼즐인지 확인
	 * @return 풀 수 있으면 true, 풀 수 없으면 false*/
	private boolean canSolve(int[] random) {

//		System.out.print("검사할 난수: ");
//		for (int i = 0; i < random.length; i++)
//			System.out.print(random[i] + ", ");
//		System.out.println();

		int inversion = 0;
		for (int i = 0; i < random.length; i++) {
			for (int j = i+1; j < random.length; j++) {
				if (random[i] > random[j]) {
//					System.out.print("감지, ");
					inversion++;
				}
			}

		}
//		System.out.println();

		/*
		풀 수 있는 조건
		- 한 변이 홀수이면, inversion 짝수
		- 한 변이 짝수이면
			fromBottom 짝수 && inversion 홀수
			fromBottom 홀수 && inversion 짝수
		*/
		if (random.length == 8 && inversion % 2 == 0) { // 한 변 홀수
//			System.out.println("완료: 한 변이 홀수, inversion: " + inversion);
			return true;
		} else if (random.length == 15) { // 한 변 짝수
			if (empty_row % 2 == 0 && inversion % 2 == 1) {
//				System.out.println("완료: 한 변이 짝수, inversion: " + inversion + ", empty_row: " + empty_row);
				return true;
			} else if (empty_row % 2 == 1 && inversion % 2 == 0) {
//				System.out.println("완료: 한 변이 짝수, inversion: " + inversion + ", empty_row: " + empty_row);
				return true;
			} else {
//				System.out.println("실패: 난수 재설정, 사유: inversion: " + inversion + ", empty_row: " + empty_row);
				return false;
			}
		} else {
//			System.out.println("실패: 난수 재생성, 사유: inversion: " + inversion);
			return false;
		}

	}

	/**퍼즐 보드를 랜덤으로 섞기 위해, 바로 아래의 createPuzzleBoard 메소드에서만 사용*/
	private int[] generateRandomPermutation(int n) {
		Random random = new Random();
		int[] permutation = new int[n];
		int count = 0;

		do {
//			System.out.println("난수 생성 중");
			count++;
			for (int i = 0; i < n; i++) {
				// nextInt(int n) : 0~n 미만의 정수형 난수 반환
				int d = random.nextInt(i + 1);
				permutation[i] = permutation[d];
				permutation[d] = i;
			}
		} while (!canSolve(permutation));
//		System.out.println("난수 생성 완료. 총 시도: " + count + "번");
		return permutation;
    }

	/**createPuzzleBoard - 퍼즐 게임 초기 보드 생성*/
	public void createPuzzleBoard() {
//		System.out.println("퍼즐 보드 생성 중");
		int [] numbers = generateRandomPermutation(size * size - 1);
		int i = 0;
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				if (col != (size-1) || row != (size-1)) {
					board[row][col] = new PuzzlePiece(numbers[i] + 1);
					i++;
				}
				else {
					board[size-1][size-1] = null;
					empty_row = size-1;
					empty_col = size-1;
				}
			}
		}
//		System.out.println("퍼즐 보드 생성 완료");
	}

	/**gameOver - 퍼즐 게임이 끝났는지를 확인
	 * @ return 목표를 달성했으면 true, 아직 더 진행해야 하면 false*/
	public boolean gameOver() {
		if (empty_row != size-1 || empty_col != size-1) {
			return false;
		}
		else {
			int number = 1;
			for (int row = 0; row < size; row++) {
				for (int col = 0; col < size; col++) {
					if (col != size-1 || row != size-1) {
						if (board[row][col].faceValue() != number)
							return false;
						else
							number += 1;
					}
				}
			}
		}
		return true;
	}
}