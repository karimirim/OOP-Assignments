package tugas4;

public class bentuk {

    public String warna;

    public bentuk(String warna) {
        this.warna = warna;
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }

    public void printInfo() {
        System.out.println("Bentuk berwarna " + warna);
    }
}