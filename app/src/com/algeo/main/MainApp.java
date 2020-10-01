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
                        new BigDecimal(4.800),
                        new BigDecimal(8211),
                },
                {
                        new BigDecimal(5.000),
                        new BigDecimal(10118),
                },
                {
                        new BigDecimal(5.516),
                        new BigDecimal(17025),
                },
                {
                        new BigDecimal(5.710),
                        new BigDecimal(20796),
                },
                {
                        new BigDecimal(6.500),
                        new BigDecimal(39294),
                },
                {
                        new BigDecimal(7.194),
                        new BigDecimal(64958),
                },
                {
                        new BigDecimal(8.097),
                        new BigDecimal(113134),
                },
                {
                        new BigDecimal(8.258),
                        new BigDecimal(123503),
                },
                {
                        new BigDecimal(9.033),
                        new BigDecimal(177571),
                },
                {
                        new BigDecimal(9.333),
                        new BigDecimal(145510),
                },

        };


        BigDecimal[][] testMatrix2 = {
                {
                        new BigDecimal(0.1),
                        new BigDecimal(0.003),
                },
                {
                        new BigDecimal(0.3),
                        new BigDecimal(0.067),
                },
                {
                        new BigDecimal(0.5),
                        new BigDecimal(0.148),
                },
                {
                        new BigDecimal(0.7),
                        new BigDecimal(0.248),
                },
                {
                        new BigDecimal(0.9),
                        new BigDecimal(0.370),
                },
                {
                        new BigDecimal(1.1),
                        new BigDecimal(0.518),
                },
                {
                        new BigDecimal(1.3),
                        new BigDecimal(0.697),
                }

        };

        BDMatrix interpolateThis = new BDMatrix(7, 2, testMatrix);
        BDMatrix B = new BDMatrix(2, 2, testMatrix2);
//        C.makeMatrix();
//        C.printMatrix();
//        C.readUserMatrix();
//        BDSPL spl = new BDSPL(C);
//        spl.hitungGaussJordan();


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
//
//        BDInterpolasi interpolator = new BDInterpolasi(interpolateThis);
//        interpolator.interpolate();
//
//        System.out.println(interpolator.getPersamaan());
//        System.out.println(interpolator.getTitikInterpolasi());

//        BDMatrix C = new BDMatrix(5, 6);
//        System.out.println("INPUT SHIT");
//        C.readUserMatrix();
//        C.printMatrix("THIS IS THE USER INPUT");
//        C.reducedEchelon();

        BigDecimal[][] regTester = {
                {
                        new BigDecimal(1),
                        new BigDecimal(15),
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
                }
        };

        FileHandler hihi = new FileHandler("reglin.txt");
        hihi.readFile();

        BDMatrix C = hihi.getData();
        C.printMatrix("THIS IS C");

//        FileHandler huhi = new FileHandler("test2.txt");
//        huhi.readFile();
//
//        BDMatrix D = huhi.getData();
//        D.printMatrix("THIS IS D");
//
//        C.crossProductWith(D).printMatrix("THIS IS THE CROSS PRODUCT");

        BDRegresiLinier reglin = new BDRegresiLinier(C);
//
//        C.reducedEchelon();
//
//        C.printMatrix("THIS IS C REDUCED");

//        BDSPL newHandler = new BDSPL(C);
//        newHandler.hitungGaussJordan();

//        C.printMatrix("THIS IS C REDUCED");

//        hihi.setOutputString(C.convertToString());
//        hihi.writeFile("test.txt");

//        BDMatrix testThisPls = new BDMatrix(3, 3, regTester);
//        testThisPls.echelon();
//        testThisPls.printMatrix("REDUCEDs");
    }
}
