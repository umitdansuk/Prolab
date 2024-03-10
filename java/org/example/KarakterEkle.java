package org.example;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class KarakterEkle {


    private Karakter karakter;

    private static KarakterEkle karakterEkle ;

    private byte [][] izgaraMatris ;


    private Izgara izgara ;

    private KarakterEkle () {
        izgara = Izgara.izgaraGetir(800,800,(byte)5) ;
        izgaraMatris = izgara.getIzgaraMatris() ;

    }
    public static KarakterEkle KarakterEkleGetir () {
        if ( karakterEkle== null) {
            karakterEkle = new KarakterEkle() ;
        }
        return karakterEkle ;
    }


    public Karakter karakterEkle() {
        if (karakter == null) {

            Point point = izgara.rastgeleKonum(4,4) ;
            karakter = new Karakter(point);
            izgaraMatrisGuncelle(karakter);

        }
        return  karakter;

    }



    private void izgaraMatrisGuncelle (Karakter karakter) {
        for (int i = karakter.getKonum().x; i <= karakter.getKonum().x + karakter.getBoyutX() * izgara.getKareGenisligi() + izgara.getKareGenisligi(); i+= izgara.getKareGenisligi()) {
            for (int j = karakter.getKonum().y; j <= karakter.getKonum().y + karakter.getBoyutY() * izgara.getKareGenisligi() + izgara.getKareGenisligi(); j+= izgara.getKareGenisligi()) {
                izgaraMatris[j/ izgara.getKareGenisligi()][i/ izgara.getKareGenisligi()] = 3 ;
            }
        }
    }

    public byte [][] getIzgaraMatris () {
        return izgaraMatris ;
    }


}
