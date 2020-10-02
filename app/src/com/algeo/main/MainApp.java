package com.algeo.main;

import java.sql.SQLOutput;
import java.util.Scanner;

import com.algeo.matrix.*;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class MainApp {
    public static void main (String[] args)
    {
        int state;
        int state2;
        int jenisinput;
        int write;

        BDDeterminan determinanObject = new BDDeterminan();
        Interpolasi interpolasiObject = new Interpolasi();

        state = 0;
        while (state != 6) {
            main_menu();
            Scanner scan = new Scanner(System.in);
            state = scan.nextInt();
            switch(state) {
                case 1 :
                    menu_2();
                    state2 = scan.nextInt();
                    switch(state2) {
                        //Metode eliminasi gauss
                        case 1 :
                            break;
                        //Metode eliminasi gauss-jordan
                        case 2 :
                            break;
                        //Metode matriks balikan
                        case 3 :
                            break;
                        //Kaidah cramer
                        case 4 :
                            break;
                        default :
                            break;
                    }
                    break;
                case 2 :
                    menu_determinan();
                    state2 = scan.nextInt();
                    switch(state2) {
                        //Metode EK
                        case 1 :
                            menu_input();
                            jenisinput = scan.nextInt();
                            switch(jenisinput) {
                                case 1 :
                                    Scanner detScan = new Scanner(System.in);
                                    System.out.println("Masukkan nama file");
                                    String filename = detScan.nextLine();
                                    FileHandler inputMethod = new FileHandler("app/test/" + filename + ".txt");
                                    inputMethod.readFile();
                                    BDMatrix Det = inputMethod.getData();
                                    String determinan = (determinanObject.hitungDeterminanEK(Det, Det.getRows())).toString();
                                    System.out.println(determinanObject.hitungDeterminanEK(Det, Det.getRows()));
                                    menu_write();
                                    write = scan.nextInt();
                                    switch(write) {
                                        case 0 :
                                            break;
                                        case 1 :
                                            //writefile
                                            inputMethod.setOutputString(determinan);
                                            inputMethod.writeFile("./../../../../test/" + filename + "_hasil.txt");
                                            break;
                                        default :
                                            break;
                                    }
                                    break;
                                case 2 :
                                    determinanObject.readData();
                                    System.out.println(determinanObject.getAttributeDeterminanEK());
                                    menu_write();
                                    write = scan.nextInt();
                                    switch(write) {
                                        case 0 :
                                            break;
                                        case 1 :
                                            //salah
                                            FileHandler inputMethod2 = new FileHandler("as");
                                            inputMethod2.setOutputString(determinanObject.getAttributeDeterminanEK().toString());


                                    }
                                    break;
                                default :
                                    break;
                            }
                        //Metode OBE
                        case 2 :
                            menu_input();
                            jenisinput = scan.nextInt();
                            switch(jenisinput) {
                                case 1 :
                                    //filepath salah
                                    System.out.println("Masukkan nama file");
                                    String filename = scan.nextLine();
                                    FileHandler inputMethod = new FileHandler(filename);
                                    break;
                                case 2 :
                                    break;
                            }


                    }
                case 3 :
                    //Matriks Balikan
                    break;
                case 4 :
                    //Interpolasi Polinom
                    menu_input();
                    jenisinput = scan.nextInt();
                    switch(jenisinput) {
                        case 1 :
                            //Interpolasi (BDMatrix Titik)
                            System.out.println("Masukkan nama file");
                            String filename = scan.nextLine();
                            //benerin path nya
                            FileHandler inputMethod = new FileHandler("./../../../../test/" + filename + ".txt");

                            break;
                        case 2 :
                            interpolasiObject.readData();
                            interpolasiObject.interpolasi();
                            String interpolasi = interpolasiObject.getPersamaan();
                            System.out.println(interpolasi);
                            menu_write();
                            write = scan.nextInt();
                            switch(write) {
                                case 0 :
                                    break;
                                case 1 :
                                    //write
                                    break;
                            }
                            break;
                        default :
                            break;
                    }
                case 5 :
                    //Regresi Linier Berganda
                    menu_input();
                    jenisinput = scan.nextInt();
                    switch(jenisinput) {
                        case 1 :
                            break;
                        case 2 :
                            break;
                    }
            }
        }
    }
    private static void main_menu() {
        System.out.println("MENU PROGRAM");
        System.out.println("1. Sistem Persamaan Linier");
        System.out.println("2. Determinan");
        System.out.println("3. Matriks Balikan");
        System.out.println("4. Interpolasi Polinom");
        System.out.println("5. Regresi Linier Berganda");
        System.out.println("6. Keluar");
    }
    private static void menu_2() {
        System.out.println("1. Sistem Persamaan Linier");
        System.out.println("2. Metode Eliminasi Gauss");
        System.out.println("3. Metode Eliminasi Gauss-Jordan");
        System.out.println("4. Kaidah Cramer");
    }
    private static void menu_determinan() {
        System.out.println("1. Ekspansi Kofaktor");
        System.out.println("2. Operasi Baris Elementer");
    }
    private static void menu_input() {
        System.out.println("1. Dari File");
        System.out.println("2. Dari Keyboard");
    }
    private static void menu_write() {
        System.out.println("Apakah akan di save ke file? 0 untuk Tidak, 1 untuk Ya");
    }

    private static void menu_readMatrix(BDMatrix matrix)
    {
        System.out.println("Input nama file");
        Scanner readFileName = new Scanner(System.in);

        FileHandler getMatrix = new FileHandler("app/test/" + readFileName.nextLine() + ".txt");
        getMatrix.readFile();
        matrix = getMatrix.getData();
        matrix.printMatrix();
    }
}