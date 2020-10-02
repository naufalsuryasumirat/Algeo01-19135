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
        /*
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

        A.crossProductWith(B).printMatrix();

        FileHandler hihi = new FileHandler("test.txt");
        hihi.readFile();

        BDMatrix C = hihi.getData();
        C.printMatrix("THIS IS C");*/

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
        /*BDInterpolasi interpolasiObject = new BDInterpolasi();
        interpolasiObject.readData();
        interpolasiObject.interpolate();
        System.out.println(interpolasiObject.getPersamaan());
        for (int yo = 0; yo < 6; yo++) {
            System.out.println(interpolasiObject.getTitikInterpolasi());
        }*/
        //dengan masukkan file
        /*
        BigDecimal[][] testMatrix = {
                {
                    new BigDecimal(0.1),
                    new BigDecimal(0.003),
                },
                {
                    new BigDecimal(0.3),
                    new BigDecimal(0.067)
                },
                {
                    new BigDecimal(0.5),
                    new BigDecimal(0.148)
                },
                {
                    new BigDecimal(0.7),
                    new BigDecimal(0.248)
                },
                {
                    new BigDecimal(0.9),
                    new BigDecimal(0.370)
                },
                {
                    new BigDecimal(1.1),
                    new BigDecimal(0.518)
                },
                {
                    new BigDecimal(1.3),
                    new BigDecimal(0.697)
                }
        };
        //Interpolasi dari file
        BDMatrix A = new BDMatrix(7, 2, testMatrix);
        Interpolasi interpolasiObject = new Interpolasi(A);
        interpolasiObject.interpolasi();
        System.out.println(interpolasiObject.getPersamaan());
        System.out.println(interpolasiObject.getTitikInterpolasi());

        //Interpolate interpolator = new Interpolate(arrayTitik)
        //interpolator.interpolate()
        //fileHandler(address, interpolator.getPersamaan, WRITE)

        //Interpolasi dengan user input
        Interpolasi interpolasiObject2 = new Interpolasi();
        interpolasiObject2.readData();
        interpolasiObject2.interpolasi();
        System.out.println(interpolasiObject2.getPersamaan());
        System.out.println(interpolasiObject2.getTitikInterpolasi());
        */
        /*
        BigDecimal[][] testDeterminan = {
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
                }
        };
        BDMatrix DET = new BDMatrix(3, 3, testDeterminan);

        //Determinan dengan user input



        //Determinan dari file
        BDDeterminan determinanObjectFile = new BDDeterminan();
        System.out.println(determinanObjectFile.hitungDeterminanOBE(DET));
        System.out.println(determinanObjectFile.hitungDeterminanEK(DET, DET.getRows()));

        //BDInterpolasi interpolasiObject = new BDInterpolasi();
        //interpolasiObject.interpolasi();
        //Interpolasi interpolasiObjectYO = new Interpolasi();
        //interpolasiObjectYO.interpolasi();
        */
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
                                    FileHandler inputMethod = new FileHandler("../../../../../test/" + filename + ".txt");
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
}