package com.algeo.matrix;
import java.math.BigDecimal;
import java.math.MathContext;

public class BDDeterminan {
    BigDecimal attributeDeterminan;
    BDMatrix attributeMatriks;

    public enum Metode {
        EK,
        OBE,
        NOTSET
    }

    Metode metode;

    public BDDeterminan(BDMatrix MatriksDeterminan, Metode metode) {
        attributeMatriks = MatriksDeterminan;
        if (metode == Metode.EK) {
            setDeterminan(hitungDeterminanEK(attributeMatriks, attributeMatriks.getRows()));
        }
        else if (metode == Metode.OBE) {
            setDeterminan(hitungDeterminanOBE(attributeMatriks, attributeMatriks.getRows()));
        }
    }

    public BDDeterminan(BDMatrix MatriksDeterminan) {
        attributeMatriks = MatriksDeterminan;
        Metode metode = Metode.NOTSET;
    }

    public BigDecimal getDeterminan() {
        return attributeDeterminan;
    }

    private void setDeterminan(BigDecimal det) {
        attributeDeterminan = det;
    }

    public BigDecimal hitungDeterminanEK(BDMatrix Matriks, int ukuran) {
        BigDecimal determinan = BigDecimal.ZERO;
        BigDecimal sign;
        if (ukuran == 1) {
            determinan = Matriks.getElmt(0, 0);
        }
        else if (ukuran == 2) {
            determinan = (Matriks.getElmt(0, 0).multiply(Matriks.getElmt(1, 1))).subtract(Matriks.getElmt(0, 1).multiply(Matriks.getElmt(1, 0)));
        }
        else {
            BDMatrix MatriksMinor = new BDMatrix(ukuran - 1);
            for (int kol = 0; kol < ukuran; kol++) {
                if (Matriks.getElmt(0, kol).compareTo(BigDecimal.ZERO) == 0) {
                    continue;
                }
                for (int i = 1; i < ukuran; i++) {
                    int kolMinor = 0;
                    for (int j = 0; j < ukuran; j++) {
                        if (j != kol) {
                            MatriksMinor.setElmt(i - 1, kolMinor, Matriks.getElmt(i, j));
                            kolMinor = kolMinor + 1;
                        }
                    }
                }
                if (kol % 2 == 0) {
                    sign = BigDecimal.valueOf(1.0);
                }
                else {
                    sign = BigDecimal.valueOf(-1.0);
                }
                determinan = determinan.add(sign.multiply((Matriks.getElmt(0, kol)).multiply(hitungDeterminanEK(MatriksMinor, ukuran - 1))));
            }
        }
        return determinan;
    }
    public BigDecimal hitungDeterminanOBE(BDMatrix Matriks, int ukuran) {
        //algoritma meng-copy matriks agar bentuk Matriks input tidak berubah
        BDMatrix MatriksOBE = new BDMatrix(ukuran);
        for (int baris = 0; baris < ukuran; baris++) {
            for (int kolom = 0; kolom < ukuran; kolom++) {
                MatriksOBE.setElmt(baris, kolom, Matriks.getElmt(baris, kolom));
            }
        }
        int count_swap = 0;
        BigDecimal sign;
        BigDecimal temp;
        //algoritma membuat matriks menjadi segitiga atas
        for (int j = 0; j < (ukuran - 1); j++) {
            //algoritma sorting untuk kolom dari terbesar ke terkecil
            for (int sorting = j; sorting < (ukuran - 1); sorting++) {
                for (int sorting2 = (sorting + 1); sorting2 < ukuran; sorting2++) {
                    if (MatriksOBE.getElmt(sorting, j).compareTo(MatriksOBE.getElmt(sorting2, j)) == -1) {
                        for (int kolom_sorting = 0; kolom_sorting < ukuran; kolom_sorting++) {
                            temp = MatriksOBE.getElmt(sorting, kolom_sorting);
                            MatriksOBE.setElmt(sorting, kolom_sorting, MatriksOBE.getElmt(sorting2, kolom_sorting));
                            MatriksOBE.setElmt(sorting2, kolom_sorting, temp);
                        }
                        count_swap = count_swap + 1;
                    }
                }
            }
            for (int i = (j + 1); i < ukuran; i++) {
                BigDecimal numeratorRatio = MatriksOBE.getElmt(i, j);
                BigDecimal denominatorRatio = MatriksOBE.getElmt(j, j);
                for (int kolom_kurang = 0; kolom_kurang < ukuran; kolom_kurang++) {
                    MatriksOBE.setElmt(i, kolom_kurang, MatriksOBE.getElmt(i, kolom_kurang).subtract(numeratorRatio.multiply(MatriksOBE.getElmt(j, kolom_kurang).divide(denominatorRatio, MathContext.DECIMAL32))));
                }
            }
        }
        BigDecimal determinan = BigDecimal.valueOf(1.0);
        if (count_swap % 2 == 0) {
            sign = BigDecimal.valueOf(1.0);
        }
        else {
            sign = BigDecimal.valueOf(-1.0);
        }
        for (int k = 0; k < ukuran; k++) {
            determinan = determinan.multiply(MatriksOBE.getElmt(k, k));
        }
        determinan = determinan.multiply(sign);
        //round hingga 5 angka di belakang koma
        return determinan;
    }
}