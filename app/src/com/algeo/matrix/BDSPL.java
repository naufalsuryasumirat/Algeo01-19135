package com.algeo.matrix;

import java.math.BigDecimal;

public class BDSPL {

    BDMatrix original;
    BDMatrix data;
    String[] solution;

    public BDSPL(BDMatrix input)
    {
        data = input;
        original = input;
        solution = new String[input.getColumns()-1];
    }

    public void hitungGauss()
    {
        data.echelon();

    }

    public void hitungGaussJordan()
    {
        data.reducedEchelon();

        int solCount = 0;
        for(BigDecimal[] row: data.element)
        {
            String value = "";
            int i = 0;
            for(i = 0; i < data.getColumns()-1; i++)
            {
                if(row[i].compareTo(BigDecimal.ZERO) != 0)
                {
                    value += "x" + row[i];
                }

                if(i != data.getColumns()-2)
                {
                    value += "+";
                }
            }

            value += "=";
            value += "x" + row[i];

            solution[solCount] = value;
            solCount++;
        }
    }

    public void hitungKramer()
    {

    }

    public void hitungInvers()
    {
        Inverse inverter = new Inverse(data);
        data = inverter.getInverse();
    }

    private void calcSPL()
    {

    }

}
