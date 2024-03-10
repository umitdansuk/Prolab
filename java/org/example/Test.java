package org.example;

import javax.swing.*;
import java.awt.*;

public class Test {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Izgara Test");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Izgara tablosunu oluştur
            Izgara izgara = Izgara.izgaraGetir(600, 600, (byte) 5); // Örnek olarak 10x10'luk bir ızgara oluşturuldu, her karenin boyutu 50x50 piksel
            frame.getContentPane().add(izgara, BorderLayout.CENTER) ;

            EngelEkle engelEkle = EngelEkle.engelEkleGetir() ;
            engelEkle.engelEkle();
            HazineEkle hazineEkle = HazineEkle.hazineEkleGetir();
            hazineEkle.hazineEkle();

            // JFrame'i boyutlandır ve görünür yap
            frame.pack();
            frame.setVisible(true);
        });
    }
}
