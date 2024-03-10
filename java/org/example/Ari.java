package org.example;

import java.awt.*;

public class Ari extends HareketliEngel {
    private boolean solYon;
    private Point previousPosition;

    public Ari(Point point) {
        super(2, 2, "Arı", 3, 3, 0, 0, point);
        previousPosition = point;
    }

    @Override
    public void hareketEt() {
        int yeniX = getKonum().x;
        Izgara izgara = Izgara.izgaraGetir(0, 0, (byte) 0);
        if (super.isMerkez()) {
            yeniX -= (getSolHareketMiktari() * izgara.getKareGenisligi());
            if (yeniX >= 0) {
                setKonum(new Point(yeniX, getKonum().y));
                super.setMerkez(false);
                solYon = true;
            }
        }
        if (solYon) {
            yeniX += 2 * (getSagHareketMiktari() * izgara.getKareGenisligi());
            if (yeniX + getBoyutX() * izgara.getKareGenisligi() <= izgara.getWidth()) {
                setKonum(new Point(yeniX, getKonum().y));
                solYon = false;
            }
        } else {
            yeniX -= 2 * getSolHareketMiktari() * izgara.getKareGenisligi();
            if (yeniX >= 0) {
                setKonum(new Point(yeniX, getKonum().y));
                solYon = true;
            }
        }
        // Draw red line from previous position to current position
        Graphics g = izgara.getGraphics();
        g.setColor(Color.RED);
        g.drawLine(previousPosition.x, previousPosition.y + getBoyutY() * izgara.getKareGenisligi() / 2, getKonum().x, getKonum().y+ getBoyutY() * izgara.getKareGenisligi() / 2);
        previousPosition = getKonum();
    }
}