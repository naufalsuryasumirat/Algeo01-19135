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
                    new BigDecimal(1)
                },

                {
                        new BigDecimal(2),
                        new BigDecimal(1),
                        new BigDecimal(2)
                },

                {
                        new BigDecimal(3),
                        new BigDecimal(4),
                        new BigDecimal(4)
                },
        };

        BDMatrix A = new BDMatrix(3, 3, testMatrix);

        A.printMatrix();

        SPL balikan = new SPL();
        balikan.Balikan();
    }
}
