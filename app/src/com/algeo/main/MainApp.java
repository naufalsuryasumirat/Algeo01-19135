package com.algeo.main;

import java.io.File;
import java.util.Scanner;

import com.algeo.matrix.*;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class MainApp {
    public static void main (String[] args)
    {
        FileHandler hihi = new FileHandler("reglin.txt");
        hihi.readFile();

        BDMatrix C = hihi.getData();
        C.printMatrix("THIS IS C");

        FileHandler haha = new FileHandler("test2.txt");
        haha.readFile();

        BDRegresiLinier reglinSolver = new BDRegresiLinier(C);
        System.out.println(reglinSolver.readAssertY());
    }
}
