package com.algeo.main;

import java.util.Scanner;

import com.algeo.matrix.*;

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
                        new BigDecimal(2),
                        new BigDecimal(1),
                        new BigDecimal(3),
                        new BigDecimal(1),
                        new BigDecimal(0),
                        new BigDecimal(2),
                },
                {
                        new BigDecimal(3),
                        new BigDecimal(4),
                        new BigDecimal(2),
                        new BigDecimal(5),
                        new BigDecimal(1),
                        new BigDecimal(0),
                        new BigDecimal(-1),
                },
                {
                        new BigDecimal(3),
                        new BigDecimal(4),
                        new BigDecimal(4),
                        new BigDecimal(5),
                        new BigDecimal(0),
                        new BigDecimal(1),
                        new BigDecimal(1),
                },
                {
                        new BigDecimal(6),
                        new BigDecimal(7),
                        new BigDecimal(4),
                        new BigDecimal(3),
                        new BigDecimal(3),
                        new BigDecimal(3),
                        new BigDecimal(3),
                },
        };


        BigDecimal[][] testMatrix2 = {
                {
                    new BigDecimal(5),
                    new BigDecimal(6),
                    new BigDecimal(1),
                    new BigDecimal(3),
                },
                {
                    new BigDecimal(7),
                    new BigDecimal(8),
                    new BigDecimal(1),
                    new BigDecimal(3),
                }
        };

        BDMatrix A = new BDMatrix(2, 2, testMatrix);
        BDMatrix B = new BDMatrix(2, 2, testMatrix2);
        BDMatrix C = new BDMatrix();
        C.makeMatrix();
        C.printMatrix();
        C.readUserMatrix();
        BDSPL spl = new BDSPL(C);
        spl.hitungGaussJordan();
        /*


        A.crossProductWith(B).printMatrix();

        FileHandler hihi = new FileHandler("test.txt");
        hihi.readFile();

        BDMatrix C = hihi.getData();
        C.printMatrix("THIS IS C");
        C.upperTri();
        hihi.setOutputString(C.convertToString());
        hihi.writeFile("test.txt");
        */


//        A.printMatrix();
//
//        BigDecimal detA = new BDDeterminan(A).hitungDeterminanOBE(A).stripTrailingZeros();
//        System.out.println(detA);
//
//        A.printMatrix();
//        A.reducedEchelon();
//        A.printMatrix("REDUCED ECHELON FORM");


//        A.printMatrix("ORIGINAL");
//        System.out.println("===============================");
//        A.reducedEchelon();
//        A.printMatrix("REDUCED ECHELON");


    }
}
