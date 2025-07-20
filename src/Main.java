import java.util.Scanner;

class Node {
    String nim, nama, jurusan;
    Node next;

    public Node(String nim, String nama, String jurusan) {
        this.nim = nim;
        this.nama = nama;
        this.jurusan = jurusan;
        this.next = null;
    }
}

class MahasiswaLinkedList {
    Node head;
    int count = 0;
    final int MAX_DATA = 5;

    // Push dengan sort berdasarkan NIM
    public void push(String nim, String nama, String jurusan) {
        if (count >= MAX_DATA) {
            System.out.println("❌ Maksimal 5 data mahasiswa!");
            return;
        }

        if (nim.length() > 10 || nama.length() > 30 || jurusan.length() > 50) {
            System.out.println("❌ Validasi gagal! Pastikan panjang NIM/Nama/Jurusan sesuai batas.");
            return;
        }

        Node newNode = new Node(nim, nama, jurusan);

        // Insert di awal jika list kosong atau NIM lebih kecil
        if (head == null || nim.compareTo(head.nim) < 0) {
            newNode.next = head;
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null && current.next.nim.compareTo(nim) < 0) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }

        count++;
        System.out.println("✅ Data mahasiswa berhasil ditambahkan.");
    }

    public void tampilkan() {
        if (head == null) {
            System.out.println("📭 Tidak ada data mahasiswa.");
            return;
        }

        System.out.println("\n📋 Daftar Mahasiswa:");
        Node current = head;
        while (current != null) {
            System.out.println("NIM    : " + current.nim);
            System.out.println("Nama   : " + current.nama);
            System.out.println("Jurusan: " + current.jurusan);
            System.out.println("---------------------------");
            current = current.next;
        }
    }

    public void popAll() {
        head = null;
        count = 0;
        System.out.println("🗑️ Semua data mahasiswa telah dihapus.");
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MahasiswaLinkedList list = new MahasiswaLinkedList();
        int pilihan;

        do {
            System.out.println("\n====== MENU DATABASE MAHASISWA ======");
            System.out.println("1. Push Data Mahasiswa");
            System.out.println("2. Tampilkan Semua Data");
            System.out.println("3. Pop Semua Data");
            System.out.println("4. Keluar");
            System.out.print("Pilih menu (1-4): ");
            pilihan = scanner.nextInt();
            scanner.nextLine(); // Buang newline

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan NIM (maks 10 angka): ");
                    String nim = scanner.nextLine();
                    System.out.print("Masukkan Nama (maks 30 karakter): ");
                    String nama = scanner.nextLine();
                    System.out.print("Masukkan Jurusan (maks 50 karakter): ");
                    String jurusan = scanner.nextLine();
                    list.push(nim, nama, jurusan);
                    break;
                case 2:
                    list.tampilkan();
                    break;
                case 3:
                    list.popAll();
                    break;
                case 4:
                    System.out.println("🚪 Keluar dari program. Sampai jumpa!");
                    break;
                default:
                    System.out.println("❗ Pilihan tidak valid!");
            }

        } while (pilihan != 4);

        scanner.close();
    }
}
