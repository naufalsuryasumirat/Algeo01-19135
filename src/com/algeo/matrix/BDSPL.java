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

//        data.printMatrix();
        calcSPL(data);
    }

    public void hitungGaussJordan()
    {
        data.reducedEchelon();
        data.printMatrix();
        int i,j;
        int solCount = 0;

        calcSPL(data);
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

    public String hitungKramerDBL(double[][] matrixAugmented){
        int i,j,k,l;
        double detA;
        String solusi = "";

        double[][] matrixA = new double[matrixAugmented.length][matrixAugmented.length];
        for (i=0;i<matrixAugmented.length;++i){
            for (j=0;j<matrixAugmented.length;++j){
                matrixA[i][j] = matrixAugmented[i][j];
            }
        }


        //Set matrix B dari matrix augmented
        double[] matrixB = new double[matrixAugmented.length];
        for (i=0;i<matrixAugmented.length;++i){
            matrixB[i] = matrixAugmented[i][matrixAugmented[0].length-1];
        }

        if (matrixAugmented.length != matrixAugmented[0].length-1){
            solusi = "Matriks A bukan matriks bujur sangkar";
            System.out.println(solusi);
            return solusi;
        }
        else {
            Determinan det = new Determinan();
            detA = det.hitungDeterminanOBE(matrixA, matrixAugmented.length);
            double[] ArrayDet = new double[matrixAugmented.length];
            for (j=0; j<matrixAugmented.length; ++j){
                // Kopi elemen matrix A ke matrix temporary
                double[][] tempMatrix = new double[matrixAugmented.length][matrixAugmented.length];
                for (k=0;k<matrixAugmented.length;++k){
                    for (l=0;l<matrixAugmented.length;++l){
                        tempMatrix[k][l] = matrixA[k][l];
                    }
                }
                // Ganti satu kolom matrix A dengan matrix B
                for (i=0;i<matrixAugmented.length;++i){
                    tempMatrix[i][j] = matrixB[i];
                }

                ArrayDet[j] = det.hitungDeterminanOBE(tempMatrix, matrixAugmented.length);
                solusi += "x"+(j+1)+" = "+(ArrayDet[j]/detA)+"\n";
            }
            return solusi;
        }
    }

    public void hitungInvers()
    {
        if(data.rows != data.columns)
        {
            solution = new String[1];
            solution[0] = "Matriks bukan bujur sangkar";
            return;
        }

        int i,j;
        Inverse inverter = new Inverse(data);
        BDMatrix matrixB = new BDMatrix(data.rows, 1);
        BDMatrix matrixX = new BDMatrix(data.rows, 1);

        for (i = 0; i < matrixB.rows; ++i) {
            for (j = 0; j < matrixB.columns; ++j) {
                matrixB.element[i][j] = data.element[i][data.columns - 1];
            }
        }

//        matrixB.printMatrix();

        data = inverter.getInverse();
//        data.printMatrix();

        matrixX.element = data.dotMatrix(matrixB);
//        matrixX.printMatrix();

        for (i = 0; i < matrixX.rows; ++i) {
            for (j = 0; j < matrixX.columns; ++j) {
                solution[i] = matrixX.element[i][j].stripTrailingZeros().toPlainString();
            }
        }
    }

    public String getSolution()
    {
        String resultString = "";
        for(String eq: solution)
        {
            resultString += eq;
            resultString += "\n";
        }

        return resultString;
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

        for(int i = 0; i < solutionData.rows; i++)
        {
            if(solutionData.getLeadingIndex(i) == solutionData.columns-1)
            {
                solutionData.setElmt(i, solutionData.getLeadingIndex(i), BigDecimal.ZERO);
            }
        }

//        solutionData.printMatrix("THIS IS SOLUTION DATA");

        solutionMatrix = solutionData;
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

//                System.out.println(value);
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
//                    if(i == row.length-1)
//                    {
//                        result += row[i].stripTrailingZeros();
//                        moreThanOneVar = true;
//                    } else {
//
//                    }
                    result += row[i].stripTrailingZeros() + "x" + (i);
                    moreThanOneVar = true;
                }
            }
        }
        return result;
    }

}