package tugas4;

public class lingkaran extends bentuk {

    private double radius;
    private static final double PHI = 3.141592653589793;

    public lingkaran(double radius, String warna) {
        super(warna);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double r) {
        this.radius = r;
    }

    public double hitungLuas() {
        return PHI * radius * radius;
    }

    @Override
    public void printInfo() {
        System.out.println(
            "Lingkaran " + warna +
            ", luas = " + hitungLuas()
        );
    }
}