package com.algeo.matrix;

public class Determinan {
    public void simpleMessage(String name) {
        System.out.println("Method Determinan by " + name);
    }
    public float hitungDeterminanEK(float[][] Matriks, int ukuran) {
        float determinan = 0;
        int sign;
        if (ukuran == 1) {
            determinan = Matriks[0][0];
        }
        else if (ukuran == 2) {
            determinan = (Matriks[0][0] * Matriks[1][1]) - (Matriks[0][1] * Matriks[1][0]);
        }
        else {
            float[][] MatriksMinor = new float[ukuran - 1][ukuran - 1];
            for (int kol = 0; kol < ukuran; kol++) {
                if (Matriks[0][kol] == 0) {
                    continue;
                }
                for (int i = 1; i < ukuran; i++) {
                    int kolMinor = 0;
                    for (int j = 0; j < ukuran; j++) {
                        if (j != kol) {
                            MatriksMinor[i - 1][kolMinor] = Matriks[i][j];
                        }
                        kolMinor = kolMinor + 1;
                    }
                }
                if (kol % 2 == 0) {
                    sign = 1;
                } else {
                    sign = -1;
                }
                determinan = determinan + sign * Matriks[0][kol] * hitungDeterminanEK(MatriksMinor, (ukuran - 1));
            }
        }
        return determinan;
    }
    public float hitungDeterminanOBE(float [][] Matriks, int ukuran) {
        int count_swap = 0;
        int sign = 0;
        float temp;
        //algoritma membuat matriks menjadi segitiga atas
        for (int j = 0; j < ukuran; j++) {
            //algoritma sorting untuk kolom dari terbesar ke terkecil
            for (int sorting = j; sorting < (ukuran - 1); sorting++) {
                if (j != (ukuran - 1)) {
                    for (int sorting2 = (sorting + 1); sorting2 < ukuran; sorting2++) { //bisa keluar dari range nanti liat lagi algoritmanya, masukin ke if (j!= ukuran-1) nanti
                        if (Matriks[sorting][j] < Matriks[sorting2][j]) {
                            for (int kolom_sorting = 0; kolom_sorting < ukuran; kolom_sorting++) {
                                temp = Matriks[sorting][kolom_sorting];
                                Matriks[sorting][kolom_sorting] = Matriks[sorting2][kolom_sorting];
                                Matriks[sorting2][kolom_sorting] = temp;
                            }
                /*for (int sorting2 = (sorting + 1); sorting2 < ukuran; sorting2++) { //bisa keluar dari range nanti liat lagi algoritmanya, masukin ke if (j!= ukuran-1) nanti
                    if (Matriks[sorting][j] < Matriks[sorting2][j]) {
                        for (int kolom_sorting = 0; kolom_sorting < ukuran; kolom_sorting++) {
                            temp = Matriks[sorting][kolom_sorting];
                            Matriks[sorting][kolom_sorting] = Matriks[sorting2][kolom_sorting];
                            Matriks[sorting2][kolom_sorting] = temp;*/ //algo tadi (cek nanti ini bener apa engga ditempatinnya)
                            count_swap = count_swap + 1;
                        }
                    }
                }

            }
            for (int i = j; i < ukuran; i++) {
                if (i != j) {
                    float ratio = (Matriks[i][j] / Matriks[j][j]);
                    for (int kolom_kurang = 0; kolom_kurang < ukuran; kolom_kurang++) {
                        Matriks[i][kolom_kurang] = Matriks[i][kolom_kurang] - (ratio * Matriks[j][j]);
                    }
                }
            }
        }
        float determinan = 1;
        if (count_swap % 2 == 0) {
            sign = 1;
        }
        else {
            sign = -1;
        }
        for (int k = 0; k < ukuran; k++) {
            determinan = determinan * Matriks[k][k];
            determinan = determinan * sign;
        }
        return determinan;
    }
}
