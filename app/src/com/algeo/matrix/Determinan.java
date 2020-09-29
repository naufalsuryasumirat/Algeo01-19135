package com.algeo.matrix;
import java.math.BigDecimal;

public class Determinan {
    public double hitungDeterminanEK(double[][] Matriks, int ukuran) {
        double determinan = 0;
        int sign;
        if (ukuran == 1) {
            determinan = Matriks[0][0];
        }
        else if (ukuran == 2) {
            determinan = (Matriks[0][0] * Matriks[1][1]) - (Matriks[0][1] * Matriks[1][0]);
        }
        else {
            double[][] MatriksMinor = new double[ukuran - 1][ukuran - 1];
            for (int kol = 0; kol < ukuran; kol++) {
                if (Matriks[0][kol] == 0) {
                    continue;
                }
                for (int i = 1; i < ukuran; i++) {
                    int kolMinor = 0;
                    for (int j = 0; j < ukuran; j++) {
                        if (j != kol) {
                            MatriksMinor[i - 1][kolMinor] = Matriks[i][j];
                            kolMinor = kolMinor + 1;
                        }
                    }
                }
                if (kol % 2 == 0) {
                    sign = 1;
                }
                else {
                    sign = -1;
                }
                determinan = determinan + sign * Matriks[0][kol] * hitungDeterminanEK(MatriksMinor, (ukuran - 1));
            }
        }
        return determinan;
    }
    public double hitungDeterminanOBE(double [][] Matriks, int ukuran) {
        int count_swap = 0;
        int sign = 0;
        double temp;
        //algoritma membuat matriks menjadi segitiga atas
        for (int j = 0; j < (ukuran - 1); j++) {
            //algoritma sorting untuk kolom dari terbesar ke terkecil
            for (int sorting = j; sorting < (ukuran - 1); sorting++) {
                for (int sorting2 = (sorting + 1); sorting2 < ukuran; sorting2++) {
                    if (Matriks[sorting][j] < Matriks[sorting2][j]) {
                        for (int kolom_sorting = 0; kolom_sorting < ukuran; kolom_sorting++) {
                            temp = Matriks[sorting][kolom_sorting];
                            Matriks[sorting][kolom_sorting] = Matriks[sorting2][kolom_sorting];
                            Matriks[sorting2][kolom_sorting] = temp;
                        }
                        count_swap = count_swap + 1;
                    }
                }
            }
            for (int i = (j + 1); i < ukuran; i++) {
                double ratio = (Matriks[i][j]) / Matriks[j][j];
                for (int kolom_kurang = 0; kolom_kurang < ukuran; kolom_kurang++) {
                    Matriks[i][kolom_kurang] = Matriks[i][kolom_kurang] - (ratio * Matriks[j][kolom_kurang]);
                }
            }
        }
        double determinan = 1;
        if (count_swap % 2 == 0) {
            sign = 1;
        }
        else {
            sign = -1;
        }
        for (int k = 0; k < ukuran; k++) {
            determinan = determinan * Matriks[k][k];
        }
        determinan = determinan * sign;
        return determinan;
    }
}