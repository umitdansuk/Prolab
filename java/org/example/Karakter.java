package org.example;

import java.awt.*;

public class Karakter {

    private String ad ;
    private int boyutX;
    private int boyutY;
    private int id;
    private Point konum;



    public Karakter(Point konum) {
        this.ad = "maria";
        this.boyutX = 4;
        this.boyutY = 4;
        this.id = 123456;
        this.konum=konum;

    }

    public Point getKonum() {
        return konum;
    }

    public void setKonum(Point konum) {
        this.konum = konum;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public int getBoyutX() {
        return boyutX;
    }

    public void setBoyutX(int boyutX) {
        this.boyutX = boyutX;
    }

    public int getBoyutY() {
        return boyutY;
    }

    public void setBoyutY(int boyutY) {
        this.boyutY = boyutY;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
