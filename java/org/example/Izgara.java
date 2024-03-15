package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.border.LineBorder;
import java.util.List ;

public class Izgara extends JPanel  {

    private static Izgara izgara ;
    private static final int TIMER_DELAY = 500; // Her 500 milisaniyede bir hareket etsin
    private Random random  ;
    private byte kareGenisligi ;
    private Timer timer;
    private EngelEkle engelEkle;
    ArrayList<Point> kullanilanKonumlar = new ArrayList<>(); // Kullanılan konumları saklamak için liste
    private int satir;
    private int sutun;
    private byte [][] izgaraMatris ;

    List <Point> patika ;

    private Izgara(int satir, int sutun , byte kareGenisligi) {
        this.satir = satir;
        this.sutun=sutun;
        this.kareGenisligi = kareGenisligi ;
        random = new Random () ;
        izgaraMatris = new byte [satir][sutun] ;
        timer = new Timer(TIMER_DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hareketliEngelleriHareketEttir();
                repaint(); // Yeniden çizim yapılmasını sağlar
            }
        });
        timer.start(); // Timer'ı başlat

    }
    public void temizle() {
        repaint();
    }
    private void hareketliEngelleriHareketEttir() {
        EngelEkle engelEkle = EngelEkle.engelEkleGetir();
        for (Engel engel : engelEkle.engellerList) {
            if (engel instanceof Ari) {
                ((Ari) engel).hareketEt(); // Hareketli engelleri hareket ettir
            }
            else if (engel instanceof Kus) {
                ((Kus) engel).hareketEt();
            }
        }
    }


    public static Izgara izgaraGetir (int satir , int sutun , byte kareGenisligi) {
        if (izgara == null) {
            izgara = new Izgara(satir,sutun,kareGenisligi);
        }
        return izgara ;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        kareleriCiz(g);
        try {
            resimCiz(g);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        patikayiCiz(g);
    }

    private void kareleriCiz (Graphics g) {
        for (int i = 0 ; i <= satir ; i+=kareGenisligi) {
            for (int j = 0 ; j <= sutun ; j+= kareGenisligi) {
                g.drawRect(i,j,kareGenisligi,kareGenisligi);
            }
        }
    }

    private Image getImageForEngel(Engel engel) throws IOException {

        if (engel instanceof Agac)
            return  ImageIO.read(new File("C:\\Java intelij\\Prolabb2Proje1\\resim\\foto\\agac.png"));
        else if (engel instanceof Kaya)
            return  ImageIO.read(new File("C:\\Java intelij\\Prolabb2Proje1\\resim\\foto\\kayaa.jpg"));
        else if (engel instanceof Duvar)
            return ImageIO.read(new File("C:\\Java intelij\\Prolabb2Proje1\\resim\\foto\\duvar.jpg"));
        else if (engel instanceof Dag)
            return ImageIO.read(new File("C:\\Java intelij\\Prolabb2Proje1\\resim\\foto\\daaag.png"));
        else if (engel instanceof Ari)
            return  ImageIO.read(new File("C:\\Java intelij\\Prolabb2Proje1\\resim\\foto\\ariiii.jpg"));
        else if (engel instanceof Kus)
            return  ImageIO.read(new File("C:\\Java intelij\\Prolabb2Proje1\\resim\\foto\\kus.jpg"));

        else return null ;

    }

    private Image getImageForHazine(HazineSandigi hazineSandigi) throws IOException {

          if (hazineSandigi instanceof Altin)
            return ImageIO.read(new File("C:\\Java intelij\\Prolabb2Proje1\\resim\\foto\\altin.jpg"));
        else if (hazineSandigi instanceof Zümrüt)
            return ImageIO.read(new File("C:\\Java intelij\\Prolabb2Proje1\\resim\\foto\\zümrüt.jpg"));
        else if (hazineSandigi instanceof Gümüs)
            return  ImageIO.read(new File("C:\\Java intelij\\Prolabb2Proje1\\resim\\foto\\gümüs.jpg"));
        else if (hazineSandigi instanceof Bakir)
            return  ImageIO.read(new File("C:\\Java intelij\\Prolabb2Proje1\\resim\\foto\\bakir.jpg"));

        else return null ;

    }

    private Image getImageForKarakter(Karakter karakter) throws IOException {


            return ImageIO.read(new File("C:\\Java intelij\\Prolabb2Proje1\\resim\\foto\\mario.png"));

    }


    private void resimCiz(Graphics g) throws IOException {
        EngelEkle engelEkle = EngelEkle.engelEkleGetir();

        for (Engel engel : engelEkle.engellerList) {

            Image orjinalResim = getImageForEngel(engel);
            Image olcekliResim = resmiOlceklendirEngel(orjinalResim, engel);

            g.drawImage(olcekliResim, engel.getKonum().x, engel.getKonum().y, null);
        }

        HazineEkle hazineEkle = HazineEkle.hazineEkleGetir();

        for (HazineSandigi hazineSandigi : hazineEkle.hazineList) {
            Image orjinalResim = getImageForHazine(hazineSandigi);
            Image olcekliResim = resmiOlceklendirHazine(orjinalResim, hazineSandigi);
            g.drawImage(olcekliResim, hazineSandigi.getKonum().x, hazineSandigi.getKonum().y, null);
        }


        KarakterEkle karakterEkle = KarakterEkle.KarakterEkleGetir();
        Karakter karakter = karakterEkle.karakterEkle();

            Image orjinalResim = getImageForKarakter(karakter);
            Image olcekliResim = resmiOlceklendirKarakter(orjinalResim, karakter);

            g.drawImage(olcekliResim, karakter.getKonum().x, karakter.getKonum().y, null);



    }

    private Image resmiOlceklendirHazine(Image image, HazineSandigi hazineSandigi) {
        Image olcekliResim = image.getScaledInstance(hazineSandigi.getBoyutX() * kareGenisligi , hazineSandigi.getBoyutY() * kareGenisligi,Image.SCALE_SMOOTH);
        return olcekliResim;
    }


    private Image resmiOlceklendirEngel (Image image , Engel engel) {
        Image olcekliResim = image.getScaledInstance(engel.getBoyutX() * kareGenisligi , engel.getBoyutY() * kareGenisligi,Image.SCALE_SMOOTH);
        return olcekliResim ;
    }

    private Image resmiOlceklendirKarakter (Image image , Karakter karakter) {
        Image olcekliResim = image.getScaledInstance(karakter.getBoyutX() * kareGenisligi , karakter.getBoyutY() * kareGenisligi,Image.SCALE_SMOOTH);
        return olcekliResim ;
    }



    public Point rastgeleKonum(int boyutX, int boyutY) {
        Point konum ;

        int x = random.nextInt(sutun - boyutX * kareGenisligi + 1) ;
        int y = random.nextInt(satir - (boyutY * kareGenisligi) +1) ;

        while (!konumGecerliMi(x,y,boyutX,boyutY) || y % kareGenisligi != 0|| x % kareGenisligi != 0) {
            x = random.nextInt(sutun - boyutX * kareGenisligi + 1) ;
            y = random.nextInt(satir - (boyutY * kareGenisligi) +1) ;
        }

        konum = new Point(x,y) ;

        return konum;
    }

    private boolean konumGecerliMi (int x , int y , int boyutX , int boyutY) {
        for (int i = x ; i <= x + boyutX * kareGenisligi ; i+= kareGenisligi) {
            for (int j = y ; j <= y + boyutY * kareGenisligi ; j+=kareGenisligi) {
                if (izgaraMatris[j/kareGenisligi][i/kareGenisligi] != 0) {
                    return false ;
                }
            }
        }
        return true ;
    }


    public Point hareketliEngelIcınRastgeleKonum (int boyutX , int boyutY , boolean yukariAsagi , int hareketMiktari) {
        Point konum ;

        int x = random.nextInt(sutun * kareGenisligi - boyutX * kareGenisligi + 1) ;
        int y = random.nextInt(satir * kareGenisligi - boyutY * kareGenisligi + 1) ;

        while (!hareketliEngelIcinKonumGecerliMi(x,y,boyutX,boyutY,yukariAsagi,hareketMiktari) || y % kareGenisligi != 0|| x % kareGenisligi != 0) {
            x = random.nextInt(sutun - boyutX * kareGenisligi + 1) ;
            y = random.nextInt(satir - (boyutY * kareGenisligi) +1) ;
        }

        konum = new Point(x,y) ;

        return konum;
    }

    private boolean hareketliEngelIcinKonumGecerliMi(int x, int y, int boyutX, int boyutY, boolean yukariAsagi, int hareketMiktari) {

        engelEkle = EngelEkle.engelEkleGetir() ;

        // Engelin x ve y koordinatlarını kontrol et
        if (x < 0 || y < 0 || x + boyutX * kareGenisligi > sutun * kareGenisligi || y + boyutY * kareGenisligi > satir * kareGenisligi)
            return false;

        byte[][] izgaraMatris = engelEkle.getIzgaraMatris();
        if (yukariAsagi) {
            int baslangicY = y - hareketMiktari * kareGenisligi;
            int sonY = y + hareketMiktari * kareGenisligi + boyutY * kareGenisligi;
            if (sonY > satir * kareGenisligi || baslangicY < 0) return false;
            for (int i = x / kareGenisligi; i <= (x + boyutX * kareGenisligi) / kareGenisligi; i += kareGenisligi) {
                for (int j = baslangicY / kareGenisligi; j <= sonY / kareGenisligi; j += kareGenisligi) {
                    if (izgaraMatris[j][i] != 0) {
                        return false;
                    }
                }
            }
        } else {
            int baslangicX = x - hareketMiktari * kareGenisligi;
            int sonX = x + hareketMiktari * kareGenisligi + boyutX * kareGenisligi;
            if (sonX > sutun * kareGenisligi || baslangicX < 0) return false;
            for (int i = baslangicX / kareGenisligi; i <= sonX / kareGenisligi; i += kareGenisligi) {
                for (int j = y / kareGenisligi; j <= (y + boyutY * kareGenisligi) / kareGenisligi; j += kareGenisligi) {
                    if (izgaraMatris[j][i] != 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void patikayiCiz (Graphics graphics) {
        if (patika != null) {
            for (Point lokasyon : patika) {
                graphics.fillRect(lokasyon.x, lokasyon.y, kareGenisligi,kareGenisligi);
            }
        }
    }


    public ArrayList<Point> getKullanilanKonumlar() {
        return kullanilanKonumlar;
    }


    public int getSutun() {
        return sutun;
    }

    public int getSatir() {
        return satir;
    }

    public void setPatika (List<Point> patika) {
        this.patika = patika ;
    }

    public int getBirimSatir () {
        return satir / kareGenisligi ;
    }

    public int getBirimSutun () {
        return sutun / kareGenisligi ;
    }

    public byte getKareGenisligi () {
        return kareGenisligi ;
    }

    public byte[][] getIzgaraMatris() {
        return izgaraMatris;
    }
}