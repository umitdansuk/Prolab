package org.example;

import java.awt.Point;
import java.util.*;

public class ShortestPathFinder {

    private Izgara izgara ;
    private byte [] [] izgaraMatris ;
    private Dugum[][] dugumler ;
    private List <Dugum> acikListe ;
    private List <Dugum> kapaliListe ;
    private Dugum baslangic ;
    private Dugum suankiDugum ;
    private Dugum hedefDugum ;
    private List <Point> patika ;
    boolean ulasildiMi;

    public ShortestPathFinder () {
        izgara = Izgara.izgaraGetir(0,0,(byte)0) ;
        izgaraMatris = izgara.getIzgaraMatris() ;
        dugumler = new Dugum[izgaraMatris.length] [izgaraMatris.length] ;
        acikListe = new LinkedList<>() ;
        kapaliListe = new LinkedList<>() ;
        patika = new ArrayList<>() ;
    }

    public List<Point> search(List<HazineSandigi> targets) {
        for (HazineSandigi target : targets) {
            configureNodeMatrix(target);
            while (!ulasildiMi) {
                int row = suankiDugum.getSatir();
                int column = suankiDugum.getSutun();
                suankiDugum.kapaliDugumYap();
                kapaliListe.add(suankiDugum);
                acikListe.remove(suankiDugum);
                if (isValidLocation(row - 1, column)) {
                    openNode(dugumler[row - 1][column]);
                }
                if (isValidLocation(row, column - 1)) {
                    openNode(dugumler[row][column - 1]);
                }
                if (isValidLocation(row + 1, column)) {
                    openNode(dugumler[row + 1][column]);
                }
                if (isValidLocation(row, column + 1)) {
                    openNode(dugumler[row][column + 1]);
                }
                int bestNodeIndex = 0;
                int bestNodeFCost = Integer.MAX_VALUE;

                for (int i = 0; i < acikListe.size(); i++) {
                    if (acikListe.get(i).getFMaliyeti() < bestNodeFCost) {
                        bestNodeIndex = i;
                        bestNodeFCost = acikListe.get(i).getFMaliyeti();
                    } else if (acikListe.get(i).getFMaliyeti() == bestNodeFCost && acikListe.get(i).gMaliyeti < acikListe.get(bestNodeIndex).gMaliyeti) {
                        bestNodeIndex = i;
                    }
                }

                if (acikListe.isEmpty()) {
                    continue;
                }

                suankiDugum = acikListe.get(bestNodeIndex);

                if (suankiDugum == hedefDugum) {
                    ulasildiMi = true;
                    List<Point> onePath = writePath();
                    patika.addAll(onePath);
                    changeStartNode();
                }
            }
            acikListe.clear();
            kapaliListe.clear();
            ulasildiMi = false;
        }
        return patika;
    }





    private Dugum[][] configureNodeMatrix(HazineSandigi target) {
        Karakter karakter = KarakterEkle.KarakterEkleGetir().karakterEkle();
        int targetRow = target.getKonum().y/ izgara.getKareGenisligi() ;
        int targetColumn = target.getKonum().x/ izgara.getKareGenisligi() ;
        int characterRow = karakter.getKonum().y / izgara.getKareGenisligi();
        int characterColumn = karakter.getKonum().x / izgara.getKareGenisligi();

        for (int i = 0; i < izgaraMatris.length; i++) {
            for (int j = 0; j < izgaraMatris.length; j++) {
                Dugum dugum = new Dugum(i, j);
                dugumler[i][j] = dugum;
                if (izgaraMatris[i][j] == 1) {
                    dugumler[i][j].engelMi = true;
                    dugumler[i][j].setHMaliyeti(Integer.MAX_VALUE);
                    dugumler[i][j].setFMaliyeti();
                } else if (izgaraMatris[i][j] == 2 && i == targetRow && j == targetColumn) {
                    dugumler[i][j].hazineMi = true;
                    dugumler[i][j].setGMaliyeti(Math.abs(i - characterRow) + Math.abs(j - characterColumn));
                    dugumler[i][j].setFMaliyeti();
                    hedefDugum = dugumler[i][j];
                } else if (izgaraMatris[i][j] == 2) {
                    dugumler[i][j].hazineMi = false;
                    dugumler[i][j].setGMaliyeti(Math.abs(i - characterRow) + Math.abs(j - characterColumn));
                    dugumler[i][j].setHMaliyeti(Math.abs(i - targetRow) + Math.abs(j - targetColumn));
                    dugumler[i][j].setFMaliyeti();
                } else if (izgaraMatris[i][j] == 3) {
                    suankiDugum = dugumler[i][j];
                    baslangic = dugumler[i][j];
                    suankiDugum.baslangic = true;
                    suankiDugum.setHMaliyeti(Math.abs(targetRow - i) + Math.abs(targetColumn - j));
                    suankiDugum.setFMaliyeti();
                } else {
                    dugumler[i][j].setGMaliyeti(Math.abs(i - characterRow) + Math.abs(j - characterColumn));
                    dugumler[i][j].setHMaliyeti(Math.abs(i - targetRow) + Math.abs(j - targetColumn));
                    dugumler[i][j].setFMaliyeti();
                }
            }
        }
        return dugumler;
    }




    private void changeStartNode() {
        for (int i = 0; i < izgaraMatris.length; i++) {
            for (int j = 0; j < izgaraMatris.length; j++) {
                if (izgaraMatris[i][j] == 3) {
                    izgaraMatris[i][j] = 0;
                    Dugum oldStartingDugum = dugumler[i][j];
                    oldStartingDugum.baslangic = false;
                    break;
                }
            }
        }
        izgaraMatris[suankiDugum.getSatir()][suankiDugum.getSutun()] = 3;

    }

    private boolean isValidLocation(int satir, int sutun) {
        return !isOutOfBoundArea(satir, sutun) && dugumler[satir][sutun].getFMaliyeti() != Integer.MAX_VALUE;
    }

    private boolean isOutOfBoundArea(int satir, int sutun) {
        return !(satir < izgara.getSatir() / izgara.getKareGenisligi() && sutun < izgara.getSutun() / izgara.getKareGenisligi() && satir >= 0 && sutun >= 0);
    }

    private void openNode(Dugum dugum) {

        if (dugum.acikMi == false && dugum.engelMi == false && dugum.kontrolEdildiMi == false) {
            dugum.setAcikMi(true);
            dugum.onceki = suankiDugum;
            acikListe.add(dugum);
        }
    }

    private List<Point> writePath() {
        Karakter karakter = KarakterEkle.KarakterEkleGetir().karakterEkle();
        List<Point> path = new ArrayList();
        Dugum currentDugumCopy = suankiDugum;
        while (currentDugumCopy != baslangic) {
            Point konum = new Point(currentDugumCopy.getSutun() * izgara.getKareGenisligi(), currentDugumCopy.getSatir() * izgara.getKareGenisligi());
            path.add(konum);
            currentDugumCopy = currentDugumCopy.getOnceki();
        }
        Collections.reverse(path);
        karakter.getKonum().setLocation(suankiDugum.getSatir(),suankiDugum.getSutun());
        return path;
    }

    public static class Dugum {

        private int satir;
        private int sutun;

        private int hMaliyeti;
        private int gMaliyeti;
        private int fMaliyeti;

        private boolean acikMi;
        private boolean kontrolEdildiMi;
        private boolean engelMi;
        private boolean hazineMi;
        private boolean baslangic;

        private Dugum onceki;

        public Dugum(int satir, int sutun) {
            this.satir = satir;
            this.sutun = sutun;
        }

        public int getSatir() {
            return satir;
        }

        public void setSatir(int satir) {
            this.satir = satir;
        }

        public int getSutun() {
            return sutun;
        }

        public void setSutun(int sutun) {
            this.sutun = sutun;
        }

        public int getHMaliyeti() {
            return hMaliyeti;
        }

        public void setHMaliyeti(int hCost) {
            this.hMaliyeti = hCost;
        }

        public int getGMaliyeti() {
            return gMaliyeti;
        }

        public void setGMaliyeti(int gCost) {
            this.gMaliyeti = gCost;
        }

        public int getFMaliyeti() {
            return fMaliyeti;
        }

        public void setFMaliyeti() {
            fMaliyeti = gMaliyeti + hMaliyeti;
        }

        public Dugum getOnceki() {
            return onceki;
        }

        public void setOnceki(Dugum onceki) {
            this.onceki = onceki;
        }

        public boolean isAcikMi() {
            return acikMi;
        }

        public void setAcikMi(boolean acikMi) {
            this.acikMi = acikMi;
        }

        public boolean isKontrolEdildiMi() {
            return kontrolEdildiMi;
        }

        public void kapaliDugumYap() {
            if (baslangic == false && hazineMi == false) {
                kontrolEdildiMi = true;
            }
        }

        public boolean isEngelMi() {
            return engelMi;
        }

        public void setEngelMi(boolean engelMi) {
            this.engelMi = engelMi;
        }

        public boolean isHazineMi() {
            return hazineMi;
        }

        public void setHazineMi(boolean hazineMi) {
            this.hazineMi = hazineMi;
        }

        public boolean isBaslangic() {
            return baslangic;
        }

        public void setBaslangic(boolean baslangic) {
            this.baslangic = baslangic;
        }


    }

}

