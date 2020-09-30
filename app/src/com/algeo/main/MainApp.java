package com.algeo.main;

import java.util.Scanner;

import com.algeo.matrix.BDMatrix;
import com.algeo.matrix.Matrix;
import com.algeo.matrix.Determinan;
import com.algeo.matrix.SPL;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class MainApp {
    public static void main (String[] args)
    {
        System.out.println("Hello world");
        Matrix test = new Matrix(1, 1);
        test.Test();

        System.out.println("Testing BDMatrix");
        BigDecimal[][] testMatrix = {
                {
                        new BigDecimal(1),
                        new BigDecimal(0),
                        new BigDecimal(1),
                        new BigDecimal(3)
                },
                {
                        new BigDecimal(2),
                        new BigDecimal(1),
                        new BigDecimal(2),
                        new BigDecimal(5)
                },
                {
                        new BigDecimal(3),
                        new BigDecimal(4),
                        new BigDecimal(4),
                        new BigDecimal(5)
                },
                {
                        new BigDecimal(6),
                        new BigDecimal(7),
                        new BigDecimal(4),
                        new BigDecimal(3)
                },
        };

        BDMatrix A = new BDMatrix(3, 4, testMatrix);
        BDMatrix B = new BDMatrix(A.getRows());

        int size = A.getColumns();

        A.printMatrix("A");
        B.printMatrix("B");

        Determinan determinator = new Determinan();

        System.out.println("===============================");

        System.out.println("===============================");

        A.addHorizontal(B);
        A.printMatrix();

        System.out.println("===============================");

        A.reducedEchelon();
        A.printMatrix("REDUCED ECHELON MATRIX");

        System.out.println("===============================");

        A.removeHorizontal(size, 0);
        A.printMatrix("INVERSE");

        /*
        SPL balikan = new SPL();
        balikan.Balikan();

         */

    }
}
