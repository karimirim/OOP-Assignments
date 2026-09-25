package tugas4;

public class silinder extends lingkaran {

    private double tinggi;

    public silinder(double tinggi, double radius, String warna) {
        super(radius, warna);
        this.tinggi = tinggi;
    }

    public double getTinggi() {
        return tinggi;
    }

    public void setTinggi(double t) {
        this.tinggi = t;
    }

    public double hitungVolume() {
        return hitungLuas() * tinggi;
    }

    @Override
    public void printInfo() {
        System.out.println(
            "Silinder warna " + warna +
            ", volume = " + hitungVolume()
        );
    }
}