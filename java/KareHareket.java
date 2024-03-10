import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KareHareket extends JFrame implements ActionListener {
    private JPanel panel;
    private Timer timer;
    private int kareX = 50; // Kare başlangıç x koordinatı
    private int kareY = 50; // Kare başlangıç y koordinatı
    private int hareketYonu = 1; // Hareket yönü: 1 -> İleri, -1 -> Geri
    private final int hareketMesafesi = 3; // Kare her adımda kaç piksel ileri/geri gidecek

    public KareHareket() {
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLUE);
                g.fillRect(kareX, kareY, 50, 50); // Kare boyutu: 50x50
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(400, 400); // Panel boyutu
            }
        };

        timer = new Timer(50, this); // Timer ayarlanması (milisaniye cinsinden)

        add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        timer.start(); // Timer başlatılıyor
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        kareX += hareketYonu * hareketMesafesi; // Hareket mesafesi kadar ileri/geri hareket
        if (kareX <= 0 || kareX >= panel.getWidth() - 50) { // Panelin sınırlarını kontrol etme
            hareketYonu *= -1; // Yönü değiştirme (Geriye gitme)
        }
        panel.repaint(); // Paneli yeniden çizme
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(KareHareket::new);
    }
}

