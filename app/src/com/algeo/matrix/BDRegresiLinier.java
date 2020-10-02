package com.algeo.matrix;

import java.math.BigDecimal;

public class BDRegresiLinier {

    BDMatrix data, B, X, Y;
    BDMatrix equationData;

    public BDRegresiLinier()
    {

    }

    public BDRegresiLinier(BDMatrix inputData)
    {
        data = inputData;
        X = new BDMatrix(inputData);
        Y = new BDMatrix(inputData);

        X.removeHorizontal(0, 1);
        Y.removeHorizontal(Y.getColumns()-1, 0);

        BDMatrix constants = new BDMatrix(X.getRows(), 1, BigDecimal.ONE);

        constants.addHorizontal(X);
        X = new BDMatrix(constants);

        X.printMatrix("THIS IS X");
        Y.printMatrix("THIS IS Y");

        createLeastSquareNormalEquations();
        equationData.printMatrix("THIS IS DATA");

        solve();
    }

    private void solve()
    {
//           BDSPL solver = new BDSPL(equationData);
//           solver.hitungGaussJordan();
           equationData.reducedEchelon();
           equationData.printMatrix("SOLUTION");
    }

    private void createLeastSquareNormalEquations()
    {
        BDMatrix temp = X.transpose();

        temp.printMatrix("THIS IS TEMP");
        X.printMatrix("THIS IS ORIGINAL X");

        X = temp.crossProductWith(X);
        Y = temp.crossProductWith(Y);


        X.printMatrix("THIS IS X AFTER");

        equationData = X;

        equationData.printMatrix("THIS IS EQ DATA");

        equationData.addHorizontal(Y);

        equationData.printMatrix("THIS IS NEW EQ DATA");
    }

}
