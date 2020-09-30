package com.algeo.matrix;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Scanner;
import java.lang.Math;

public class BDInterpolasi {
    //attribute ArrayHasil
    BDMatrix ArrayHasil;
    String Persamaan;

    public BDMatrix HasilInterpolasi(BDMatrix Matriks) {
        int ukuran = Matriks.getRows();
        BDMatrix Hasil = new BDMatrix(ukuran, 1);
        for (int i = 0; i < ukuran; i++) {
            Hasil.setElmt(i, 0, Matriks.getElmt(i, ukuran));
        }
        return Hasil;
    }
    private void setArrayHasil(BDMatrix Hasil) {
        ArrayHasil = Hasil;
    }
    public BDMatrix getArrayHasil(){
        return ArrayHasil;
    }
    public String convertingToString(BDMatrix hasilInterpolasi) {
        int ukuran = hasilInterpolasi.getRows();
        int n = ukuran - 1;
        String output = "";
        for (int i = 0; i < ukuran; i++) {
            if (i == 0) {
                output += ("P" + n + "(x) = ");
            }
            if (hasilInterpolasi.getElmt(i, 0).compareTo(BigDecimal.ZERO) == 1) {
                output += ("" + hasilInterpolasi.getElmt(i, 0));
            }
            else {
                output += ("(" + hasilInterpolasi.getElmt(i, 0) + ")");
            }
            if (i != 0) {
                output += ("x^" + i);
            }
            if (i != (ukuran - 1)) {
                output += (" + ");
            }
            if (i == (ukuran - 1)) {
                output += ("%n");
            }
        }
        return output;
    }
    //cek mau keluarannya gaada apa jadi double[][] atau double[]
    public void interpolasi(BDMatrix MatriksTitik) {
        Scanner scan = new Scanner(System.in);
        //input n dari keyboard
        //int n = scan.nextInt();
        //int ukuran = (n + 1);
        int ukuran = MatriksTitik.getRows();
        //double[][] MatriksTitik = new double[ukuran][2];
        //input titik-titik dan dijadikan sebuah matriks
        /*for (int i = 0; i < ukuran; i++) {
            for (int j = 0; j < 2; j++) {
                MatriksTitik[i][j] = scan.nextDouble();
            }
        }*/
        int kolomInterpolasi = (ukuran + 1);
        //double[][] MatriksInterpolasi = new double[ukuran][kolomInterpolasi];
        BDMatrix MatriksInterpolasi = new BDMatrix(ukuran, kolomInterpolasi);
        //mengisi nilai y dari titik yang sudah diinput ke MatriksInterpolasi ke kolom terakhir
        //juga mengisi kolom pertama MatriksInterpolasi dengan 1
        /*for (int i = 0; i < ukuran; i++) {
            MatriksInterpolasi[i][kolomInterpolasi - 1] = MatriksTitik[i][1];
            MatriksInterpolasi[i][0] = 1;
        }*/
        for (int i = 0; i < ukuran; i++) {
            MatriksInterpolasi.setElmt(i, kolomInterpolasi - 1, MatriksTitik.getElmt(i, 1));
            MatriksInterpolasi.setElmt(i, 0, BigDecimal.ONE);
        }
        //mengisi nilai a0 xn^0 sampai dengan an xn^n ke MatriksInterpolasi (cek batas ukuran udah bener apa belom)
        /*for (int i = 0; i < ukuran; i++) {
            //elemen x pada matriks titik yang telah dibuat
            double toBePow = MatriksTitik[i][0];
            for (int j = 1; j < ukuran; j++) {
                MatriksInterpolasi[i][j] = Math.pow(toBePow, j);
            }
        }*/
        for (int i = 0; i < ukuran; i++) {
            //elemen x pada matriks titik yang telah dibuat
            BigDecimal toBePow = MatriksTitik.getElmt(i, 0);
            for (int j = 1; j < ukuran; j++) {
                MatriksInterpolasi.setElmt(i, j, toBePow.pow(j));
            }
        }
        //ini dari hitungDeterminanOBE, cek lagi!!
        //SPL Gauss-Jordan sampai bentuk matriks reduced echelom form
        BigDecimal temp;
        for (int j = 0; j < (ukuran - 1); j++) {
            //algoritma sorting untuk kolom dari terbesar ke terkecil
            for (int sorting = j; sorting < (ukuran - 1); sorting++) {
                for (int sorting2 = (sorting + 1); sorting2 < ukuran; sorting2++) {
                    if ((MatriksInterpolasi.getElmt(sorting, j).compareTo(MatriksInterpolasi.getElmt(sorting2, j)) == -1)) {
                        for (int kolom_sorting = 0; kolom_sorting < kolomInterpolasi; kolom_sorting++) {
                            temp = MatriksInterpolasi.getElmt(sorting, kolom_sorting);
                            MatriksInterpolasi.setElmt(sorting, kolom_sorting, MatriksInterpolasi.getElmt(sorting2, kolom_sorting));
                            MatriksInterpolasi.setElmt(sorting2, kolom_sorting, temp);
                        }
                    }
                }
            }
            for (int i = (j + 1); i < ukuran; i++) {
                BigDecimal numeratorRatio = MatriksInterpolasi.getElmt(i, j);
                BigDecimal denominatorRatio = MatriksInterpolasi.getElmt(i, j);
                for (int kolom_kurang = 0; kolom_kurang < kolomInterpolasi; kolom_kurang++) {
                    MatriksInterpolasi.setElmt(i, kolom_kurang, MatriksInterpolasi.getElmt(i, kolom_kurang).subtract((numeratorRatio.multiply(MatriksInterpolasi.getElmt(j, kolom_kurang).divide(denominatorRatio, MathContext.DECIMAL32)))));
                }
            }
        }
        //pengurangan mulai dari kolom paling akhir
        //cek bener apa engga
        for (int j = ukuran - 1; j > 0; j--) {
            for (int i = (j - 1); i >= 0; i--) {
                BigDecimal numeratorRatio = MatriksInterpolasi.getElmt(i, j);
                BigDecimal denominatorRatio = MatriksInterpolasi.getElmt(j, j);
                for (int kolom_kurang = 0; kolom_kurang < kolomInterpolasi; kolom_kurang++) {
                    MatriksInterpolasi.setElmt(i, kolom_kurang, MatriksInterpolasi.getElmt(i, kolom_kurang).subtract((numeratorRatio.multiply(MatriksInterpolasi.getElmt(j, kolom_kurang).divide(denominatorRatio, MathContext.DECIMAL32)))));
                }
            }
        }
        for (int division = 0; division < ukuran; division++) {
            BigDecimal divider = MatriksInterpolasi.getElmt(division, division);
            MatriksInterpolasi.setElmt(division, kolomInterpolasi - 1, MatriksInterpolasi.getElmt(division, kolomInterpolasi - 1).divide(divider, MathContext.DECIMAL32));
            MatriksInterpolasi.setElmt(division, division, MatriksInterpolasi.getElmt(division, division).divide(divider, MathContext.DECIMAL32));
        }
        //harusnya abis ini reduced echelon form bentuknya
        //meng-ekstrak value a0 - an dari MatriksInterpolasi
        /*double[] Hasil = new double[ukuran];
        for (int i = 0; i < ukuran; i++) {
            Hasil[i] = (Math.round(MatriksInterpolasi[i][kolomInterpolasi - 1] * 10000000.0) / 10000000.0);
        }*/
        setArrayHasil(HasilInterpolasi(MatriksInterpolasi)); //mark
        /*double x = scan.nextDouble();
        double y = Hasil[0];
        for (int i = 1; i < ukuran; i++) {
            y = y + (Hasil[i] * Math.pow(x, i));
        }
        System.out.println(y);*/
    }
    public void interpolate() {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        BDMatrix MatriksTitik = makeMatriksTitik(n);
        int ukuran = MatriksTitik.getRows();
        int kolomInterpolasi = (ukuran + 1);
        BDMatrix MatriksInterpolasi = new BDMatrix(ukuran, kolomInterpolasi);
        for (int i = 0; i < ukuran; i++) {
            MatriksInterpolasi.setElmt(i, kolomInterpolasi - 1, MatriksTitik.getElmt(i, 1));
            MatriksInterpolasi.setElmt(i, 0, BigDecimal.ONE);
        }
        for (int i = 0; i < ukuran; i++) {
            //elemen x pada matriks titik yang telah dibuat
            BigDecimal toBePow = MatriksTitik.getElmt(i, 0);
            for (int j = 1; j < ukuran; j++) {
                MatriksInterpolasi.setElmt(i, j, toBePow.pow(j));
            }
        }
        MatriksInterpolasi.reducedEchelon();
        setArrayHasil(HasilInterpolasi(MatriksInterpolasi));
        String OUT = convertingToString(ArrayHasil);
        System.out.println(OUT);//check
        Persamaan = OUT;
    }
    public BDMatrix makeMatriksTitik(int n) {
        BDMatrix matriksTitik = new BDMatrix(n + 1, 2);
        matriksTitik.readUserMatrix();
        return matriksTitik;
    }
}