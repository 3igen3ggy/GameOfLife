package nauka;

public class TheGameOfLife {

	
	public static void main(String[] args) throws InterruptedException {
		
		//x - size of arena, frames - frames amount, wait - steers of how quick the animation is
		int x = 20;
		int wait = 150;
		int t = 0;
		int frames = 100;
		
		boolean[][] gameBoard = createBoard(x);	
		boolean[][] largeBoard = new boolean[x+2][x+2];
		printBoard(gameBoard, x, t, frames);
		
		//animation
		while (t <= frames) {		
			largeBoard = largerBoard(gameBoard, x);
			gameBoard = nextFrame(gameBoard, largeBoard, x);
			printBoard(gameBoard, x, t, frames);
			t++;
			Thread.sleep(wait);
		}		
	}
	
	public static char checkUnit(boolean x) {
		if (x == false) {
			return ' ';
		} else {
			return 'X';
		}
	}
	public static boolean[][] createBoard(int n) {
		boolean[][] board = new boolean[n][n];
		int i;
		int j;
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++) {
				if (((int) (2 * Math.random())) == 0) {
					board[i][j] = false;
				} else if (((int) (2 * Math.random())) == 1) {
					board[i][j] = true; 
				}
			}
			j = 0;
		}
		i = 0;
		return board;
	}
	
	public static void printBoard(boolean[][] gameBoard, int n, int t, int frames) {
		int i = 0;
		int j = 0;
		
		System.out.print(" ");
		while (i < n) {

			System.out.print("─");
			i++;
		}
		i = 0;
		System.out.println();
		
		for (i = 0; i < n; i++) {
			System.out.print("|");
			for (j = 0; j < n; j++) {
				System.out.print(checkUnit(gameBoard[i][j]));
			}
			System.out.print("|");
			System.out.println();

			j = 0;

		}
		i = 0;
		System.out.print(" ");
		while (i < n) {

			System.out.print("─");
			i++;
		}
		System.out.print("Timer: " + t + "//" + frames);
		System.out.println();
	}
	
	public static boolean[][] largerBoard(boolean[][] gameBoard, int x) {
		boolean[][] largeBoard = new boolean[x+2][x+2];
		
		int i = 0;
		int j = 0;

		//first line
		largeBoard[0][0] = gameBoard[x-1][x-1];
		
		for (i = 0; i < x; i++) {
			largeBoard[0][i+1] = gameBoard[x-1][i];
		}
		
		largeBoard[0][x-1] = gameBoard[x-1][0];
		
		
		//inside
		j = 0;
		for (i = 0; i < x; i++) {
			largeBoard[i+1][0] = gameBoard[i][x-1];
			for (j = 0; j < x; j++) { 
				largeBoard[i+1][j+1] = gameBoard[i][j];
			}
			largeBoard[i+1][j+1] = gameBoard[i][0];
			j = 0;
		}
		
		//last line 
		largeBoard[x-1][0] = gameBoard[0][x-1];
		
		for (i = 0; i < x; i++) {
			largeBoard[x-1][i+1] = gameBoard[0][i];
		}
		
		largeBoard[x-1][x-1] = gameBoard[0][x-1];
		
		return largeBoard;
	}
	
	public static boolean[][] nextFrame(boolean[][] gameBoard, boolean[][] largeBoard, int x) {
		int i = 0;
		int j = 0;
		int n = 0;

		for (i = 1; i <= x; i++) {
			
			for (j = 1; j <= x; j++) {
				n = 0;
				
				if (largeBoard[i-1][j-1]) {
					n++;
				}
				if (largeBoard[i-1][j]) {
					n++;
				}
				if (largeBoard[i-1][j+1]) {
					n++;
				}
				if (largeBoard[i][j-1]) {
					n++;
				}
				if (largeBoard[i][j+1]) {
					n++;
				}
				if (largeBoard[i+1][j-1]) {
					n++;
				}
				if (largeBoard[i+1][j]) {
					n++;
				}
				if (largeBoard[i+1][j+1]) {
					n++;
				}
				
				if (gameBoard[i-1][j-1] == false) {
					if (n == 3) {
						gameBoard[i-1][j-1] = true;
					}
				}
				
				if (gameBoard[i-1][j-1] == true) {
					if (n != 3 && n!= 2) {
						gameBoard[i-1][j-1] = false;
					}
				}

				
			}
			j = 0;
		}
		return gameBoard;
	}
}
