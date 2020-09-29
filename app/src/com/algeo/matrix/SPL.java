package com.algeo.matrix;

public class SPL {
    public void splGaussJordan() {
        Matrix matrixAugmented = new Matrix(0,0);
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
        Matrix matrixAugmented = new Matrix(1,1);
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
        int i,j,k,l;
        Matrix matrixAugmented = new Matrix(0,0);
        matrixAugmented.makeMatrix();
        matrixAugmented.bacaMatrix();
        double detA;
        /*
        System.out.println();
        for (i=0; i< matrixAugmented.rows; ++i) {
            System.out.print(matrixAugmented.element[i][0]);
            for (k=1; k< matrixAugmented.columns;++k) {
                System.out.print(" "+matrixAugmented.element[i][k]);
                if ((i != matrixAugmented.rows-1) && (k == matrixAugmented.columns-1)) {
                    System.out.print("\n");
                }
            }
        }
        System.out.println();
        */

        // Set matrix A dari matrix augmented
        double[][] matrixA = new double[matrixAugmented.rows][matrixAugmented.rows];
        for (i=0;i<matrixAugmented.rows;++i){
            for (j=0;j<matrixAugmented.rows;++j){
                matrixA[i][j] = matrixAugmented.element[i][j];
            }
        }

        /*
        System.out.println();
        for (i=0; i< matrixAugmented.rows; ++i) {
            System.out.print(matrixA[i][0]);
            for (k=1; k< matrixAugmented.rows;++k) {
                System.out.print(" "+matrixAugmented.element[i][k]);
                if ((i != matrixAugmented.rows-1) && (k == matrixAugmented.columns-1)) {
                    System.out.print("\n");
                }
            }
        }
        System.out.println();
        */

        //Set matrix B dari matrix augmented
        double[] matrixB = new double[matrixAugmented.rows];
        for (i=0;i<matrixAugmented.rows;++i){
            matrixB[i] = matrixAugmented.element[i][matrixAugmented.columns-1];
        }

        if (matrixAugmented.rows != matrixAugmented.columns-1){
            System.out.println("Matriks A bukan matriks bujur sangkar");
        }
        else {
            Determinan det = new Determinan();
            detA = det.hitungDeterminanOBE(matrixA, matrixAugmented.rows);
            double[] ArrayDet = new double[matrixAugmented.rows];
            for (j=0; j<matrixAugmented.rows; ++j){
                // Kopi elemen matrix A ke matrix temporary
                double[][] tempMatrix = new double[matrixAugmented.rows][matrixAugmented.rows];
                for (k=0;k<matrixAugmented.rows;++k){
                    for (l=0;l<matrixAugmented.rows;++l){
                        tempMatrix[k][l] = matrixA[k][l];
                    }
                }
                /*
                System.out.println();
                for (i=0; i< matrixAugmented.rows; ++i) {
                    System.out.print(tempMatrix[i][0]);
                    for (k=1; k< matrixAugmented.rows;++k) {
                        System.out.print(" "+tempMatrix[i][k]);
                        if ((i != matrixAugmented.rows-1) && (k == matrixAugmented.rows-1)) {
                            System.out.print("\n");
                        }
                    }
                }
                */


                // Ganti satu kolom matrix A dengan matrix B
                for (i=0;i<matrixAugmented.rows;++i){
                    tempMatrix[i][j] = matrixB[i];
                }

                ArrayDet[j] = det.hitungDeterminanOBE(tempMatrix, matrixAugmented.rows);
                System.out.println("det A"+(j+1)+" = "+ArrayDet[j]);
            }

            System.out.println("det A"+ " = "+detA);
            for (i=0; i< matrixAugmented.rows; ++i) {
                System.out.println("x"+(i+1)+" = "+(ArrayDet[i]/detA));
            }
        }

    }

    public void Balikan(){
        // Menggunakan rumus [A|I]= [I|A^-1] untuk mendapatkan matriks invers.

        int i,j,k,l;

        Matrix matrixAugmented = new Matrix(0,0);
        matrixAugmented.makeMatrix();
        matrixAugmented.bacaMatrix();

        Matrix matrixA = new Matrix(matrixAugmented.rows,matrixAugmented.rows);
        Matrix matrixX = new Matrix(matrixAugmented.rows,1);
        Matrix matrixB = new Matrix(matrixAugmented.rows,1);
        Matrix matrixAinverse = new Matrix(matrixAugmented.rows,matrixAugmented.rows);

        double[][] xArray;

        Inverse inv = new Inverse();

        /*
        System.out.println();
        for (i=0; i< matrixAugmented.rows; ++i) {
            System.out.print(matrixAugmented.element[i][0]);
            for (k=1; k< matrixAugmented.columns;++k) {
                System.out.print(" "+matrixAugmented.element[i][k]);
                if ((i != matrixAugmented.rows-1) && (k == matrixAugmented.columns-1)) {
                    System.out.print("\n");
                }
            }
        }
        System.out.println();
        */

        // Set matrix A dari matrix augmented
        for (i=0;i<matrixA.rows;++i) {
            for (j = 0; j < matrixA.columns; ++j) {
                matrixA.element[i][j] = matrixAugmented.element[i][j];
            }
        }

        // Set matrix B dari matrix augmented
        for (i=0;i<matrixB.rows;++i) {
            for (j = 0; j < matrixB.columns; ++j) {
                matrixB.element[i][j] = matrixAugmented.element[i][matrixAugmented.columns-1];
            }
        }

        // Olah data sehingga menghasilkan matrix A^-1
        matrixAinverse.element = inv.getInvers(matrixA.element);

        // Operasikan data matrix inverse sehingga mengeluarkan matrix yang mengeuarkan nilai variabel
        matrixX.element = matrixAinverse.MkaliMatrix(matrixB.element); // Lalu kalikan matrix inverse dengan matrix B
        matrixX.bulatElemenKeNol();

        // Cetak solusi
        System.out.println();
        System.out.println("Solusi SPL :");
        for (i=0; i< matrixX.rows; ++i) {
            for (j=0; j< matrixX.columns;++j) {
                System.out.println("x"+(i+1)+" = "+ matrixX.element[i][j]);
            }
        }

        // Set nilai matrix x dari nilai matrix inverse yang sudah diolah

        // Ekstrak matrix A^-1

    }

}
