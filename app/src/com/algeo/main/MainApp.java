package com.algeo.main;

import java.util.Scanner;
import com.algeo.matrix.Matrix;
import com.algeo.matrix.Determinan;

public class MainApp {
    public static void main (String[] args)
    {
        Scanner input = new Scanner(System.in);
        Determinan simpleMessageObject = new Determinan();

        System.out.println("Enter your name here: ");
        String name = input.nextLine();

        simpleMessageObject.simpleMessage(name);

        System.out.println("Hello world");
        Matrix test = new Matrix();
        test.Test();
    }
}
