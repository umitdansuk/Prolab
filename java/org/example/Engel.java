package org.example;


import java.awt.Point;

public abstract class Engel {
    private int boyutX;
    private int boyutY;
    private String engelAdi;
    private Point konum;


    public Engel(int boyutX, int boyutY, String engelAdi , Point konum) {
        this.boyutX = boyutX;
        this.boyutY = boyutY;
        this.engelAdi = engelAdi;
        this.konum = konum ;

    }

    public Point getKonum() {
        return konum;
    }

    public void setKonum(Point konum) {
        this.konum = konum;
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

    public String getEngelAdi() {
        return engelAdi;
    }

    public void setEngelAdi(String engelAdi) {
        this.engelAdi = engelAdi;
    }


}
