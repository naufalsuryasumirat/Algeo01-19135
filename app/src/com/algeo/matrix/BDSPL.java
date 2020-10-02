package com.algeo.matrix;

import java.math.BigDecimal;

public class BDSPL {

    BDMatrix original;
    BDMatrix data;
    String[] solution;
    BDMatrix solutionMatrix;
    boolean solutionFound;

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

        calcSPL(data);
//        data.printMatrix("SOLUTION");

//        if (data.columns-1-data.rows>0) { // inisialisasi nilai
//            BigDecimal[][] matrixPeubahVal = new BigDecimal[data.rows][data.rows];
//            for (i = 0; i < data.columns - 1 - data.rows; ++i) {
//                for (j = 0; i < data.columns - 1; ++j) {
//                    matrixPeubahVal[i][j] = BigDecimal.valueOf(0);
//                }
//            }
//            for (i = data.rows - 1; i > 0; --i) {
//                if (i == data.rows - 1) {
//                    for (j = data.rows; j < data.columns - 1; ++j) {
//                        matrixPeubahVal[i][j - data.rows] = data.element[i][j];
//                    }
//                } else {
//                    for (j = i + 1; j < data.rows; ++j) {
//                        for (k = 0; k < data.rows; ++k) {
////                            matrixPeubahVal[k][i] += data.element[i][j] * matrixPeubahVal[k][i-1];
//
//                        }
//                    }
//                }
//            }



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
//        }
    }

    public void hitungGaussJordan()
    {
        data.reducedEchelon();
        data.printMatrix();
        int i,j;
        int solCount = 0;

        calcSPL(data);

//        if (data.adaSolusi()) {
//            for (i = 0; i < data.getColumns() - 1; i++) {
//                if (i < data.rows && !data.kolomkosong(i)) {
//                    String value = "";
//                    value += "x" + (i + 1);
//                    value += " = ";
//                    if (data.element[i][data.columns - 1].compareTo(BigDecimal.ZERO) != 0) {
//                        value += data.element[i][data.columns - 1];
//                    }
//                    for (j = i+1; j < data.columns - 1; j++) {
//                        if (data.element[i][j].compareTo(BigDecimal.ZERO) != 0 && !value.equals("x" + (i + 1) + " = ")) {
//                            if (data.element[i][j].compareTo(BigDecimal.ZERO) > 0) {
//                                value += " - " + data.element[i][j] + "x" + (j + 1);
//                            } else if (data.element[i][j].compareTo(BigDecimal.ZERO) < 0) {
//                                value += " + " + (-1 * data.element[i][j].doubleValue()) + "x" + (j + 1);
//                            }
//
//                        } else if (data.element[i][j].compareTo(BigDecimal.ZERO) != 0 && value.equals("x" + (i + 1) + " = ")) {
//                            if (data.element[i][j].compareTo(BigDecimal.ZERO) > 0) {
//                                value += "-" + data.element[i][j] + "x" + (j + 1);
//                            } else if (data.element[i][j].compareTo(BigDecimal.ZERO) < 0) {
//                                value += (-1 * data.element[i][j].doubleValue()) + "x" + (j + 1);
//                            }
//
//                        } //else if (data.element[i][j].compareTo(B) != 0 && data.getElmt(i,data.getColumns()-1)) {
//                        //   value += " 0";
//                        // }
//                    }
//                    solution[solCount] = value;
//                    solCount++;
//                } else {
//                    String value = "";
//                    value += "x" + (i + 1) + " = x" + (i + 1);
//                    solution[solCount] = value;
//                    solCount++;
//                }
//            }
//
//            for (i = 0; i < solution.length; ++i) {
//                System.out.println(solution[i]);
//            }
//        }
//        else {
//            solution[solCount] = "Solusi tidak ada";
//            System.out.println(solution[solCount]);
//        }

        /*
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

                        else if(i != data.getColumns()-2)
                        {
                            value += "+";
                        }
                }
      //          solution[solCount] = value;
      //          solCount++;
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
        }*/
    }


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

        BigDecimal detA = (new BDDeterminan().hitungDeterminanOBE(matrixA)).stripTrailingZeros();

        // Pencarian nilai determinan per column replace dari matrix A

        for (i = 0; i < matrixA.rows; ++i) {
            for (j = 0; j < matrixA.rows; ++j) {
                for (k = 0; k < matrixA.columns; ++k) {
                    matrixA.element[j][k] = original.element[j][k];
                }
            }
            matrixA.replaceColumn(matrixB, i);
            solution[i] = (new BDDeterminan().hitungDeterminanOBE(matrixA).stripTrailingZeros()).divide(detA).stripTrailingZeros().toPlainString();
            System.out.println(solution[i]);
        }
    }

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

    private void calcSPL(BDMatrix input)
    {
        int row = input.rows-1;
        solutionFound = false;
        while (row > 0) {
            for(int i = row; i >= 0; i--)
            {
                if(input.getLeadingIndex(i) != input.columns-1)
                {
                    solutionFound = true;
                }

                if(input.getElmt(i, input.getLeadingIndex(row)).compareTo(BigDecimal.ZERO) != 0 && i != row)
                {
                    input.multiplyRow(row, input.getElmt(i, input.getLeadingIndex(row)));
                    input.subtractRows(i, row);
                    input.divideRow(row, input.getLeadingElmt(row));
                }
            }
            row--;

            if(input.getLeadingIndex(0) == input.columns-1)
            {
                input.setElmt(0, input.columns-1, BigDecimal.ZERO);
            }
        }

        // CONVERTING INPUT INTO SOLUTION MATRIX FORM
        BDMatrix solutionData = new BDMatrix(input.columns-1, input.columns);

        for(int i = 0; i < input.rows; i++)
        {
            if(input.getLeadingIndex(i) != input.columns-1)
            {
                solutionData.setElmt(input.getLeadingIndex(i), 0, input.getElmt(i, input.columns-1));

                int tempIterator = input.getLeadingIndex(i)+1;
                while(tempIterator < input.columns-1)
                {
                    solutionData.setElmt(input.getLeadingIndex(i), tempIterator+1, input.getElmt(i, tempIterator).multiply(BigDecimal.valueOf(-1)));
                    tempIterator++;
                }
            }
        }
        solutionMatrix = solutionData;
        solutionData.printMatrix("THIS IS PROPOSED SOLUTION DATA");
        parseSolutionMatrix();
    }

    private void parseSolutionMatrix()
    {
        if (solutionFound)
        {
            solution = new String[solutionMatrix.rows];
            for(int i = 0; i < solutionMatrix.rows; i++)
            {
                String value = "";
                value += "x" + (i+1) + " =";

                if(solutionMatrix.isAllZero(i))
                {
                    value += " x" + (i+1);
                } else {
                    value += parseRow(solutionMatrix.getRow(i), solutionMatrix.columns);
                }

                System.out.println(value);
                solution[i] = value;
            }
        } else {
            solution = new String[1];
            solution[0] = "Solusi tidak ada";
        }
    }

    private String parseRow(BigDecimal[] row, int length)
    {
        String result = "";
        boolean moreThanOneVar = false;

        for(int i = 0; i < length; i++)
        {
            if(row[i].compareTo(BigDecimal.ZERO) != 0)
            {
                if (moreThanOneVar)
                {
                    result += " + ";
                } else {
                    result += " ";
                }

                if(i == 0)
                {
                    result += row[0];
                    moreThanOneVar = true;
                } else {
                    result += row[i].stripTrailingZeros() + "x" + (i+1);
                    moreThanOneVar = true;
                }
            }
        }

        return result;
    }

}