import java.util.Scanner;

class Globle {
	public static String fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
}

class Main {
  public static void main(String[] args) {
  	Scanner scanner = new Scanner(System.in);

		System.out.println("CHESS BOARD LOADER");
		System.out.print("Enter a FEN code\n\tEx: " + Globle.fen + "\n: ");
		String userFen = scanner.nextLine();
		
		Board board = (userFen.length() > 0) ? new Board(userFen) : new Board();
		System.out.println(board);
  }
}

class Board {
	private String fen;
	private String[][] chessboard;
	private String turn;
	
	
	public Board(String fen){
		this.fen = fen;
		createBoard(8);
	}
	public Board(){
		fen = Globle.fen;
		createBoard(8);
	}
	private void createBoard(int size){
		chessboard = new String[size][size];
		int row = 0;
		int col = 0;
		
		for(int i = 0; i < fen.length(); i++){
			String letter = fen.substring(i, i + 1);
			if(letter.equals("/")){
				row ++;
				col = 0;
			} else if(letter.equals(" ")){
				turn = (fen.substring(i + 1, i + 2).equals("w")) ? "White" : "Black";
				break;
			} else if(Character.isDigit(fen.charAt(i))){
				for (int j = 0; j < Integer.valueOf(letter); j++){
					chessboard[row][col] = "-";
					col ++;
				}
			} else{
				chessboard[row][col] = letter;
				col ++;
			}
		}
	}
	public String getFen(){
		return fen;
	}
	public String getTurn(){
		return turn;
	}
	public void printBoard(){
		System.out.println("Board:");
		for(String[] row : chessboard){
			for (String piece : row){
				System.out.print(piece + " ");
			}
			System.out.println();
		}
	}

	public String toString(){
		printBoard();
		return "Turn: " + turn;
	}
}