package com.algeo.matrix;

import java.io.ObjectInputStream;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;

public class BDMatrix {

    // BigDecimal Matrix
    BigDecimal[][] element;
    int rows;
    int columns;

    private BigDecimal zero = BigDecimal.ZERO;
    public MathContext mc = MathContext.DECIMAL32;

    /**
    * CONSTRUCTORS
    * */

    public BDMatrix()
    {
        this.rows = 0;
        this.columns = 0;
        this.element = new BigDecimal[0][0];
    }

    public BDMatrix(int row, int column)
    {
        this.rows = row;
        this.columns = column;
        this.element = new BigDecimal[row][column];

        for(BigDecimal[] re: this.element)
        {
            Arrays.fill(re, zero);
        }
    }

    public BDMatrix(int row, int column, BigDecimal initialValue)
    {
        this.rows = row;
        this.columns = column;
        this.element = new BigDecimal[row][column];

        for(BigDecimal[] re: this.element)
        {
            Arrays.fill(re, initialValue);
        }
    }

    public BDMatrix(int row, int column, BigDecimal[][] data)
    {
        this.rows = row;
        this.columns = column;
        this.element = data;
    }

    public BDMatrix(int size)
    {
        // CREATES IDENTITY MATRICES
        this.rows = size;
        this.columns = size;

        this.element = new BigDecimal[size][size];

        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {
                if (i != j)
                {
                    this.element[i][j] = zero;
                } else {
                    this.element[i][j] = BigDecimal.ONE;
                }
            }
        }
    }

    public BDMatrix(BDMatrix copyFrom)
    {
        this.rows = copyFrom.getRows();
        this.columns = copyFrom.getColumns();
        this.element = copyFrom.element;
    }




    /**
    * GETTERS AND SETTERS
    * */

    public BigDecimal getElmt(int row, int column)
    {
        return this.element[row][column];
    }

    public void setElmt(int row, int column, BigDecimal val)
    {
        element[row][column] = val;
    }

    public BigDecimal[] getRow(int row)
    {
        return element[row];
    }

    public void setRow(int row, BigDecimal[] data)
    {
        element[row] = data;
    }

    public int getRows()
    {
        return rows;
    }

    public int getColumns()
    {
        return columns;
    }

    public void setMathContext(int val)
    {
        if (val == 0)
        {
            mc = MathContext.UNLIMITED;
        } else {
            mc = MathContext.DECIMAL32;
        }
    }

    public BigDecimal getLeadingElmt(int row)
    {
        return getElmt(row, getLeadingIndex(row));
    }

    public int getLeadingIndex(int row)
    {
        int i = 0;
        while (i < columns)
        {
            if (getElmt(row, i).compareTo(zero) != 0)
            {
                return i;
            }

            i++;
        }

        return columns-1;
    }

    /**
    * PUBLIC METHODS
    * */

    /** USER INPUT */
    public void readUserMatrix()
    {
        Scanner inputData = new Scanner(System.in);
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < columns; j++)
            {
                setElmt(i, j, inputData.nextBigDecimal());
            }
        }
    }
    public void makeMatrix() { // FOR TESTING ONLY
        /** ALGORITMA **/
        Scanner scan = new Scanner(System.in);
        this.rows = scan.nextInt();
        this.columns = scan.nextInt();
        this.element = new BigDecimal[this.rows][this.columns];

        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {
                if (i != j)
                {
                    this.element[i][j] = zero;
                } else {
                    this.element[i][j] = BigDecimal.ONE;
                }
            }
        }
    }
    public void bacaMatrix() { // FOR TESTING ONLY
        /** KAMUS LOKAL **/
        int i, j;
        /** ALGORITMA **/
        Scanner scan1 = new Scanner(System.in);
        for (i = 0; i < this.rows; ++i) {
            for (j = 0; j < this.columns; ++j) {
                this.element[i][j] = scan1.nextBigDecimal();
            }
        }
    }

    public boolean adaSolusi() {
        int i,j,k;
        boolean isSolusiAda;
        isSolusiAda = true;
        for (i=0; i<this.getRows(); ++i){
            if (this.getLeadingIndex(i)==this.getColumns()-1 && this.getLeadingElmt(i).compareTo(BigDecimal.ZERO)!=0){
                return !isSolusiAda;
            }
        }
        return isSolusiAda;
    }

    public boolean kolomkosong(int idxcol){
        for (int i=0;i<rows;++i){
            if (this.getElmt(i,idxcol).compareTo(BigDecimal.ZERO)!=0) {
                return false;
            }
        }
        return true;
    }

    /** MATRIX LOGGING */
    public void printMatrix()
    {
        System.out.println(convertToString());
    }

    public void printRow(int row)
    {
        for(int i = 0; i < columns; i++)
        {
            System.out.print(getRow(row)[i]);
        }
    }

    public String convertToString()
    {
        String output = "";
        for(int i= 0; i < rows; i++)
        {
            for(int j=0; j < columns; j++)
            {
                output += getElmt(i, j).stripTrailingZeros().toPlainString() + " ";
            }
            output += "\n";
        }
        return output;
    }

    public void printMatrix(String message)
    {
        System.out.println(message);
        printMatrix();
    }

    /** MATRIX MANIPULATION */
    public void upperTri()
    {
        orderRows();
        BigDecimal ratio = new BigDecimal(0);

        int limit;

        if (rows > columns)
        {
            limit = columns;
        } else {
            limit = rows;
        }

//        printMatrix("BEFORE UPPERTRIING");

        for (int i = 0; i < limit; i++)
        {
            int j = getLeadingIndex(i);
            while (j < i)
            {
                int sRow = getLeadingIndex(j);
                ratio = getLeadingElmt(i).divide(getLeadingElmt(sRow), mc);

                multiplyRow(sRow, ratio);
                subtractRows(i, sRow);
                divideRow(sRow, ratio);

                j = getLeadingIndex(i);

//                printMatrix("PROCESSING MATRIX");
            }
        }

//        printMatrix("BEFORE ORDERING");

        if (limit == columns)
        {
            orderRows();
        }
    }

    public void echelon()
    {
        upperTri();
        for(int i = 0; i < rows; i++)
        {
            divideRow(i, getLeadingElmt(i));
        }

//        printMatrix("ECHELON");
    }

    public void reducedEchelon() {
        echelon();

        for (int i = rows - 1; i >= 0; i--) {
            int leader = getLeadingIndex(i);

            for (int j = i; j >= 0; j--) {
                if (getElmt(j, leader).compareTo(zero) != 0 && leader != getLeadingIndex(j)) {
                    BigDecimal target = getElmt(j, leader);
                    multiplyRow(i, target);
                    subtractRows(j, i);
                    divideRow(i, target);
                }
            }
        }

        // REMOVING DUPLICATE LEADING ONES FROM TOP
        int row = 0;
//        while (row < rows - 1) {
//            if (getLeadingIndex(row) == getLeadingIndex(row+1))
//            {
//                subtractRows(row+1, row);
//                divideRow(row+1, getLeadingElmt(row+1));
//                orderRows();
//            } else {
//                row++;
//            }
//        }

        // REMOVING DUPLICATE LEADING ONES FROM BOTTOM
        row = rows-1;
        while (row > 0) {
            for(int i = row; i >= 0; i--)
            {
                if(getElmt(i, getLeadingIndex(row)).compareTo(zero) != 0 && i != row)
                {
                    multiplyRow(row, getElmt(i, getLeadingIndex(row)));
                    subtractRows(i, row);
                    divideRow(row, getLeadingElmt(row));
                }
            }
            row--;
        }

        orderRows();
    }

    /** MATRIX MODIFIERS */
    public BDMatrix addNewRow(BDMatrix newRow)
    {
        BDMatrix temp = new BDMatrix(rows+1, columns);

        int i = 0;
        for(i = 0; i < rows; i++)
        {
            temp.setRow(i, getRow(i));
        }

        temp.setRow(i, newRow.getRow(0));
        return temp;
    }

    // THIS ADDS A COLUMN TO THE RIGHT
    public void addHorizontal(BDMatrix newData)
    {
        BigDecimal[][] temp = new BigDecimal[rows][columns + newData.columns];

        for(int i = 0; i < rows; i++)
        {
            System.arraycopy(getRow(i), 0, temp[i], 0, columns);
            System.arraycopy(newData.getRow(i), 0, temp[i], columns, newData.columns);
        }

        columns += newData.columns;
        element = temp;
    }

    // ACTUALLY THIS REMOVES COLUMNS
    public void removeHorizontal(int left, int right)
    {
        BigDecimal[][] temp = new BigDecimal[rows][columns - left - right];

        for(int i = 0; i < rows; i++)
        {
            System.arraycopy(getRow(i), left, temp[i], 0, columns - left - right);
        }

        columns = columns - left - right;
        element = temp;
    }

    public void replaceColumn (BDMatrix mtrx, int columns) {
        int i,j,k;
        for (i=0; i<this.rows; ++i){
            this.element[i][columns] = mtrx.element[i][0];
        }
    }

    public BDMatrix transpose()
    {
        BDMatrix result = new BDMatrix(columns, rows);

        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < columns; j++)
            {
                result.setElmt(j, i, getElmt(i, j));
            }
        }

        return result;
    }

    public void orderRows()
    {
        /*
         * Orders rows by leading
         * */
        for(int i = 0; i < rows; i++)
        {
            for(int j = i; j < rows; j++)
            {
                if (isAleadingB(i, j) == -1)
                {
                    switchRows(i, j);
                }
            }
        }
    }

    public double[][] convertToDoubleMatrix()
    {
        double[][] result = new double[rows][columns];

        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < columns; j++)
            {
                result[i][j] = getElmt(i, j).doubleValue();
            }
        }

        return result;
    }

    public BDMatrix(double[][] input)
    {
        rows = input.length;
        columns = input[0].length;
        BigDecimal[][] result = new BigDecimal[rows][columns];

        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < columns; j++)
            {
                result[i][j] = BigDecimal.valueOf(input[i][j]);
            }
        }

        element = result;
    }

    /** MATRIX ARITHMETICS */
    public BDMatrix crossProductWith(BDMatrix operand)
    {
        if(columns != operand.getRows())
        {
            return new BDMatrix();
        }

        int originalColumns = operand.columns;

        operand = operand.transpose();
        BDMatrix result = new BDMatrix(rows, originalColumns);

        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < originalColumns; j++)
            {
                result.setElmt(i, j, rowCrossSum(getRow(i), operand.getRow(j), columns));
            }
        }

        return result;
    }

    /** ROW ARITHMETICS */

    public boolean isAllZero(int row)
    {
        return (getLeadingIndex(row) == columns-1 && getLeadingElmt(row).compareTo(BigDecimal.ZERO) == 0);
    }

    public void addRows(int row1, int row2)
    {
        BigDecimal[] temp = new BigDecimal[columns];

        for(int i=0; i < columns; i++)
        {
            BigDecimal value = getElmt(row1, i).add(getElmt(row2, i), mc);
            setElmt(row1, i, value);
        }
    }

    public BigDecimal[][] dotMatrix(BDMatrix operand) {
        // Matrix M (this.element) dikali matrix 'matrix'
        int i, j, k;
        BigDecimal[][] MH = new BigDecimal[this.rows][operand.columns];

        for (i = 0; i < this.rows; ++i) {
            for (j = 0; j < operand.columns; ++j) {
                for (k=0; k<this.columns; ++k){
                    MH[i][j] = BigDecimal.valueOf(0);
                }
            }
        }

        for (i = 0; i < this.rows; ++i) {
            for (j = 0; j < operand.columns; ++j) {
                for (k=0; k<this.columns; ++k){
                    MH[i][j] = (MH[i][j]).add(this.element[i][k].multiply(operand.element[k][j]));
                }
            }
        }
        return MH;
    }

    public void subtractRows(int row1, int row2)
    {
        for(int i=0; i < columns; i++)
        {
            BigDecimal value = getElmt(row1, i).subtract(getElmt(row2, i), mc);

            if (value.abs().compareTo(new BigDecimal("1E-32")) == -1)
            {
                value = zero;
            }

            setElmt(row1, i, value);
        }

    }

    public void multiplyRow(int row, BigDecimal C)
    {
        BigDecimal value = zero;
        for(int i=0; i < columns; i++)
        {
            value = getElmt(row, i).multiply(C, mc);
            setElmt(row, i, value);
        }
    }

    public void divideRow(int row, BigDecimal C)
    {
        BigDecimal value = new BigDecimal(0);

        if(C.compareTo(zero) == 0)
        {
            return;
        }

        for(int i=0; i < columns; i++)
        {
            value = getElmt(row, i).divide(C, mc);
            setElmt(row, i, value);
        }
    }

    /**
     * PRIVATE METHODS
     * */

    /** ROW MANIPULATION */
    private void switchRows(int row1, int row2)
    {
        /*
        * Switches row1 and row2
        * */

        BigDecimal[] temp;

        temp = getRow(row1);
        setRow(row1, getRow(row2));
        setRow(row2, temp);
    }

    private int isAleadingB(int row1, int row2)
    {
        /*
        *  Returns
        *    0 if row1 and row2 are equal
        *    1 if row1 leads row2
        *   -1 if row2 leads row1
        * */
        int i = 0;

        while (i < this.columns)
        {
            if (getElmt(row1, i).compareTo(zero) != 0 && getElmt(row2, i).compareTo(zero) == 0)
            {
                return 1;
            }

            if (getElmt(row1, i).compareTo(zero) == 0 && getElmt(row2, i).compareTo(zero) != 0)
            {
                return -1;
            }

            if (getElmt(row1, i).compareTo(zero) != 0 && getElmt(row2, i).compareTo(zero) != 0)
            {
                return 0;
            }

            if (getElmt(row1, i).compareTo(zero) == 0 && getElmt(row2, i).compareTo(zero) == 0)
            {
                i++;
            }
        }

        return 0;
    }

    private BigDecimal rowCrossSum(BigDecimal[] row1, BigDecimal[] row2, int length)
    {
        BigDecimal sum = BigDecimal.ZERO;

        for(int i=0; i < length; i++)
        {

            sum = sum.add(row1[i].multiply(row2[i]));
        }

        return sum;
    }
}
