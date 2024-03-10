package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test {

    private JFrame frame;
    private Izgara izgara;

    public Test() {

        frame = new JFrame("Izgara Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        izgara = Izgara.izgaraGetir(800, 800, (byte) 5);
        frame.getContentPane().add(izgara, BorderLayout.CENTER);

        JButton yeniHaritaButton = new JButton("Yeni Harita Oluştur");
        yeniHaritaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EngelEkle engelEkle;
                HazineEkle hazineEkle;
                izgara.temizle();
                engelEkle = EngelEkle.engelEkleGetir();
                engelEkle.engelEkle();

                hazineEkle = HazineEkle.hazineEkleGetir();
                hazineEkle.hazineEkle();
                izgara.repaint(); // Yeniden çizmek için repaint çağrısı
            }
        });

        JButton oyunBaslatButton = new JButton("Oyun Başlat");
        oyunBaslatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Oyun başlatma işlemleri buraya gelecek
                // Şu anda bu butona basıldığında yapılacak bir işlem yok
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(yeniHaritaButton);
        buttonPanel.add(oyunBaslatButton);

        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Test();
        });
    }
}
