package com.algeo.matrix;


public class Matrix {

    double[][] element;

    int rows;
    int columns;

    // Constructors
    public void Matrix(int rows, int columns)
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

    public Matrix GetEchelonMatrix(Matrix data)
    {
        Matrix eMatrix = new Matrix();

        return eMatrix;
    }

    public Matrix GetReducedEchelonMatrix(Matrix data)
    {
        Matrix reMatrix = new Matrix();


        return reMatrix;
    }

    public void ResizeMatrix(Matrix data)
    {

    }

    public Matrix GetResizeMatrix(Matrix data, int newCol, int newRow)
    {
        Matrix rMatrix = new Matrix();
        return rMatrix;
    }

}
