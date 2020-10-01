package com.algeo.matrix;

public class BDRegresiLinier {

    BDMatrix data, B, X, Y;
    BDMatrix equationData;

    public BDRegresiLinier()
    {

    }

    public BDRegresiLinier(BDMatrix inputData)
    {
        data = inputData;
        X = inputData;
        Y = inputData;

        X.removeHorizontal(0, 1);
        Y.removeHorizontal(Y.getColumns()-2, 0);
        createLeastSquareNormalEquations();
        solve();
    }

    private void solve()
    {
           BDSPL solver = new BDSPL(equationData);
           solver.hitungGaussJordan();

    }

    private void createLeastSquareNormalEquations()
    {
        X = X.transpose().crossProductWith(X);
        Y = X.transpose().crossProductWith(Y);
        equationData = X;
        equationData.addHorizontal(Y);
    }

}
