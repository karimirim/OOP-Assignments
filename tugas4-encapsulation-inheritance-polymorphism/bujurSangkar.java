package tugas4;

public class bujurSangkar extends bentuk {

    private double sisi;

    public bujurSangkar(double sisi, String warna) {
        super(warna);
        this.sisi = sisi;
    }

    public double getSisi() {
        return sisi;
    }

    public void setSisi(double sisi) {
        this.sisi = sisi;
    }

    public double hitungLuas() {
        return sisi * sisi;
    }

    @Override
    public void printInfo() {
        System.out.println(
            "Bujursangkar berwarna " + warna +
            ", luas = " + hitungLuas()
        );
    }
}