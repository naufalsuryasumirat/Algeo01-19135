package com.algeo.matrix;

import java.io.ObjectInputStream;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Arrays;

public class BDMatrix {
    // BigDecimal Matrix

    BigDecimal[][] element;
    int rows;
    int columns;

    private BigDecimal zero = new BigDecimal(0.0);

    private MathContext mc = MathContext.DECIMAL32;


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

    public void printMatrix()
    {
        for(int i= 0; i < rows; i++)
        {
            for(int j=0; j < columns; j++)
            {
                System.out.print(getElmt(i, j).stripTrailingZeros() + " ");
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

        int limit;

        if (rows > columns)
        {
            limit = columns;
        } else {
            limit = rows;
        }


        for (int i = 0; i < limit; i++)
        {
            int j = getLeadingIndex(i);
            while (j < i)
            {
//                System.out.println("Testing row " + i + " Column " + j);

                int sRow = getLeadingIndex(j);

                ratio = getLeadingElmt(i).divide(getLeadingElmt(sRow), mc);

                multiplyRow(sRow, ratio);
                subtractRows(i, sRow);

                divideRow(sRow, ratio);

                j = getLeadingIndex(i);
            }
        }

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
    }

    public void reducedEchelon()
    {
        echelon();

        for(int i = rows-1; i >= 0; i--)
        {
            int leader = getLeadingIndex(i);

            for(int j = i; j >= 0; j--)
            {
            if (getElmt(j, leader).compareTo(zero) != 0 && leader != getLeadingIndex(j))
                {
                    BigDecimal target = getElmt(j, leader);
                    multiplyRow(i, target);
                    subtractRows(j, i);
                    divideRow(i, target);

//                    printMatrix("REDUCED " + j + " by " + i);
                }

            }
        }
    }

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
