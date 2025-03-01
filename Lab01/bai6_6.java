package Lab1;
import java.util.Scanner;
public class bai6_6 {
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);

		System.out.println("Nhap so hang: ");
		int rows = keyboard.nextInt();
		System.out.println("Nhap so cot: ");
		int cols = keyboard.nextInt();

		if (rows <= 0 || cols <= 0) {
			System.out.println("LOI DU LIEU");
			
		}

		int[][] arr1 = new int[rows][cols];
		int[][] arr2 = new int[rows][cols];
		int[][] arr3 = new int[rows][cols];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				arr3[i][j] = 0;
			}
		}

		System.out.println("Nhap du lieu cho mang 1: ");
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				System.out.print("arr1[" + i + "][" + j + "]: ");
				arr1[i][j] = keyboard.nextInt();
			}
		}

		System.out.println("Nhap du lieu cho mang 2: ");

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				System.out.print("arr2[" + i + "][" + j + "]: ");
				arr2[i][j] = keyboard.nextInt();
			}
		}

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				arr3[i][j] += arr1[i][j] + arr2[i][j];
			}
		}

		System.out.println("Tong hai ma tran: ");
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				System.out.printf("%-5d", arr3[i][j]);
			}
			System.out.println();
		}

		keyboard.close();
	}
}
