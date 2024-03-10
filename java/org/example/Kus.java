package org.example;

import java.awt.*;

public class Kus extends HareketliEngel {
    private boolean yukariYon;
    private Point previousPosition;

    public Kus(Point point) {
        super(2, 2, "Kuş", 0, 0, 5, 5, point);
        previousPosition = point;
    }

    @Override
    public void hareketEt() {
        int yeniY = getKonum().y;
        Izgara izgara = Izgara.izgaraGetir(0, 0, (byte) 0);
        if (super.isMerkez()) {
            yeniY -= (getYukariHareketMiktari() * izgara.getKareGenisligi());
            if (yeniY >= 0) {
                setKonum(new Point(getKonum().x, yeniY));
                yukariYon = false;
                super.setMerkez(false);
            }
        }
        if (yukariYon) {
            yeniY -= 2 * (getYukariHareketMiktari() * izgara.getKareGenisligi());
            if (yeniY >= 0) {
                setKonum(new Point(getKonum().x, yeniY));
                yukariYon = false;
            }
        } else {
            yeniY += 2 * getAsagiHareketMiktari() * izgara.getKareGenisligi();
            if (yeniY + izgara.getKareGenisligi() * getBoyutY() <= izgara.getHeight()) {
                setKonum(new Point(getKonum().x, yeniY));
                yukariYon = true;
            }
        }
        // Draw red line from previous position to current position
        Graphics g = izgara.getGraphics();
        g.setColor(Color.RED);
        g.drawLine(previousPosition.x + getBoyutX() * izgara.getKareGenisligi() / 2, previousPosition.y, getKonum().x + getBoyutX() * izgara.getKareGenisligi() /2, getKonum().y);
        previousPosition = getKonum();
    }
}
