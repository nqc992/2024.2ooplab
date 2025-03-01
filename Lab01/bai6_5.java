package Lab1;
import java.util.Arrays;
import java.util.Scanner;

public class bai6_5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhap so luong phan tu trong mang: ");
        int size = scanner.nextInt();

        int[] array = new int[size];

        System.out.println("Nhap cac phan tu cua mang:");
        for (int i = 0; i < size; i++) {
            System.out.print("Phan tu " + (i + 1) + ": ");
            array[i] = scanner.nextInt();
        }

        Arrays.sort(array);

        System.out.println("Mang sau khi sap xep: " + Arrays.toString(array));

        int sum = 0;
        for (int num : array) {
            sum += num;
        }

        double average = (double) sum / size;

        System.out.println("Tong cac phan tu trong mang: " + sum);
        System.out.println("Trung binh cong cac phan tu trong mang: " + average);

        scanner.close();
    }
}
