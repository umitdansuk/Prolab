package org.example;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class EngelEkle {

    private static EngelEkle engelEkle ;

    private byte [][] izgaraMatris ;


    Random random = new Random();
    private int MaxEngelSayisiHareketsiz = 20;
    private int MaxEngelSayisiHareketli = 3;

    ArrayList<Engel> engellerList = new ArrayList<>();

    private Izgara izgara ;

    private EngelEkle () {
        izgara = Izgara.izgaraGetir(800,800,(byte)5) ;
        izgaraMatris = izgara.getIzgaraMatris() ;

    }

    public static EngelEkle engelEkleGetir () {
        if (engelEkle == null) {
            engelEkle = new EngelEkle() ;
        }
        return engelEkle ;
    }


    public void engelEkle() {
        Engel engell = null;

        // Her bir engel türünden en az 2 engel olacak şekilde oluştur
        for (int j = 0; j < 2 ; j++) {
            engellerList.add(RastgeleAgac());
            engellerList.add(RastgeleDag());
            engellerList.add(RastgeleDuvar());
            engellerList.add(RastgeleKaya());
        }

        // Geri kalan engelleri rastgele oluştur
        for (int j = 0; j < MaxEngelSayisiHareketsiz - 8; j++) {
            int rastgeleEngelTur = random.nextInt(4); // 4 farklı engel türü için rastgele bir sayı üret
            switch (rastgeleEngelTur){
                case 0:
                    engell = RastgeleAgac();
                    break;
                case 1:
                    engell = RastgeleDag();
                    break;
                case 2:
                    engell = RastgeleDuvar();
                    break;
                case 3:
                    engell = RastgeleKaya();
                    break;
            }
            engellerList.add(engell);
        }

        // Hareketli engelleri ekleyelim
        for (int i = 0; i < MaxEngelSayisiHareketli; i++) {
            int rastgeleEngelTur = random.nextInt(2); // 2 farklı hareketli engel türü için rastgele bir sayı üret
            switch (rastgeleEngelTur){
                case 0:
                    engell = RastgeleAri();
                    break;
                case 1:
                    engell = RastgeleKus() ;
                    break;
                default:
                    engell = RastgeleAri();
                    break;
            }
            engellerList.add(engell);
        }
    }


    private Ari RastgeleAri(){

        Point point = izgara.hareketliEngelIcınRastgeleKonum(2,2,false,3) ;
        Ari ari = new Ari(point);
        hareketliEngelIcınMatrisGuncelle(ari,false);
        return ari ;
    }

    private Kus RastgeleKus(){

        Point point = izgara.hareketliEngelIcınRastgeleKonum(2,2,true,5) ;
        Kus kus = new Kus(point);
        hareketliEngelIcınMatrisGuncelle(kus,true);
        return kus;
    }

    private Agac RastgeleAgac() {
        int rastgeleX =2+ random.nextInt(3);
        Point point = izgara.rastgeleKonum(rastgeleX,rastgeleX) ;
        Agac agac = new Agac(rastgeleX,rastgeleX,point);
        izgaraMatrisGuncelle(agac);
        return agac ;
    }

    private Kaya RastgeleKaya() {
        int rastgeleX =2+ random.nextInt(2);
        Point point = izgara.rastgeleKonum(rastgeleX,rastgeleX) ;
        Kaya kaya=new  Kaya(rastgeleX,rastgeleX,point);
        izgaraMatrisGuncelle(kaya);
        return kaya;

    }
    private Dag RastgeleDag(){

        Point point = izgara.rastgeleKonum(15,15) ;
        Dag dag = new Dag(15,15 , point);
        izgaraMatrisGuncelle(dag);
        return dag;

    }

    private Duvar RastgeleDuvar(){
        Point point = izgara.rastgeleKonum(10,1) ;
        Duvar duvar = new Duvar(10,1,point);
        izgaraMatrisGuncelle(duvar);
        return duvar;
    }

    public void yazdirEngellerinIsimlerini() {
        for (Engel engel : engellerList) {
            System.out.println(engel.getClass().getSimpleName()+" : ("+engel.getBoyutX()+","+engel.getBoyutY()+")");
        }
    }


    private void izgaraMatrisGuncelle (Engel engel) {
        for (int i = engel.getKonum().x; i <= engel.getKonum().x + engel.getBoyutX() * izgara.getKareGenisligi() + izgara.getKareGenisligi(); i+= izgara.getKareGenisligi()) {
            for (int j = engel.getKonum().y; j <= engel.getKonum().y + engel.getBoyutY() * izgara.getKareGenisligi() + izgara.getKareGenisligi(); j+= izgara.getKareGenisligi()) {
                izgaraMatris[j/ izgara.getKareGenisligi()][i/ izgara.getKareGenisligi()] = 1 ;
            }
        }
    }

    private void hareketliEngelIcınMatrisGuncelle (HareketliEngel engel , boolean yukariAsagi) {
        if(!yukariAsagi) {
            int baslangicX = engel.getKonum().x - engel.getSagHareketMiktari() * izgara.getKareGenisligi() ;
            int sonX = engel.getKonum().x + engel.getSagHareketMiktari() * izgara.getKareGenisligi() + engel.getBoyutX() * izgara.getKareGenisligi() ;
            for (int i = baslangicX / izgara.getKareGenisligi() ; i <= sonX / izgara.getKareGenisligi() + 1 ; i+= izgara.getKareGenisligi()) {
                for (int j = engel.getKonum().y / izgara.getKareGenisligi(); j <= (engel.getKonum().y + engel.getBoyutY() * izgara.getKareGenisligi()) / izgara.getKareGenisligi() + 1; j+= izgara.getKareGenisligi()) {
                    izgaraMatris[j][i] = 1 ;
                }
            }
        }
        else {
            int baslangicY = engel.getKonum().y - engel.getAsagiHareketMiktari() * izgara.getKareGenisligi() ;
            int sonY = engel.getKonum().y + engel.getAsagiHareketMiktari() * izgara.getKareGenisligi() + engel.getBoyutY() * izgara.getKareGenisligi() ;
            for (int i = engel.getKonum().x / izgara.getKareGenisligi() ; i <= (engel.getKonum().x + engel.getBoyutX() * izgara.getKareGenisligi())  / izgara.getKareGenisligi() + 1 ; i+= izgara.getKareGenisligi()) {
                for (int j = baslangicY / izgara.getKareGenisligi(); j <= sonY / izgara.getKareGenisligi() + 1; j+= izgara.getKareGenisligi()) {
                    izgaraMatris[j][i] = 1 ;
                }
            }
        }
    }

    public byte [][] getIzgaraMatris () {
        return izgaraMatris ;
    }


}
