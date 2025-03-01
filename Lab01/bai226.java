package Lab1;

import java.util.Scanner;

public class bai226 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Chon loai phuong trinh can giai:");
        System.out.println("1. Phuong trinh bac nhat mot an");
        System.out.println("2. He phuong trinh bac nhat hai an");
        System.out.println("3. Phuong trinh bac hai mot an");
        int luaChon = scanner.nextInt();

        switch (luaChon) {
            case 1:
                giaiPhuongTrinhBacNhat(scanner);
                break;
            case 2:
                giaiHePhuongTrinh(scanner);
                break;
            case 3:
                giaiPhuongTrinhBacHai(scanner);
                break;
            default:
                System.out.println("Lua chon khong hop le.");
        }

        scanner.close();
    }

    private static void giaiPhuongTrinhBacNhat(Scanner scanner) {
        System.out.println("Nhap he so cho phuong trinh dang ax + b = 0:");
        System.out.print("a: ");
        double a = scanner.nextDouble();
        System.out.print("b: ");
        double b = scanner.nextDouble();

        if (a == 0) {
            if (b == 0) {
                System.out.println("Phuong trinh co vo so nghiem.");
            } else {
                System.out.println("Phuong trinh vo nghiem.");
            }
        } else {
            double x = -b / a;
            System.out.println("Nghiem cua phuong trinh la x = " + x);
        }
    }

    private static void giaiHePhuongTrinh(Scanner scanner) {
        System.out.println("Nhap cac he so cho he phuong trinh:");
        System.out.print("a11: ");
        double a11 = scanner.nextDouble();
        System.out.print("a12: ");
        double a12 = scanner.nextDouble();
        System.out.print("b1: ");
        double b1 = scanner.nextDouble();
        System.out.print("a21: ");
        double a21 = scanner.nextDouble();
        System.out.print("a22: ");
        double a22 = scanner.nextDouble();
        System.out.print("b2: ");
        double b2 = scanner.nextDouble();

        double D = a11 * a22 - a21 * a12;
        double D1 = b1 * a22 - b2 * a12;
        double D2 = a11 * b2 - a21 * b1;

        if (D == 0) {
            if (D1 == 0 && D2 == 0) {
                System.out.println("He phuong trinh co vo so nghiem.");
            } else {
                System.out.println("He phuong trinh vo nghiem.");
            }
        } else {
            double x1 = D1 / D;
            double x2 = D2 / D;
            System.out.println("Nghiem cua he phuong trinh la x1 = " + x1 + " va x2 = " + x2);
        }
    }

    private static void giaiPhuongTrinhBacHai(Scanner scanner) {
        System.out.println("Nhap he so cho phuong trinh bac hai dang ax^2 + bx + c = 0:");
        System.out.print("a: ");
        double a = scanner.nextDouble();
        System.out.print("b: ");
        double b = scanner.nextDouble();
        System.out.print("c: ");
        double c = scanner.nextDouble();

        if (a == 0) {
            if (b == 0) {
                if (c == 0) {
                    System.out.println("Phuong trinh co vo so nghiem.");
                } else {
                    System.out.println("Phuong trinh vo nghiem.");
                }
            } else {
                double x = -c / b;
                System.out.println("Nghiem cua phuong trinh la x = " + x);
            }
        } else {
            double delta = b * b - 4 * a * c;

            if (delta > 0) {
                double x1 = (-b + Math.sqrt(delta)) / (2 * a);
                double x2 = (-b - Math.sqrt(delta)) / (2 * a);
                System.out.println("Phuong trinh co hai nghiem phan biet: x1 = " + x1 + " va x2 = " + x2);
            } else if (delta == 0) {
                double x = -b / (2 * a);
                System.out.println("Phuong trinh co nghiem kep: x = " + x);
            } else {
                System.out.println("Phuong trinh vo nghiem (khong co nghiem thuc).");
            }
        }
    }
}
