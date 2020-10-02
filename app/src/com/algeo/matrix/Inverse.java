package com.algeo.matrix;

public class Inverse {

    BDMatrix data;

    public Inverse()
    {

    }

    public Inverse(BDMatrix input)
    {
        data = input;
    }

    public BDMatrix getInverse()
    {
        int size = data.getColumns();
        BDMatrix I = new BDMatrix(data.rows);

        data.addHorizontal(I);
        data.reducedEchelon();
        data.removeHorizontal(size, 0);

        return data;
    }

}
