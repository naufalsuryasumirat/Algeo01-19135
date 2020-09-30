package com.algeo.matrix;

public class BDSPL {
    BDMatrix original;
    BDMatrix data;
    String[] solution;

    public BDSPL(BDMatrix input)
    {
        data = input;
        original = input;
    }

    public void hitungGauss()
    {
        data.echelon();
    }

    public void hitungGaussJordan()
    {
        data.reducedEchelon();
    }

    public void hitungKramer()
    {

    }

    public void hitungInvers()
    {
        Inverse inverter = new Inverse(data);
        data = inverter.getInverse();
    }
}
