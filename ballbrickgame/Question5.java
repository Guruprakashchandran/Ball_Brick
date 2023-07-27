package com.ballbrickgame;

import java.util.Arrays;
import java.util.Scanner;

public class Question5 {

//	private String[][] board = {{"W","W","W","W","W","W","W"},
//								{"W"," ","2","DE","1"," ","W"},
//								{"W"," ","1","1","1"," ","W"},
//								{"W"," ","1","DS","1"," ","W"},
//								{"W"," "," "," "," "," ","W"},
//								{"W"," "," "," "," "," ","W"},
//								{"W","G","G","0","G","G","W"}};;
	private String[][] board;
	private int ball;
	private int boardSize;
	private Scanner scanner = new Scanner(System.in);
	private int brickCount = 0;
	private int base = 0;
	private boolean isIterate = true;

	public static void main(String[] args) {

		new Question5().getInputs();
	}

	private void getInputs() {

		try {

			while (true) {

				System.out.print("Enter Size Of N*N Matrix : ");
				boardSize = scanner.nextInt();
				if (boardSize % 2 == 0) {

					System.out.println("Enter Odd Size Board!!!");
				} else
					break;
			}
			scanner.nextLine();
			board = new String[boardSize][boardSize];
			for (int i = 0; i < boardSize; ++i) {

				Arrays.fill(board[i], " ");
			}
			while (true) {

				System.out.print("Enter the Brick's Position and Brick Type: ");
				String brick = scanner.nextLine();
				String[] str = brick.split(" ");
				int row = Integer.parseInt(str[0]);
				int col = Integer.parseInt(str[1]);
				if (board[row][col] == " ") {

					board[row][col] = str[2];
					brickCount += !str[2].equals("DS") && !str[2].equals("DE") && !str[2].equals("B")
							? Integer.parseInt(str[2])
							: 0;
				}

				System.out.print("Do You Want To Continue(Y/N): ");
				if (scanner.next().charAt(0) == 'N') {
					break;
				}
				scanner.nextLine();
			}
			System.out.print("Enter Ball Count: ");
			ball = scanner.nextInt();
			patternForming();
			startGame(brickCount-1,brickCount/2);
			System.out.println(brickCount <= 0 ? "you are HURRY!!!" : "It's GAME OVER!!!");
		} catch (Exception e) {

			System.out.println("Wrong Input!!!\n" + e.getMessage());
		}
	}

	private void startGame(int row, int col) {

		try {
			board[row][col] = "0";
			printForm();
			while (brickCount > 0 && isIterate) {

				String direction = getDirection();
				board[row][col] = "_";
				if (direction.equalsIgnoreCase("ST")) {

					straightUp(row, col);
				} else if (direction.equalsIgnoreCase("LD")) {

					leftUp(row, col);
				} else {

					rightUp(row, col);
				}
			}
			return;
		} catch (Exception e) {

			System.out.println("Index Reached!!!\n" + e.getMessage());
		}
	}

	private void rightUp(int row, int col) {

		try {

			boolean isReach = true;
			boolean count = false;
			while (isReach) {

				row--;
				col++;
				if (board[row][col] != " ") {
					isReach = false;
				}
				if (board[row][col].equals("DS")) {

					dsPatternRemove(row, col);
					count = true;
				} else if (board[row][col].equals("DE")) {

					dePatternRemove(row, col);
					count = true;
				} else if (board[row][col].equals("B")) {

					board[row][col] = " ";
					base += 1;
					if (base % 2 == 1 && base < boardSize - 2) {
						board[boardSize - 1][boardSize + (base / 2) + 1] = "_";
					} else if (base < boardSize - 2) {
						board[boardSize - 1][boardSize - (base / 2) - 1] = "_";
					}
					count = true;
				} else if (board[row][col] != " " && board[row][col] != "W") {

					int value = Integer.parseInt(board[row][col]);
					board[row][col] = (value - 1 == 0) ? " " : Integer.toString(value - 1);
					brickCount -= 1;
					count = true;
				}
			}

			if (count == true) {

				straightDown(row, col);
			} else if (row == 0) {

				rightDown(row, col);
			} else if (board[row][col].equals("W")) {

				leftUp(row, col);
			}
		} catch (Exception e) {

			System.out.println("Index Reached!!!\n" + e.getMessage());
		}
	}

	private void rightDown(int row, int col) {

		try {

			boolean isReach = true, count = false;
			;
			while (isReach) {

				col++;
				row++;
				if (board[row][col] != " ") {

					isReach = false;
				}
				if (board[row][col].equals("DS")) {

					dsPatternRemove(row, col);
					count = true;
				} else if (board[row][col].equals("DE")) {

					dePatternRemove(row, col);
					count = true;
				} else if (board[row][col].equals("B")) {

					board[row][col] = " ";
					base += 1;
					if (base % 2 == 1 && base < boardSize - 2) {
						board[boardSize - 1][boardSize + (base / 2) + 1] = "_";
					} else if (base < boardSize - 2) {
						board[boardSize - 1][boardSize - (base / 2) - 1] = "_";
					}
					count = true;
				} else if (board[row][col] != " " && board[row][col] != "G" && board[row][col] != "0"
						&& board[row][col] != "W") {

					int value = Integer.parseInt(board[row][col]);
					board[row][col] = (value - 1 == 0) ? " " : Integer.toString(value - 1);
					brickCount -= 1;
					count = true;
				}
				if (board[row][col].equals("G")) {

					ball -= 1;
					isIterate = ball == 0 ? false : true;
				}
				if (board[row][col].equals("W")) {

					leftDown(row, col);
				} else if (count == true) {

					straightDown(row, col);
				} else if (board[row][col].equals("G") || board[row][col].equals("0") || board[row][col].equals("_")) {

					startGame(row,col);
				}
			}
		} catch (Exception e) {

			System.out.println("Index Reached!!!\n" + e.getMessage());
		}
	}

	private void straightDown(int row, int col) {

		try {

			boolean isReach = true, count = false;
			while (isReach) {

				row++;
				if (board[row][col] != " ") {
					isReach = false;
				}
				if (board[row][col].equals("DS")) {

					dsPatternRemove(row, col);
					count = true;
				} else if (board[row][col].equals("DE")) {

					dePatternRemove(row, col);
					count = true;
				} else if (board[row][col].equals("B")) {

					board[row][col] = " ";
					base += 1;
					if (base % 2 == 1 && base < boardSize - 2) {
						board[boardSize - 1][boardSize + (base / 2) + 1] = "_";
					} else if (base < boardSize - 2) {
						board[boardSize - 1][boardSize - (base / 2) - 1] = "_";
					}
					count = true;
				} else if (board[row][col] != " " && board[row][col] != "G" && board[row][col] != "0") {

					int value = Integer.parseInt(board[row][col]);
					board[row][col] = (value - 1 == 0) ? " " : Integer.toString(value - 1);
					brickCount -= 1;
					count = true;
				}
			}

			if (board[row][col].equals("G")) {

				ball -= 1;
				isIterate = ball == 0 ? false : true;
			}
			if (count == true) {

				straightDown(row, col);
			} else if (board[row][col] != " ") {

				startGame(row,col);
			}
		} catch (Exception e) {

			System.out.println("Index Reached!!!\n" + e.getMessage());
		}
	}

	private void leftUp(int row, int col) {

		try {

			boolean isReach = true, count = false;
			while (isReach) {

				row--;
				col--;
				if (board[row][col] != " ") {

					isReach = false;
				}
				if (board[row][col].equals("DS")) {

					dsPatternRemove(row, col);
					count = true;
				} else if (board[row][col].equals("DE")) {

					dePatternRemove(row, col);
					count = true;
				} else if (board[row][col].equals("B")) {

					board[row][col] = " ";
					base += 1;
					if (base % 2 == 1 && base < boardSize - 2) {
						board[boardSize - 1][boardSize + (base / 2) + 1] = "_";
					} else if (base < boardSize - 2) {
						board[boardSize - 1][boardSize - (base / 2) - 1] = "_";
					}
					count = true;
				} else if (board[row][col] != " " && board[row][col] != "W") {

					int value = Integer.parseInt(board[row][col]);
					board[row][col] = (value - 1 == 0) ? " " : Integer.toString(value - 1);
					brickCount -= 1;
					count = true;
				}
				if (count) {

					straightDown(row, col);
				} else if (row == 0) {

					leftDown(row, col);
				} else if (board[row][col].equals("W")) {

					rightUp(row, col);
				}

			}
		} catch (Exception e) {

			System.out.println("Index Reached!!!\n" + e.getMessage());
		}
	}

	private void leftDown(int row, int col) {

		try {

			boolean isReach = true, count = false;
			while (isReach) {

				row++;
				col--;
				if (board[row][col] != " ") {

					isReach = false;
				}
				if (board[row][col].equals("DS")) {

					dsPatternRemove(row, col);
					count = true;
				} else if (board[row][col].equals("DE")) {

					dePatternRemove(row, col);
					count = true;
				} else if (board[row][col].equals("B")) {

					board[row][col] = " ";
					base += 1;
					if (base % 2 == 1 && base < boardSize - 2) {
						board[boardSize - 1][boardSize + (base / 2) + 1] = "_";
					} else if (base < boardSize - 2) {
						board[boardSize - 1][boardSize - (base / 2) - 1] = "_";
					}
					count = true;
				} else if (board[row][col] != " " && board[row][col] != "G" && board[row][col] != "0"
						&& board[row][col] != "W") {

					int value = Integer.parseInt(board[row][col]);
					board[row][col] = (value - 1 == 0) ? " " : Integer.toString(value - 1);
					brickCount -= 1;
					count = true;
				}
				if (board[row][col].equals("G")) {

					ball -= 1;
					isIterate = ball == 0 ? false : true;
				}
				if (board[row][col].equals("W")) {

					rightDown(row, col);
				} else if (count == true) {

					straightDown(row, col);
				} else if (board[row][col].equals("G") || board[row][col].equals("0") || board[row][col].equals("_")) {

					startGame(row,col);
				}
			}
		} catch (Exception e) {

			System.out.println("Index Reached!!!\n" + e.getMessage());
		}
	}

	private void straightUp(int row, int col) {

		try {

			boolean isReach = true, count = false;
			while (isReach) {

				row--;
				if (board[row][col] != " ") {
					isReach = false;
				}
				if (board[row][col].equals("DS")) {

					dsPatternRemove(row, col);
					count = true;
				} else if (board[row][col].equals("DE")) {

					dePatternRemove(row, col);
					count = true;
				} else if (board[row][col].equals("B")) {

					board[row][col] = " ";
					base += 1;
					if (base % 2 == 1 && base < boardSize - 2) {
						board[boardSize - 1][(boardSize / 2) + (base / 2) + 1] = "_";
					} else if (base < boardSize - 2) {
						board[boardSize - 1][(boardSize / 2) - (base / 2)] = "_";
					}
					count = true;
				} else if (board[row][col] != " " && board[row][col] != "W") {

					int value = Integer.parseInt(board[row][col]);
					board[row][col] = (value - 1 == 0) ? " " : Integer.toString(value - 1);
					brickCount -= 1;
					count = true;
				}
				if (count == true || board[row][col] == "W") {

					straightDown(row, col);
				}
			}
		} catch (Exception e) {

			System.out.println("Index Reached!!1\n" + e.getMessage());
		}
	}

	private void dePatternRemove(int row, int col) {

		try {
			board[row][col] = "1";
			for (int i = 1; i < boardSize - 1; ++i) {

				if (board[row][i] == "DS") {

					dsPatternRemove(row, i);
				} else if (col != i && board[row][i] == "DE") {

					dePatternRemove(row, i);
				} else {
					brickCount -= board[row][i] != " " ? 1 : 0;
				}
				if (!board[row][i].equals(" ")) {

					int value = Integer.parseInt(board[row][i]);
					board[row][i] = value - 1 == 0 ? " " : Integer.toString(value - 1);
				} else if (!board[row][i].equals("W")) {

					board[row][i] = " ";
				}
			}
		} catch (Exception e) {

			System.out.println("Index Reached!!!\n" + e.getMessage());
		}
	}

	private void dsPatternRemove(int row, int col) {

		try {
			board[row][col] = "1";
			for (int i = 0; i < 9; ++i) {

				if (board[row + (i / 3) - 1][col + (i % 3) - 1] != "W") {

					if (i != 4 && board[row + (i / 3) - 1][col + (i % 3) - 1] == "DE") {
						dePatternRemove(row + (i / 3) - 1, col + (i % 3) - 1);
					} else if (i != col && board[row + (i / 3) - 1][col + (i % 3) - 1] == "DS") {
						dsPatternRemove(row + (i / 3) - 1, col + (i % 3) - 1);
					} else {

						brickCount -= !board[row + (i / 3) - 1][col + (i % 3) - 1].equals(" ") ? 1 : 0;
					}
					if (!board[row + (i / 3) - 1][col + (i % 3) - 1].equals(" ")) {

						int value = Integer.parseInt(board[row + (i / 3) - 1][col + (i % 3) - 1]);
						board[row + (i / 3) - 1][col + (i % 3) - 1] = value - 1 == 0 ? " "
								: Integer.toString(value - 1);
					} else if (!board[row + (i / 3) - 1][col + (i % 3) - 1].equals("W")) {

						board[row + (i / 3) - 1][col + (i % 3) - 1] = " ";
					}
				}
			}
		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("Index Reached!!!\n" + e.getMessage());
		}
	}

	private String getDirection() {

		try {

			System.out.print("Enter the direction in which ball need to traverse(LD/ST/RD): ");
			String direction = scanner.next();
			if (!direction.equalsIgnoreCase("ST") && !direction.equalsIgnoreCase("LD")
					&& !direction.equalsIgnoreCase("Rd")) {

				direction = getDirection();
			}
			return direction;
		} catch (Exception e) {

			System.out.println("Wrong Input!!!\n" + e.getMessage());
		}
		return null;
	}

	private void printForm() {

		try {

			System.out.println();
			for (int i = 0; i < boardSize; ++i) {
				for (int j = 0; j < boardSize; ++j) {

					System.out.print(board[i][j] + "\t");
				}
				System.out.println();
			}
		} catch (Exception e) {

			System.out.println("Index Reached!!!\n" + e.getMessage());
		}
	}

	private void patternForming() {

		try {

			for (int i = 0; i < boardSize; ++i) {
				for (int j = 0; j < boardSize; ++j) {

					if (i == 0 || j == 0 || j == boardSize - 1) {

						board[i][j] = "W";
					} else if (i == boardSize - 1 && j == (boardSize / 2)) {

						board[i][j] = "_";
					} else if (i == boardSize - 1) {

						board[i][j] = "G";
					}
				}
			}
			System.out.println("Ball Count is : " + ball);
		} catch (Exception e) {

			System.out.println("Index Reached!!\n" + e.getMessage());
		}
	}
}