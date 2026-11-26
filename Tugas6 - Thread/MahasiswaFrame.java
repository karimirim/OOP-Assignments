import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class MahasiswaFrame extends JFrame implements ActionListener {

    private JTextField textNim;
    private JTextField textNama;
    private JTextField textJurusan;

    private JButton btnSimpan;
    private JButton btnUbah;
    private JButton btnHapus;
    private JButton btnReset;

    private JTable tableMahasiswa;
    private DefaultTableModel tableModel;
    private JLabel labelStatus;

    private int selectedId = -1;
    private final MahasiswaDAO dao = new MahasiswaDAO();

    public MahasiswaFrame() {
        setTitle("Manajemen Data Mahasiswa (Thread & Database)");
        setSize(650, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();
        muatDataAsync(); // Muat data dari database saat aplikasi pertama kali dibuka
    }

    private void initUI() {
        // Panel Form Input (Atas)
        JPanel panelInput = new JPanel(new GridBagLayout());
        panelInput.setBorder(BorderFactory.createTitledBorder("Form Mahasiswa"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 8, 6, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Baris 1: NIM
        gbc.gridx = 0; gbc.gridy = 0;
        panelInput.add(new JLabel("NIM:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 1.0;
        textNim = new JTextField(20);
        panelInput.add(textNim, gbc);

        // Baris 2: Nama
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        panelInput.add(new JLabel("Nama:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; gbc.weightx = 1.0;
        textNama = new JTextField(20);
        panelInput.add(textNama, gbc);

        // Baris 3: Jurusan
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0;
        panelInput.add(new JLabel("Jurusan:"), gbc);
        gbc.gridx = 1; gbc.gridy = 2; gbc.weightx = 1.0;
        textJurusan = new JTextField(20);
        panelInput.add(textJurusan, gbc);

        // Baris 4: Tombol Aksi
        JPanel panelTombol = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 4));
        btnSimpan = new JButton("Simpan");
        btnUbah = new JButton("Ubah");
        btnHapus = new JButton("Hapus");
        btnReset = new JButton("Reset Form");

        btnSimpan.addActionListener(this);
        btnUbah.addActionListener(this);
        btnHapus.addActionListener(this);
        btnReset.addActionListener(this);

        panelTombol.add(btnSimpan);
        panelTombol.add(btnUbah);
        panelTombol.add(btnHapus);
        panelTombol.add(btnReset);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        panelInput.add(panelTombol, gbc);

        // Panel Tabel (Tengah)
        String[] kolom = {"ID", "NIM", "Nama", "Jurusan"};
        tableModel = new DefaultTableModel(kolom, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Cell tabel hanya dibaca (read-only)
            }
        };
        tableMahasiswa = new JTable(tableModel);
        tableMahasiswa.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableMahasiswa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pilihBarisTabel();
            }
        });

        JScrollPane scrollTable = new JScrollPane(tableMahasiswa);
        scrollTable.setBorder(BorderFactory.createTitledBorder("Daftar Mahasiswa di Database"));

        // Status Bar (Bawah)
        JPanel panelStatus = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 6));
        panelStatus.setBorder(BorderFactory.createEtchedBorder());
        labelStatus = new JLabel("Status: Siap");
        labelStatus.setFont(new Font("SansSerif", Font.BOLD, 12));
        panelStatus.add(labelStatus);

        // Gabungkan ke Frame Utama
        setLayout(new BorderLayout(8, 8));
        add(panelInput, BorderLayout.NORTH);
        add(scrollTable, BorderLayout.CENTER);
        add(panelStatus, BorderLayout.SOUTH);
    }

    private void pilihBarisTabel() {
        int selectedRow = tableMahasiswa.getSelectedRow();
        if (selectedRow >= 0) {
            selectedId = (int) tableModel.getValueAt(selectedRow, 0);
            textNim.setText(tableModel.getValueAt(selectedRow, 1).toString());
            textNama.setText(tableModel.getValueAt(selectedRow, 2).toString());
            textJurusan.setText(tableModel.getValueAt(selectedRow, 3).toString());
        }
    }

    private void resetForm() {
        selectedId = -1;
        textNim.setText("");
        textNama.setText("");
        textJurusan.setText("");
        tableMahasiswa.clearSelection();
    }

    private void setKomponenAktif(boolean aktif) {
        btnSimpan.setEnabled(aktif);
        btnUbah.setEnabled(aktif);
        btnHapus.setEnabled(aktif);
        btnReset.setEnabled(aktif);
        textNim.setEnabled(aktif);
        textNama.setEnabled(aktif);
        textJurusan.setEnabled(aktif);
        tableMahasiswa.setEnabled(aktif);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSimpan) {
            simpanDataAsync();
        } else if (e.getSource() == btnUbah) {
            ubahDataAsync();
        } else if (e.getSource() == btnHapus) {
            hapusDataAsync();
        } else if (e.getSource() == btnReset) {
            resetForm();
            labelStatus.setText("Status: Form direset");
        }
    }

    // Pattern: DB on a thread > invokeLater > UI/GUI
    private void muatDataAsync() {
        setKomponenAktif(false);
        labelStatus.setText("Status: Memuat data dari database (Loading...)...");

        new Thread(() -> {
            try {
                Thread.sleep(500); // Simulasi proses background agar transisi thread terlihat jelas
                List<Mahasiswa> list = dao.getAll();

                SwingUtilities.invokeLater(() -> {
                    tableModel.setRowCount(0);
                    for (Mahasiswa m : list) {
                        tableModel.addRow(new Object[]{m.getId(), m.getNim(), m.getNama(), m.getJurusan()});
                    }
                    labelStatus.setText("Status: Data berhasil dimuat (" + list.size() + " mahasiswa)");
                    setKomponenAktif(true);
                });
            } catch (Exception ex) {
                SwingUtilities.invokeLater(() -> {
                    labelStatus.setText("Status: Gagal memuat data!");
                    setKomponenAktif(true);
                    JOptionPane.showMessageDialog(MahasiswaFrame.this, "Error DB: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                });
            }
        }).start();
    }

    private void simpanDataAsync() {
        String nim = textNim.getText().trim();
        String nama = textNama.getText().trim();
        String jurusan = textJurusan.getText().trim();

        if (nim.isEmpty() || nama.isEmpty() || jurusan.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua field harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Mahasiswa m = new Mahasiswa(nim, nama, jurusan);

        // 1. Disable button & show label "Saving..."
        setKomponenAktif(false);
        labelStatus.setText("Status: Menyimpan data ke database (Saving...)...");

        // 2. DB operation on background thread
        new Thread(() -> {
            try {
                Thread.sleep(600); // Simulasi latensi background thread
                dao.insert(m);
                List<Mahasiswa> listTerbaru = dao.getAll();

                // 3. invokeLater to update UI safely
                SwingUtilities.invokeLater(() -> {
                    resetForm();
                    tableModel.setRowCount(0);
                    for (Mahasiswa item : listTerbaru) {
                        tableModel.addRow(new Object[]{item.getId(), item.getNim(), item.getNama(), item.getJurusan()});
                    }
                    labelStatus.setText("Status: Data berhasil disimpan ke database!");
                    setKomponenAktif(true);
                    JOptionPane.showMessageDialog(MahasiswaFrame.this, "Data berhasil disimpan!");
                });
            } catch (Exception ex) {
                SwingUtilities.invokeLater(() -> {
                    labelStatus.setText("Status: Gagal menyimpan data!");
                    setKomponenAktif(true);
                    JOptionPane.showMessageDialog(MahasiswaFrame.this, "Error simpan: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                });
            }
        }).start();
    }

    private void ubahDataAsync() {
        if (selectedId == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data mahasiswa dari tabel yang ingin diubah!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String nim = textNim.getText().trim();
        String nama = textNama.getText().trim();
        String jurusan = textJurusan.getText().trim();

        if (nim.isEmpty() || nama.isEmpty() || jurusan.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua field harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Mahasiswa m = new Mahasiswa(selectedId, nim, nama, jurusan);

        setKomponenAktif(false);
        labelStatus.setText("Status: Mengubah data di database (Updating...)...");

        new Thread(() -> {
            try {
                Thread.sleep(600);
                dao.update(m);
                List<Mahasiswa> listTerbaru = dao.getAll();

                SwingUtilities.invokeLater(() -> {
                    resetForm();
                    tableModel.setRowCount(0);
                    for (Mahasiswa item : listTerbaru) {
                        tableModel.addRow(new Object[]{item.getId(), item.getNim(), item.getNama(), item.getJurusan()});
                    }
                    labelStatus.setText("Status: Data berhasil diubah!");
                    setKomponenAktif(true);
                    JOptionPane.showMessageDialog(MahasiswaFrame.this, "Data berhasil diubah!");
                });
            } catch (Exception ex) {
                SwingUtilities.invokeLater(() -> {
                    labelStatus.setText("Status: Gagal mengubah data!");
                    setKomponenAktif(true);
                    JOptionPane.showMessageDialog(MahasiswaFrame.this, "Error ubah: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                });
            }
        }).start();
    }

    private void hapusDataAsync() {
        if (selectedId == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data mahasiswa dari tabel yang ingin dihapus!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int konfirmasi = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin menghapus data ini?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
        if (konfirmasi != JOptionPane.YES_OPTION) {
            return;
        }

        final int idHapus = selectedId;
        setKomponenAktif(false);
        labelStatus.setText("Status: Menghapus data dari database (Deleting...)...");

        new Thread(() -> {
            try {
                Thread.sleep(600);
                dao.delete(idHapus);
                List<Mahasiswa> listTerbaru = dao.getAll();

                SwingUtilities.invokeLater(() -> {
                    resetForm();
                    tableModel.setRowCount(0);
                    for (Mahasiswa item : listTerbaru) {
                        tableModel.addRow(new Object[]{item.getId(), item.getNim(), item.getNama(), item.getJurusan()});
                    }
                    labelStatus.setText("Status: Data berhasil dihapus!");
                    setKomponenAktif(true);
                    JOptionPane.showMessageDialog(MahasiswaFrame.this, "Data berhasil dihapus!");
                });
            } catch (Exception ex) {
                SwingUtilities.invokeLater(() -> {
                    labelStatus.setText("Status: Gagal menghapus data!");
                    setKomponenAktif(true);
                    JOptionPane.showMessageDialog(MahasiswaFrame.this, "Error hapus: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                });
            }
        }).start();
    }
}
