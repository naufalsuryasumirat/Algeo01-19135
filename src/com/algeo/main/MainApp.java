package com.algeo.main;

import java.sql.SQLOutput;
import java.util.Scanner;

import com.algeo.matrix.*;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class MainApp {
    public static void main(String[] args) {
        int state;
        int state2;
        int jenisinput;
        int write;

        BDDeterminan determinanObject = new BDDeterminan();
        Interpolasi interpolasiObject = new Interpolasi();

        BDMatrix matrix = new BDMatrix();

        state = 0;
        while (state != 6) {
            main_menu();
            Scanner scan = new Scanner(System.in);
            state = scan.nextInt();
            switch(state) {
                case 1 :
                    menu_2();
                    state2 = scan.nextInt();

                    BDMatrix SPLQuery = new BDMatrix();

                    menu_input();
                    jenisinput = scan.nextInt();

                    switch(jenisinput)
                    {
                        case 1:
                            SPLQuery = menu_readMatrix(SPLQuery);
                            break;
                        case 2:
                            SPLQuery.readUserMatrix();
                            break;
                    }

                    BDSPL SPLCalc = new BDSPL(SPLQuery);
                    String solutionToSPL = "";

                    switch(state2) {
                        //Metode eliminasi gauss
                        case 1 :
                            SPLCalc.hitungGauss();
                            solutionToSPL = SPLCalc.getSolution();
                            break;
                        //Metode eliminasi gauss-jordan
                        case 2 :
                            SPLCalc.hitungGaussJordan();
                            solutionToSPL = SPLCalc.getSolution();
                            break;
                        //Metode matriks balikan
                        case 3 :
                            SPLCalc.hitungInvers();
                            solutionToSPL = SPLCalc.getSolution();
                            break;
                        //Kaidah cramer
                        case 4 :
                            solutionToSPL = SPLCalc.hitungKramerDBL(SPLQuery.convertToDoubleMatrix());
                            break;
                        default :
                            break;
                    }
                    System.out.println(solutionToSPL);
                    menu_write(solutionToSPL);

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
                                    BDMatrix Det = new BDMatrix();

                                    Det = menu_readMatrix(Det);
                                    BigDecimal resDet = determinanObject.hitungDeterminanEK(Det, Det.getRows());

                                    String determinan = (resDet.toString());
                                    System.out.println(determinanObject.hitungDeterminanEK(Det, Det.getRows()));
                                    menu_write(determinanObject.hitungDeterminanEK(Det, Det.getRows()));
                                    break;
                                case 2 :
                                    determinanObject.readData();
                                    System.out.println(determinanObject.getAttributeDeterminanEK());
                                    menu_write(determinanObject.getAttributeDeterminanEK());
                                    break;
                                default :
                                    break;
                            }
                            break;
                                //Metode OBE
                                case 2 :
                                    menu_input();
                                    jenisinput = scan.nextInt();
                                    switch(jenisinput) {
                                        case 1 :
                                            BDMatrix Det = new BDMatrix();

                                            Det = menu_readMatrix(Det);
                                            BigDecimal resDet = determinanObject.hitungDeterminanOBE(Det);

                                            String determinan = (resDet.toString());
                                            System.out.println(determinanObject.hitungDeterminanOBE(Det));
                                            menu_write(determinanObject.hitungDeterminanOBE(Det));
                                            break;
                                        case 2 :
                                            determinanObject.readData();
                                            System.out.println(determinanObject.getAttributeDeterminanOBE());
                                            menu_write(determinanObject.getAttributeDeterminanOBE());
                                            break;
                                    }
                            break;
                    } break;
                case 3 :
                    //Matriks Balikan
                    menu_input();
                    jenisinput = scan.nextInt();
                    BDMatrix toInvert = new BDMatrix();
                    BDMatrix invertedResult = new BDMatrix();
                    switch(jenisinput) {
                        case 1:
                            toInvert = menu_readMatrix(toInvert);
                            Inverse inverted = new Inverse(toInvert);
                            invertedResult = inverted.getInverse();
                            break;
                        case 2:
                            toInvert.readUserMatrix();
                            Inverse uInverted = new Inverse(toInvert);
                            invertedResult = uInverted.getInverse();
                            break;
                        default:
                            break;
                    }
                    menu_write(invertedResult);
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
                            FileHandler inputMethod = new FileHandler("app/test/" + filename + ".txt");
                            inputMethod.readFile();
                            BDMatrix MatrixInterpolasi = inputMethod.getData();
                            Interpolasi interpolasiObjects = new Interpolasi(MatrixInterpolasi);
                            String persamaan = interpolasiObjects.getPersamaan();
                            double titikInterpolasi = interpolasiObjects.getTitikInterpolasi();
                            System.out.println(persamaan);
                            System.out.println(titikInterpolasi);
                            menu_write(persamaan);
                            break;
                        case 2 :
                            interpolasiObject.readData();
                            interpolasiObject.interpolasi();
                            String interpolasi = interpolasiObject.getPersamaan();
                            System.out.println(interpolasi);
                            double titik = interpolasiObject.getTitikInterpolasi();
                            System.out.println(titik);
                            menu_write(interpolasi);
                            break;
//                        default :
//                            break;
                    } break;
                case 5 :
                    //Regresi Linier Berganda
                    menu_input();
                    jenisinput = scan.nextInt();
                    BDMatrix reglinData = new BDMatrix();
                    BDRegresiLinier reglinner = new BDRegresiLinier();
                    switch(jenisinput) {
                        case 1 :
                            reglinData = menu_readMatrix(reglinData);
                            reglinner.setData(reglinData);
                            System.out.println("Input Xk");
                            reglinner.readAssertY();
                            break;
                        case 2 :
                            reglinner.readData();
                            reglinner.readAssertY();
                            break;
                        }
                    System.out.println(reglinner.getEquation());
                    menu_write(reglinner.getEquation());
                    System.out.println(reglinner.getResult());
                    menu_write(reglinner.getResult());
                    break;
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
        System.out.println("1. Metode ELiminasi Gauss");
        System.out.println("2. Metode Eliminasi Gauss-Jordan");
        System.out.println("3. Metode Inverse");
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
    private static void menu_write(BDMatrix matrix) {
        System.out.println("Apakah akan di save ke file? 0 untuk Tidak, 1 untuk Ya");
        Scanner readFileName = new Scanner(System.in);

        switch (readFileName.nextLine())
        {
            case("0"):
                break;
            case("1"):
                System.out.println("Input nama file tujuan save");
                FileHandler getMatrix = new FileHandler("app/test/_" + readFileName.nextLine() + ".txt");
                getMatrix.setOutputString(matrix.convertToString());
                getMatrix.writeFile("test/_" + readFileName.nextLine() + ".txt");
        }

        return;
    }

    private static void menu_write(String input) {
        System.out.println("Apakah akan di save ke file? 0 untuk Tidak, 1 untuk Ya");
        Scanner readFileName = new Scanner(System.in);

        switch (readFileName.nextLine())
        {
            case("0"):
                break;
            case("1"):
                System.out.println("Input nama file tujuan save");
                FileHandler getMatrix = new FileHandler("app/test/_" + readFileName.nextLine() + ".txt");
                getMatrix.setOutputString(input);
                getMatrix.writeFile("test/_" + readFileName.nextLine() + ".txt");
        }

        return;
    }

    private static void menu_write(BigDecimal number) {
        System.out.println("Apakah akan di save ke file? 0 untuk Tidak, 1 untuk Ya");
        Scanner readFileName = new Scanner(System.in);

        switch (readFileName.nextLine())
        {
            case("0"):
                break;
            case("1"):
                System.out.println("Input nama file tujuan save");
                String filename = "test/_" + readFileName.nextLine() + ".txt";

                FileHandler getMatrix = new FileHandler(filename);
                getMatrix.setOutputString(number.toPlainString());
                getMatrix.writeFile(filename);
                return;
        }

        return;
    }

    private static BDMatrix menu_readMatrix(BDMatrix matrix)
    {
        System.out.println("Input nama file");
        Scanner readFileName = new Scanner(System.in);

        FileHandler getMatrix = new FileHandler("test/" + readFileName.nextLine() + ".txt");
        getMatrix.readFile();
        matrix = getMatrix.getData();

        return matrix;
    }
}

