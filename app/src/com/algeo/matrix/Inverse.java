package com.algeo.matrix;

public class Inverse {
    public double[][] getInvers(double[][] matrixA){
        // Menggunakan sifat matrix [A|I]=[I|A^-1]
        int i,j,k;
        int ukuran = matrixA.length;;

        Matrix matrixAI = new Matrix(ukuran, ukuran*2);

        Matrix matrixAinverse = new Matrix(ukuran, ukuran);

        boolean[] bariskosong = new boolean[ukuran];
        boolean[] kolomkosong = new boolean[ukuran*2];

        // Masukkan matrix A
        for (i=0;i<ukuran;++i){
            for (j=0;j<ukuran;++j){
                matrixAI.element[i][j] = matrixA[i][j];
            }
        }
        // Masukkan matrix identitas ke dalam matrix
        for (i=0; i<ukuran; ++i){
            for (j=ukuran;j<ukuran*2;++j){
                if (i==(j-ukuran)) {
                    matrixAI.element[i][j] = 1;
                }
                else {
                    matrixAI.element[i][j] = 0;
                }
            }
        }

        // Cetak matrix [A|I]
        System.out.print("Matrix [A|I]\n");
        for (i=0; i< matrixAI.rows; ++i) {
            System.out.print(matrixAI.element[i][0]);
            for (k=1; k< matrixAI.columns;++k) {
                System.out.print(" "+matrixAI.element[i][k]);
                if ((i != matrixAI.rows-1) && (k == matrixAI.rows*2-1)) {
                    System.out.print("\n");
                }
            }
        }
        System.out.println();

        // PROSES PENGUBAHAN [A|I]= [I|A^-1]


        //Matriks Segitiga Atas
        // SPL pasti lebih dari 1 persamaan
        matrixAI.segitigaAtas(bariskosong);

        //Matriks Segitiga Atas -> Eselon
        matrixAI.SAtoEselon();

        //Matriks Eselon -> Matriks Eselon Tereduksi
        matrixAI.ReduksiEselon(bariskosong,kolomkosong);

        // Cetak matrix [I|A^-1]
        System.out.println();
        System.out.print("Matrix [I|A^-1]\n");
        for (i=0; i< matrixAI.rows; ++i) {
            System.out.print(matrixAI.element[i][0]);
            for (k=1; k< matrixAI.columns;++k) {
                System.out.print(" "+matrixAI.element[i][k]);
                if ((i != matrixAI.rows-1) && (k == matrixAI.rows*2-1)) {
                    System.out.print("\n");
                }
            }
        }
        System.out.println();

        // Ekstrak matrix inverse A^-1 dari matrix [I|A^-1]
        for (i=0; i<ukuran; ++i){
            for (j=ukuran;j<ukuran*2;++j){
                matrixAinverse.element[i][j-ukuran] = matrixAI.element[i][j];
            }
        }

        // Cetak matrix A
        System.out.println();
        System.out.print("Matrix A inverse\n");
        for (i=0; i< matrixAinverse.rows; ++i) {
            System.out.print(matrixAinverse.element[i][0]);
            for (k=1; k< matrixAinverse.columns;++k) {
                System.out.print(" "+matrixAinverse.element[i][k]);
                if ((i != matrixAI.rows-1) && (k == matrixAI.rows-1)) {
                    System.out.print("\n");
                }
            }
        }
        System.out.println();

        return matrixAinverse.element;
    }

}
