package com.algeo.matrix;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Scanner;
import java.lang.Math;

public class Interpolasi {
    double[][] ArrayHasil;
    BDMatrix ArrayHasilBD;
    String Persamaan;
    double[][] ArrayTitik;
    BDMatrix ArrayTitikBD;
    BigDecimal titikXBD;
    double titikX;
    BigDecimal titikInterpolasi;
    int derajat;
    MathContext dc = MathContext.DECIMAL128;

    public Interpolasi() {

    }

    public Interpolasi(BDMatrix Titik) {
        derajat = Titik.getRows() - 1;
        int row = Titik.getRows();
        int column = Titik.getColumns();
        //mengkonversikan BigDecimal Inputan ke double
        double[][] toArrayTitik = new double[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                toArrayTitik[i][j] = Titik.getElmt(i, j).doubleValue();
            }
        }
        ArrayTitik = toArrayTitik;
    }

    public double getTitikInterpolasi() {
        Scanner scan = new Scanner(System.in);
        double x = scan.nextDouble();
        double y = ArrayHasil[0][0];
        int ukuran = derajat + 1;
        for (int i = 1; i < ukuran; i++) {
            y = y + (ArrayHasil[i][0] * Math.pow(x, i));
        }
        return y;
    }
    public BigDecimal getTitikInterpolasi(BigDecimal x) {
        double xin = x.doubleValue();
        double y = ArrayHasil[0][0];
        int ukuran = derajat + 1;
        for (int i = 1; i < ukuran ; i++) {
            y = y + (ArrayHasil[i][0] * Math.pow(xin, i));
        }
        return BigDecimal.valueOf(y);
    }

    private void setArrayHasil(double[][] Hasil) {
        ArrayHasil = Hasil;
    }

    public String getPersamaan() {
        return Persamaan;
    }

    //Method kalo perlu buat ambil BDMatrix ArrayHasilBD
    public BDMatrix getArrayHasilBD() {
        double[][] toBD = ArrayHasil;
        int ukuran = derajat + 1;
        BDMatrix returnBD = new BDMatrix(ukuran, 1);
        for (int i = 0; i < ukuran; i ++) {
            returnBD.setElmt(i, 0, BigDecimal.valueOf(toBD[i][0]));
        }
        return returnBD;
    }

    //Method buat manggil User Input buat dapetin titik dalam bentuk BigDecimal kalo perlu
    public BigDecimal getTitikInterpolasiBD() {
        Scanner scan = new Scanner(System.in);
        titikXBD = scan.nextBigDecimal();
        titikInterpolasi = BigDecimal.ZERO;
        for (int i = 0; i < ArrayHasilBD.getRows(); i++) {
            titikInterpolasi = titikInterpolasi.add(ArrayHasilBD.getElmt(i, 0).multiply(titikXBD.pow(i)));
        }
        return titikInterpolasi;
    }

    //Method buat convert ArrayHasil jadi String (bukan buat dipake)
    private String convertingToString(double[][] hasilInterpolasi) {
        int ukuran = derajat + 1;
        int n = derajat;
        String output = "";
        for (int i = 0; i < ukuran; i++) {
            if (i == 0) {
                output += ("P" + n + "(x) = ");
            }
            if (hasilInterpolasi[i][0] > 0) {
                output += ("" + hasilInterpolasi[i][0]);
            }
            else {
                output += ("(" + hasilInterpolasi[i][0] + ")");
            }
            if (i != 0) {
                output += ("x^" + i);
            }
            if (i != (ukuran - 1)) {
                output += (" + ");
            }
        }
        return output;
    }

    //Method buat input data dari User
    public void readData() {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        double[][] matrixTitik = makeMatriksTitik(n);
        ArrayTitik = matrixTitik;
        derajat = n;
    }

    //Method buat makeMatriksTitik dari input n pengguna (private gadipake di MainApp)
    private double[][] makeMatriksTitik(int n) {
        //BDMatrix matriksTitik = new BDMatrix(n + 1, 2);
        Scanner scan = new Scanner(System.in);
        double[][] matriksTitik = new double[n + 1][2];
        for (int i = 0; i < (n + 1); i++) {
            for (int j = 0; j < 2; j++) {
                matriksTitik[i][j] = scan.nextDouble();
            }
        }
        return matriksTitik;
    }

    //Method menghitung interpolasi dan menginisialisasi ArrayHasil
    public void interpolasi() {
        int ukuran = (derajat + 1);
        double[][] MatriksTitik = ArrayTitik;
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
        double[][] Hasil = new double[ukuran][1];
        for (int i = 0; i < ukuran; i++) {
            Hasil[i][0] = (Math.round(MatriksInterpolasi[i][kolomInterpolasi - 1] * 10000000.0) / 10000000.0);
        }
        setArrayHasil(Hasil);
        String OUT = convertingToString(ArrayHasil);
        Persamaan = OUT;
    }
}