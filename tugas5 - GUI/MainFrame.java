import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements ActionListener {
    
    private JLabel labelNama;
    private JTextField textNama;
    private JButton btnSubmit;
    private JLabel labelHasil;

    public MainFrame() {
        setTitle("Form Input Sederhana");
        setSize(360, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        inisialisasiKomponen();
    }

    private void inisialisasiKomponen() {
        labelNama = new JLabel("Masukkan Nama:");
        textNama = new JTextField(15);
        btnSubmit = new JButton("Kirim");
        labelHasil = new JLabel("Halo, ...");

        btnSubmit.addActionListener(this);

        JPanel panelForm = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelForm.add(labelNama);
        panelForm.add(textNama);

        JPanel panelAksi = new JPanel(new GridLayout(2, 1, 5, 5));
        
        JPanel panelTombol = new JPanel(new FlowLayout());
        panelTombol.add(btnSubmit);

        labelHasil.setHorizontalAlignment(SwingConstants.CENTER);

        panelAksi.add(panelTombol);
        panelAksi.add(labelHasil);

        setLayout(new BorderLayout(10, 10));
        add(panelForm, BorderLayout.NORTH);
        add(panelAksi, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSubmit) {
            String input = textNama.getText().trim();
            if (input.isEmpty()) {
                labelHasil.setText("Nama tidak boleh kosong!");
            } else {
                labelHasil.setText("Halo, " + input + "! Selamat belajar PBO.");
            }
        }
    }
}
