package com.algeo.matrix;

public class SPL {
    Matrix matrixAugmented = new Matrix(1,1);
    public void splGaussJordan() {
        matrixAugmented.makeMatrix();
        matrixAugmented.bacaMatrix();

        boolean isTidakAdaJawaban = false;
        boolean[] bariskosong = new boolean[matrixAugmented.rows];
        boolean[] kolomkosong = new boolean[matrixAugmented.columns-1];
        double x[] = new double[matrixAugmented.columns]; // nilai X apabila solusi unik
        int i,j,k;
        ;
        // Apabila matrixAugmented.element augmented memiliki baris X dan kolom X-1

        // Menjumlahkan setiap baris sebagai upaya untuk menghindari elemen 0
        matrixAugmented.plusAllPL();

        //Matriks Segitiga Atas
        // SPL pasti lebih dari 1 persamaan
        matrixAugmented.segitigaAtas(bariskosong);

        //Matriks Segitiga Atas -> Eselon
        matrixAugmented.SAtoEselon();

        //Matriks Eselon -> Matriks Eselon Tereduksi
        matrixAugmented.ReduksiEselon(bariskosong,kolomkosong);


        // Pengecekkan terdapat jawaban atau tidak
        for (i=0; i<matrixAugmented.rows; ++i) {
            if (matrixAugmented.idxkolomLeadingOne(i) == -999 && matrixAugmented.element[i][matrixAugmented.columns - 1] != 0) {
                isTidakAdaJawaban = true;
            }

        }
        // Pengisian array boolean bariskosong dan kolomkosong
        for (i=0;i< matrixAugmented.rows;++i){
            bariskosong[i] = true;
        }
        for (j=0;j< matrixAugmented.columns-1;++j){
            kolomkosong[j] = true;
        }
        // Mengecek apakah terdapat baris atau kolom yang kosong
        for (i=0; i< matrixAugmented.rows; ++i) {
            for (j=0; j< matrixAugmented.columns; ++j) {
                if (matrixAugmented.element[i][j] != 0) {
                    bariskosong[i] = false;
                }
            }
        }
        for (j=0; j<matrixAugmented.columns-1; ++j) {
            for (i=0; i< matrixAugmented.rows; ++i) {
                if (matrixAugmented.element[i][j] != 0) {
                    kolomkosong[j] = false;
                }
            }
        }

        // Tulis matriks augmented dalam bentuk matriks eselon tereduksi
        matrixAugmented.TulisMatrix();

        // Pemrosesan nilai
        System.out.print("\n");
        if (!isTidakAdaJawaban) {
            System.out.print("Solusi SPL:\n");
            for (i = 0; i <=matrixAugmented.idxbarisPersAkhir(); ++i) {
                if (!kolomkosong[i]) {
                    x[i] = matrixAugmented.element[i][matrixAugmented.columns - 1];
                    System.out.print("x" + (i + 1) + " =");
                    if (x[i]!=0) {
                        System.out.print(" " + x[i]);
                    }
                    if (matrixAugmented.idxkolomLeadingOne(matrixAugmented.idxbarisPersAkhir()) != matrixAugmented.columns-2) {
                        for (k = matrixAugmented.idxkolomLeadingOne(matrixAugmented.idxbarisPersAkhir())+1; k < matrixAugmented.columns - 1; ++k) {
                            if (matrixAugmented.element[i][k]!=0 && k != i-1){
                                System.out.print(" -" + "(" + matrixAugmented.element[i][k] + ")" + "x" + (k + 1));
                            }
                        }
                    }
                    else if (x[i]==0){
                        System.out.print(" " + "x" + (j + 1));
                        System.out.print("\n");
                    }
                    System.out.print("\n");
                }

                else{
                    System.out.print("x" + (i + 1) + " = " + null + "\n");
                }
            }
            for (j = matrixAugmented.idxbarisPersAkhir()+1; j < matrixAugmented.columns-1; ++j) {
                if (!kolomkosong[j]) {
                    System.out.print("x" + (j + 1) + " = " + "x" + (j + 1));
                    System.out.print("\n");
                }
                else {
                    System.out.print("x" + (i + 1) + " = " + null + "\n");
                }
            }
        }
        else {
            System.out.println("Tidak ada solusi");
        }
    }

    public void splGauss(){
        matrixAugmented.makeMatrix();
        matrixAugmented.bacaMatrix();

        boolean isTidakAdaJawaban = false;
        boolean[] bariskosong = new boolean[matrixAugmented.rows];
        boolean[] kolomkosong = new boolean[matrixAugmented.columns-1];
        double x[] = new double[matrixAugmented.columns]; // nilai X apabila terdapat 1 solusi
        int i,j,k;
        ;
        // Apabila matrixAugmented.element augmented memiliki baris X dan kolom X-1

        // Menjumlahkan setiap baris sebagai upaya untuk menghindari elemen 0
        matrixAugmented.plusAllPL();

        //Matriks Segitiga Atas
        // SPL pasti lebih dari 1 persamaan
        matrixAugmented.segitigaAtas(bariskosong);

        //Matriks Segitiga Atas -> Eselon
        matrixAugmented.SAtoEselon();

        // Pengecekkan terdapat jawaban atau tidak
        for (i=0; i<matrixAugmented.rows; ++i) {
            if (matrixAugmented.idxkolomLeadingOne(i) == -999 && matrixAugmented.element[i][matrixAugmented.columns - 1] != 0) {
                isTidakAdaJawaban = true;
            }

        }
        // Pengisian array boolean bariskosong dan kolomkosong
        for (i=0;i< matrixAugmented.rows;++i){
            bariskosong[i] = true;
        }
        for (j=0;j< matrixAugmented.columns-1;++j){
            kolomkosong[j] = true;
        }
        // Mengecek apakah terdapat baris atau kolom yang kosong
        for (i=0; i< matrixAugmented.rows; ++i) {
            for (j=0; j< matrixAugmented.columns; ++j) {
                if (matrixAugmented.element[i][j] != 0) {
                    bariskosong[i] = false;
                }
            }
        }
        for (j=0; j<matrixAugmented.columns-1; ++j) {
            for (i=0; i< matrixAugmented.rows; ++i) {
                if (matrixAugmented.element[i][j] != 0) {
                    kolomkosong[j] = false;
                }
            }
        }

        // Tulis matriks augmented dalam bentuk matriks eselon tereduksi
        matrixAugmented.TulisMatrix();
    }
    public void Kramer(){

    }
    public void Balikan(){

    }

}
