package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame{
    private JButton YeniHaritaOlusturButton;
    private JPanel panelMenu;
    private JButton oyunuBaşlatButton;

    public Menu(){

        add(panelMenu);
        setSize(400,400);
        setTitle("Menü");

        YeniHaritaOlusturButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String satirSütunStr = JOptionPane.showInputDialog(Menu.this, "Lütfen satırXsütun sayısını giriniz :");

                // Kullanıcı iptal ettiyse veya boş bir giriş yaptıysa işlemi sonlandır
                if (satirSütunStr == null || satirSütunStr.isEmpty())
                    return;

                // String'i integer'a dönüştürürken hata oluşabileceği için try-catch bloğu kullanıyoruz
                try {
                    int satirSütunInt = Integer.parseInt(satirSütunStr);

                    //Izgara izgara = new Izgara(satirSütunInt,satirSütunInt,(byte)5);
                    //add(izgara) ;
                    setVisible(true);
                    setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);
                } catch (NumberFormatException ex) {
                    // Giriş string'i sayıya dönüştürülemezse kullanıcıya hata mesajı göster
                    JOptionPane.showMessageDialog(Menu.this, "Geçersiz giriş! Lütfen bir sayı girin.");
                }
            }
        });
        oyunuBaşlatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }


}
