package com.algeo.matrix;

import java.io.ObjectInputStream;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class BDMatrix {
    // BigDecimal Matrix

    BigDecimal[][] element;
    int rows;
    int columns;

    private BigDecimal zero = new BigDecimal(0.0);

    private MathContext mc = new MathContext(0, RoundingMode.HALF_UP);

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
    }

    public BDMatrix(int row, int column, BigDecimal[][] data)
    {
        this.rows = row;
        this.columns = column;
        this.element = data;
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

    public BigDecimal getLeadingElmt(int row)
    {
        int i = 0;
        while (i < columns)
        {
            if (getElmt(row, i).compareTo(zero) != 0)
            {
                return getElmt(row, i);
            }
        }
        return zero;
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
        }

        return -1;
    }

    /**
    * PUBLIC METHODS
    * */

    public void printMatrix()
    {
        for(int i= 0; i < rows; i++)
        {
            for(int j=0; j < columns; j++)
            {
                System.out.print(getElmt(i, j));
            }

            System.out.println();
        }
    }

    public void printMatrix(String message)
    {
        System.out.println(message);
        printMatrix();
    }

    public void upperTri()
    {
        orderRows();
        BigDecimal ratio = new BigDecimal(0);

        for (int i = 0; i < rows; i++)
        {
            while (getLeadingIndex(i) < i && getLeadingIndex(i) != -1)
            {
                int sRow = getLeadingIndex(i);
                ratio = getLeadingElmt(i).divide(getLeadingElmt(sRow), mc);
                multiplyRow(sRow, ratio);

                subtractRows(i, sRow);
            }
        }
    }

    public void echelon()
    {
        upperTri();

        for(int i = 0; i < rows; i++)
        {
            divideRow(i, getLeadingElmt(i));
        }
    }

    public void reducedEchelon()
    {

    }

    public void resize()
    {

    }



    /** ROW ARITHMETICS */
    public void addRows(int row1, int row2)
    {
        BigDecimal[] temp = new BigDecimal[columns];

        for(int i=0; i < columns; i++)
        {
            BigDecimal value = getElmt(row1, i).add(getElmt(row2, i), mc);
            setElmt(row1, i, value);
        }

    }

    public void subtractRows(int row1, int row2)
    {
        for(int i=0; i < columns; i++)
        {
            BigDecimal value = getElmt(row1, i).subtract(getElmt(row2, i), mc);
            setElmt(row1, i, value);
        }

    }

    public void multiplyRow(int row, BigDecimal C)
    {
        BigDecimal value = new BigDecimal(0);
        for(int i=0; i < columns; i++)
        {
            value = getElmt(row, i).multiply(C, mc);
            setElmt(row, i, value);
        }
    }

    public void divideRow(int row, BigDecimal C)
    {
        BigDecimal value = new BigDecimal(0);
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
    private void orderRows()
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
            if (getElmt(row1, i).compareTo(zero) == 0 && getElmt(row2, i).compareTo(zero) == 0)
            {
                i++;
            }

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
        }

        return 0;
    }

}
