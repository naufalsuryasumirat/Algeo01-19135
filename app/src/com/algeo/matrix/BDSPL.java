package com.algeo.matrix;

import java.math.BigDecimal;

public class BDSPL {

    BDMatrix original;
    BDMatrix data;
    String[] solution;
    BDMatrix solutionMatrix;

    public BDSPL(BDMatrix input)
    {
        data = input;
        original = input;
        solution = new String[input.getColumns()-1];
    }

    public void hitungGauss()
    {
        data.echelon();
        int i,j,k;
        int solCount = 0;

        if (data.columns-1-data.rows>0) { // inisialisasi nilai
            BigDecimal[][] matrixPeubahVal = new BigDecimal[data.rows][data.rows];
            for (i = 0; i < data.columns - 1 - data.rows; ++i) {
                for (j = 0; i < data.columns - 1; ++j) {
                    matrixPeubahVal[i][j] = BigDecimal.valueOf(0);
                }
            }
            for (i = data.rows - 1; i > 0; --i) {
                if (i == data.rows - 1) {
                    for (j = data.rows; j < data.columns - 1; ++j) {
                        matrixPeubahVal[i][j - data.rows] = data.element[i][j];
                    }
                } else {
                    for (j = i + 1; j < data.rows; ++j) {
                        for (k = 0; k < data.rows; ++k) {
//                            matrixPeubahVal[k][i] += data.element[i][j] * matrixPeubahVal[k][i-1];

                        }
                    }
                }
            }



        /*
        for(i=data.getColumns()-2 ; i>=0 ; --i){
            if (i > data.rows){
                String value = "";
                value += "x" + (i + 1) + " = x"+(i+1);
                solution[solCount] = value;
                solCount++;
            }
            else {
                String value = "";
                value += "x" + (i + 1);
                value += " = ";
                if (data.element[i][data.columns-1].compareTo(BigDecimal.ZERO)!=0) {
                    value += data.element[i][data.columns - 1];
                }
                for (j = data.rows; j < data.columns - 1; j++) {
                    if (data.element[i][j].compareTo(BigDecimal.ZERO) != 0 && !value.equals("x" + (i + 1) + " = ")) {
                        v


                        /*
                        if (data.element[i][j].compareTo(BigDecimal.ZERO) > 0) {
                            matrix
                            value += " - " + data.element[i][j] + "x" + (j + 1);
                        } else if (data.element[i][j].compareTo(BigDecimal.ZERO) < 0) {
                            value += " + " + (-1 * data.element[i][j].doubleValue()) + "x" + (j + 1);
                        }

                    }
                    else if (data.element[i][j].compareTo(BigDecimal.ZERO) != 0 && value.equals("x" + (i + 1) + " = ")){
                        if (data.element[i][j].compareTo(BigDecimal.ZERO) > 0) {
                            value += "-" + data.element[i][j] + "x" + (j + 1);
                        } else if (data.element[i][j].compareTo(BigDecimal.ZERO) < 0) {
                            value += (-1 * data.element[i][j].doubleValue()) + "x" + (j + 1);
                        }

                    }
            }
        }*/
        }
    }

    public void hitungGaussJordan()
    {
        data.reducedEchelon();
        data.printMatrix();
        int i,j;
        int solCount = 0;


        //if (data.getLeadingElmt(data.get).compareTo()) {
        for(i = 0; i < data.getColumns()-1; i++) {
            if (i<data.rows) {
                String value = "";
                value += "x" + (i + 1);
                value += " = ";
                if (data.element[i][data.columns-1].compareTo(BigDecimal.ZERO)!=0) {
                    value += data.element[i][data.columns - 1];
                }
                for (j = data.rows; j < data.columns - 1; j++) {
                    if (data.element[i][j].compareTo(BigDecimal.ZERO) != 0 && !value.equals("x" + (i + 1) + " = ")) {
                        if (data.element[i][j].compareTo(BigDecimal.ZERO) > 0) {
                            value += " - " + data.element[i][j] + "x" + (j + 1);
                        } else if (data.element[i][j].compareTo(BigDecimal.ZERO) < 0) {
                            value += " + " + (-1 * data.element[i][j].doubleValue()) + "x" + (j + 1);
                        }
                    }
                    else if (data.element[i][j].compareTo(BigDecimal.ZERO) != 0 && value.equals("x" + (i + 1) + " = ")){
                        if (data.element[i][j].compareTo(BigDecimal.ZERO) > 0) {
                            value += "-" + data.element[i][j] + "x" + (j + 1);
                        } else if (data.element[i][j].compareTo(BigDecimal.ZERO) < 0) {
                            value += (-1 * data.element[i][j].doubleValue()) + "x" + (j + 1);
                        }

                    }
                        /*
                        else if(i != data.getColumns()-2)
                        {
                            value += "+";
                        }*/
                }
                solution[solCount] = value;
                solCount++;
            }
            else {
                String value = "";
                value += "x" + (i + 1) + " = x"+(i+1);
                solution[solCount] = value;
                solCount++;
            }
        }

        for (i=0;i<solution.length;++i){
            System.out.println(solution[i]);
        }
        //}
    }

    /*
    public void hitungKramer() {
        int i, j, k, l;
        BDMatrix matrixA = new BDMatrix(data.rows, data.rows);
        BDMatrix matrixB = new BDMatrix(data.rows, 1);
        BDMatrix matrixX = new BDMatrix(data.rows, 1);
        BDMatrix detArray = new BDMatrix(1, data.columns);

        // Matrix B
        for (i = 0; i < matrixB.rows; ++i) {
            for (j = 0; j < matrixB.columns; ++j) {
                matrixB.element[i][j] = data.element[i][data.columns - 1];
            }
        }

        // Matrix A dan determinannya
        for (i = 0; i < matrixA.rows; ++i) {
            for (j = 0; j < matrixA.columns; ++j) {
                matrixA.element[i][j] = data.element[i][j];
            }
        }

        BigDecimal detA = new BDDeterminan(matrixA).hitungDeterminanOBE(matrixA).stripTrailingZeros();

        // Pencarian nilai determinan per column replace dari matrix A

        for (i = 0; i < matrixA.rows; ++i) {
            for (j = 0; j < matrixA.rows; ++j) {
                for (k = 0; k < matrixA.columns; ++k) {
                    matrixA.element[j][k] = original.element[j][k];
                }
            }
            matrixA.replaceColumn(matrixB, i);
            solution[i] = (new BDDeterminan(matrixA).hitungDeterminanOBE(matrixA).stripTrailingZeros()).divide(detA).stripTrailingZeros().toPlainString();
            System.out.println(solution[i]);
        }
    }
    *
     */

    public void hitungInvers()
    {
        int i,j;
        Inverse inverter = new Inverse(data);
        BDMatrix matrixB = new BDMatrix(data.rows, 1);
        BDMatrix matrixX = new BDMatrix(data.rows, 1);

        for (i = 0; i < matrixB.rows; ++i) {
            for (j = 0; j < matrixB.columns; ++j) {
                matrixB.element[i][j] = data.element[i][data.columns - 1];
            }
        }

        matrixB.printMatrix();

        data = inverter.getInverse();
        data.printMatrix();

        matrixX.element = data.dotMatrix(matrixB);
        matrixX.printMatrix();

        for (i = 0; i < matrixX.rows; ++i) {
            for (j = 0; j < matrixX.columns; ++j) {
                solution[i] = matrixX.element[i][j].stripTrailingZeros().toPlainString();
            }
        }

    }

    private void calcSPL()
    {
        // Strip rows of their leading ones
        for(int i = 0; i < solutionMatrix.rows; i++)
        {
            solutionMatrix.setElmt(i, solutionMatrix.getLeadingIndex(i), BigDecimal.ZERO);
        }
    }
}
