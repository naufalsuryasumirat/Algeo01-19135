package com.algeo.main;

import com.algeo.matrix.Matrix;
import com.algeo.matrix.Determinan;

public class MainApp {
    public static void main (String[] args)
    {
        Determinan simpleMessageObject = new Determinan();
        simpleMessageObject.simpleMessage();
        System.out.println("Hello world");
        Matrix test = new Matrix();
        test.Test();
    }
}
