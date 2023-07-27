package com.ballbrickgame;

import java.util.Arrays;
import java.util.Scanner;

public class Question1 {

	private String[][] board;
	private int ball;
	private int boardSize;

	public static void main(String[] args) {

		new Question1().getInputs();
	}

	private void getInputs() {

		Scanner scanner = new Scanner(System.in);
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
				board[row][col] = str[2];

				System.out.print("Do You Want To Continue(Y/N): ");
				if (scanner.next().charAt(0) == 'N') {
					break;
				}
				scanner.nextLine();
			}
			System.out.print("Enter Ball Count: ");
			ball = scanner.nextInt();
			patternForming();
			printForm();
		} catch (Exception e) {

			System.out.println("Wrong Input!!!\n" + e.getMessage());
		} finally {

			scanner.close();
		}
	}

	private void printForm() {

		try {

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

						board[i][j] = "0";
					} else if (i == boardSize - 1) {

						board[i][j] = "G";
					}
				}
			}
		} catch (Exception e) {

			System.out.println("Index Reached!!\n" + e.getMessage());
		}
	}
}
