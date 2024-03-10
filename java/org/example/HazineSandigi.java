package org.example;

import java.awt.*;

public class HazineSandigi {

    private int boyutX;
    private int boyutY;
    private String hazineAdi;
    private Point konum;


    public HazineSandigi(int boyutX, int boyutY, String hazineAdi,Point konum) {
        this.boyutX = boyutX;
        this.boyutY = boyutY;
        this.hazineAdi = hazineAdi;
        this.konum = konum;
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

    public String getHazineAdi() {
        return hazineAdi;
    }

    public void setHazineAdi(String hazineAdi) {
        this.hazineAdi = hazineAdi;
    }
}
