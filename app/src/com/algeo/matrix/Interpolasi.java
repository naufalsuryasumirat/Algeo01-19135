package com.algeo.matrix;

import java.util.Scanner;
import java.lang.Math;

public class Interpolasi {
    //cek mau keluarannya gaada apa jadi double[][] atau double[]
    public void interpolasi() {
        Scanner scan = new Scanner(System.in);
        //input n dari keyboard
        int n = scan.nextInt();
        int ukuran = (n + 1);
        double[][] MatriksTitik = new double[ukuran][2];
        //input titik-titik dan dijadikan sebuah matriks
        for (int i = 0; i < ukuran; i++) {
            for (int j = 0; j < 2; j++) {
                MatriksTitik[i][j] = scan.nextDouble();
            }
        }
        int kolomInterpolasi = (ukuran + 1);
        double[][] MatriksInterpolasi = new double[ukuran][kolomInterpolasi];
        //mengisi nilai y dari titik yang sudah diinput ke MatriksInterpolasi ke kolom terakhir
        //juga mengisi kolom pertama MatriksInterpolasi dengan 1
        for (int i = 0; i < ukuran; i++) {
            MatriksInterpolasi[i][kolomInterpolasi - 1] = MatriksTitik[i][1];
            MatriksInterpolasi[i][0] = 1;
        }
        //mengisi nilai a0 xn^0 sampai dengan an xn^n ke MatriksInterpolasi (cek batas ukuran udah bener apa belom)
        for (int i = 0; i < ukuran; i++) {
            //elemen x pada matriks titik yang telah dibuat
            double toBePow = MatriksTitik[i][0];
            for (int j = 1; j < ukuran; j++) {
                MatriksInterpolasi[i][j] = Math.pow(toBePow, j);
            }
        }
        //ini dari hitungDeterminanOBE, cek lagi!!
        //SPL Gauss-Jordan sampai bentuk matriks reduced echelom form
        double temp;
        for (int j = 0; j < (ukuran - 1); j++) {
            //algoritma sorting untuk kolom dari terbesar ke terkecil
            for (int sorting = j; sorting < (ukuran - 1); sorting++) {
                for (int sorting2 = (sorting + 1); sorting2 < ukuran; sorting2++) {
                    if (MatriksInterpolasi[sorting][j] < MatriksInterpolasi[sorting2][j]) {
                        for (int kolom_sorting = 0; kolom_sorting < kolomInterpolasi; kolom_sorting++) {
                            temp = MatriksInterpolasi[sorting][kolom_sorting];
                            MatriksInterpolasi[sorting][kolom_sorting] = MatriksInterpolasi[sorting2][kolom_sorting];
                            MatriksInterpolasi[sorting2][kolom_sorting] = temp;
                        }
                    }
                }
            }
            for (int i = (j + 1); i < ukuran; i++) {
                double numeratorRatio = MatriksInterpolasi[i][j];
                double denominatorRatio = MatriksInterpolasi[j][j];
                for (int kolom_kurang = 0; kolom_kurang < kolomInterpolasi; kolom_kurang++) {
                    MatriksInterpolasi[i][kolom_kurang] = MatriksInterpolasi[i][kolom_kurang] - (numeratorRatio * MatriksInterpolasi[j][kolom_kurang] / denominatorRatio);
                }
            }
        }
        //pengurangan mulai dari kolom paling akhir
        //cek bener apa engga
        for (int j = ukuran - 1; j > 0; j--) {
            for (int i = (j - 1); i >= 0; i--) {
                double numeratorRatio = MatriksInterpolasi[i][j];
                double denominatorRatio = MatriksInterpolasi[j][j];
                for (int kolom_kurang = 0; kolom_kurang < kolomInterpolasi; kolom_kurang++) {
                    MatriksInterpolasi[i][kolom_kurang] = MatriksInterpolasi[i][kolom_kurang] - (numeratorRatio * MatriksInterpolasi[j][kolom_kurang] / denominatorRatio);
                }
            }
        }
        for (int division = 0; division < ukuran; division++) {
            double divider = MatriksInterpolasi[division][division];
            MatriksInterpolasi[division][kolomInterpolasi - 1] = (MatriksInterpolasi[division][kolomInterpolasi - 1] / divider);
            MatriksInterpolasi[division][division] = (MatriksInterpolasi[division][division] / divider);
        }
        //harusnya abis ini reduced echelon form bentuknya
        //meng-ekstrak value a0 - an dari MatriksInterpolasi
        double[] Hasil = new double[ukuran];
        for (int i = 0; i < ukuran; i++) {
            Hasil[i] = (Math.round(MatriksInterpolasi[i][kolomInterpolasi - 1] * 10000000.0) / 10000000.0);
        }
        for (int i = 0; i < ukuran; i++) {
            if (i == 0) {
                System.out.printf("P" + n + "(x) = ");
            }
            if (Hasil[i] > 0) {
                System.out.printf("" + Hasil[i]);
            }
            else {
                System.out.printf("(" + Hasil[i] + ")");
            }
            if (i != 0) {
                System.out.printf("x^" + i);
            }
            if (i != (ukuran - 1)) {
                System.out.printf(" + ");
            }
            if (i == (ukuran - 1)) {
                System.out.printf("%n");
            }
        }
        double x = scan.nextDouble();
        double y = Hasil[0];
        for (int i = 1; i < ukuran; i++) {
            y = y + (Hasil[i] * Math.pow(x, i));
        }
        System.out.println(y);
    }
}