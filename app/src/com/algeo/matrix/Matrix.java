package com.algeo.matrix;


import java.util.Scanner;
import java.math.BigDecimal;

public class Matrix {

    double[][] element;

    int rows;
    int columns;

    // Constructors
    public Matrix(int rows, int columns)
    {
        this.rows = rows;
        this.columns = columns;
        this.element = new double[rows][columns];
    }

    // Getter and Setter methods
    public double getElmt(int row, int column)
    {
        return this.element[row][column];
    }

    public void setElmt(int row, int column, double val)
    {
        this.element[row][column] = val;
    }

    // Methods
    public void Test()
    {
        System.out.println("Testing TEST function");
    }

    public Matrix GetEchelonMatrix()
    {
        Matrix eMatrix = new Matrix(this.rows, this.columns);

        return eMatrix;
    }

    public Matrix GetReducedEchelonMatrix()
    {
        Matrix reMatrix = new Matrix(this.rows, this.columns);


        return reMatrix;
    }

    public void ResizeMatrix(int newRow, int newCol)
    {
        Matrix rMatrix = new Matrix(newRow, newCol);


    }

    public Matrix GetResizeMatrix(int newRow, int newCol)
    {
        Matrix rMatrix = new Matrix(newRow, newCol);
        return rMatrix;
    }


    public void makeMatrix() {
        /** ALGORITMA **/
        Scanner scan = new Scanner(System.in);
        rows = scan.nextInt();
        columns = scan.nextInt();
        element = new double[rows][columns];
    }

    public void bacaMatrix() {
        /** KAMUS LOKAL **/
        int i, j;
        /** ALGORITMA **/
        Scanner scan = new Scanner(System.in);
        for (i = 0; i < this.rows; ++i) {
            for (j = 0; j < this.columns; ++j) {
                this.element[i][j] = scan.nextDouble();
            }
        }
    }

    public void bulatElemenKeNol(){
        // Fungsi yang digunakan untuk membulatkan bilangan positif yang sangat mendekati nol ke nol dalam matriks
        /** KAMUS LOKAL **/
        int i, j;
        /** ALGORITMA **/
        for (i = 0; i < this.rows; ++i) {
            for (j = 0; j < this.columns; ++j) {
                if ((this.element[i][j] < 0.00001 && this.element[i][j] > 0) || (this.element[i][j] > -0.00001 && this.element[i][j] < 0)) {
                    this.element[i][j] = 0;
                }
            }
        }
    }
    public int idxkolomLeadingOne(int row){
        // I.S. : MATRIKS BERUPA MATRIKS ESELON
        // FUNGSI MENGEMBALIKAN KOLOM LEADING ONE BERADA DALAM SUATU BARIS.
        /** KAMUS LOKAL **/
        int j;
        /** ALGORITMA **/
        for (j=0;j<this.columns-1;++j) {
            if (this.element[row][j] == 1) {
                return j;
            }
        }
        return -999;
    }
    public int idxbarisPersAkhir(){
        // return baris terakhir yang tidak kosong
        /** KAMUS LOKAL **/
        int i,j;
        /** ALGORITMA **/
        for (i=this.rows-1;i>=0;--i) {
            j = 0;
            while(j < this.columns){
                if (this.element[i][j]==0){
                    ++j;
                }
                else {
                    return i;
                }
            }
        }
        return -999;
    }

    public void plusAllPL(){
        //Setiap persamaan linear dijumlahkan dengan semua persamaan linear
        /** KAMUS LOKAL **/
        int i,j,k;
        /** ALGORITMA **/
        for (i=0; i< this.rows; ++i) {
            for (j=0; j< this.columns; ++j) {
                for (k=0; k< this.rows; ++k) {
                    this.element[i][j] += this.element[k][j];
                }
            }
        }
    }

    public void TulisMatrix(){
        /** KAMUS LOKAL **/
        int i,j;
        /** ALGORITMA **/
        for (i=0; i< this.rows; ++i) {
            System.out.print(this.element[i][0]);
            for (j=1; j< this.columns;++j) {
                System.out.print(" "+this.element[i][j]);
                if ((i != this.rows-1) && (j == this.columns-1)) {
                    System.out.print("\n");
                }
            }
        }
    }

    public void segitigaAtas (boolean[] bariskosong){
        /** KAMUS LOKAL **/
        int i,j,k;
        double ratio;
        /** ALGORITMA **/
        for (i=0; i < this.rows; ++i) {
            if (!bariskosong[i] && this.element[i][i]!= 0) {
                for (j = 0; j < this.rows; ++j) {
                    if (j > i) {
                        ratio = this.element[j][i] / this.element[i][i];
                        for (k = 0; k < this.columns; ++k) {
                            this.element[j][k] -= (ratio * this.element[i][k]);
                        }
                    }
                }
            }
        }
        this.bulatElemenKeNol();
    }
    public void SAtoEselon(){
        /** KAMUS LOKAL **/
        int i,j,k;
        double divisor;
        /** ALGORITMA **/
        for (i=0; i <this.rows; ++i) {
            divisor = 0;
            k = 0;
            while (k<this.columns && divisor ==0) {
                if(this.element[i][k]!=0) {
                    divisor = this.element[i][k];
                }
                ++k;
            }
            if (divisor!=0) {
                for (j = 0; j < this.columns; ++j) {
                    this.element[i][j] /= divisor;
                }
            }
        }
        this.bulatElemenKeNol();
    }

    public void ReduksiEselon(boolean[] bariskosong, boolean[] kolomkosong){
        /** KAMUS LOKAL **/
        int i,j,k;
        double divisor,ratio;
        /** ALGORITMA **/
        for (i = 0; i < this.rows; ++i) {
            divisor = 0;
            k = 0;
            if (!kolomkosong[i] && !bariskosong[i]) {
                for (j = 0; j < this.rows; ++j) {
                    if (j > i) {
                        while (k < this.columns && divisor == 0) {
                            if (this.element[i][k] != 0) {
                                divisor = this.element[i][k];
                            }
                            ++k;
                        }
                        ratio = this.element[i][j] / divisor;
                        for (k = 0; k < this.columns; ++k) {
                            this.element[i][k] -= (ratio * this.element[j][k]);
                        }
                    }
                }
            }
        }
        this.bulatElemenKeNol();
    }

    public void sortBarisBasedOnColumnVal (int column){

    }
}
