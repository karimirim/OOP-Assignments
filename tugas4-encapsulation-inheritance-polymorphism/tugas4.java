package tugas4;

import java.util.Scanner;

public class tugas4 {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("===== DEMO ENCAPSULATION, INHERITANCE & POLYMORPHISM =====");

        System.out.println("\n--- ENCAPSULATION (class Bentuk) ---");
        bentuk shape = new bentuk("HIJAU");
        shape.printInfo();
        System.out.println("Warna saat ini: " + shape.getWarna());
        System.out.print("Masukkan warna baru: ");
        String warnaBaru = input.nextLine();
        shape.setWarna(warnaBaru);
        shape.printInfo();

        System.out.println("\n--- INHERITANCE ---");
        bujurSangkar square = new bujurSangkar(4, "MERAH");
        lingkaran circle = new lingkaran(3, "BIRU");
        silinder cylinder = new silinder(4, 2, "UNGU");

        square.printInfo();
        System.out.println("Luas  : " + square.hitungLuas());

        circle.printInfo();
        System.out.println("Luas  : " + circle.hitungLuas());

        cylinder.printInfo();
        System.out.println("Volume: " + cylinder.hitungVolume());

        System.out.println("\n--- POLYMORPHISM ---");
        bentuk[] semuaBentuk = { shape, square, circle, cylinder };
        for (bentuk b : semuaBentuk) {
            b.printInfo();
        }

        System.out.println("\n===== SELESAI =====");

        input.close();
    }
}
