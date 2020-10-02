package com.algeo.matrix;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class BDRegresiLinier {

    BDMatrix data, B, X, Y;
    BDMatrix equationData;
    BDMatrix Xk;
    BigDecimal result;

    public BDRegresiLinier()
    {

    }

    public BDRegresiLinier(BDMatrix inputData)
    {
        data = inputData;
        processData(data);
    }

    public BigDecimal assertY(BDMatrix inputData)
    {
        BigDecimal result = BigDecimal.ZERO;
        BDMatrix equation = new BDMatrix(1, 1, BigDecimal.ONE);
        equation.addHorizontal(inputData);

        equation.crossProductWith(equationData);

        result = equation.getElmt(0, 0);
        return equation.getElmt(0, 0);
    }

    public BigDecimal readAssertY()
    {
        BDMatrix point = new BDMatrix(data.columns-1, 1);
        point.readUserMatrix();

        return assertY(point);
    }

    public void readData()
    {
        Scanner dataReader = new Scanner(System.in);
        System.out.println("INPUT N");
        int N = dataReader.nextInt();

        boolean moreInput = true;

        while(moreInput)
        {
            BDMatrix row = new BDMatrix(1, N);
            row.readUserMatrix();
            data.addNewRow(row);

            System.out.println("BACA DATA LAGI? 1 jika ya, 0 jika tidak");
            int nextData = dataReader.nextInt();

            if(nextData != 1)
            {
                moreInput = false;
            }

        }

        processData(data);

        System.out.println("INPUT Xk");
        System.out.println(readAssertY());
    }

    private void processData(BDMatrix inputData)
    {
        X = new BDMatrix(inputData);
        Y = new BDMatrix(inputData);

        X.removeHorizontal(0, 1);
        Y.removeHorizontal(Y.getColumns()-1, 0);

        BDMatrix constants = new BDMatrix(X.getRows(), 1, BigDecimal.ONE);

        constants.addHorizontal(X);
        X = new BDMatrix(constants);

        createLeastSquareNormalEquations();
        solve();
    }

    private void solve()
    {
        BDSPL solver = new BDSPL(equationData);
        solver.hitungGauss();
        B = solver.solutionMatrix;
    }

    private void createLeastSquareNormalEquations()
    {
        BDMatrix temp = X.transpose();

        X = temp.crossProductWith(X);
        Y = temp.crossProductWith(Y);

        equationData = X;
        equationData.addHorizontal(Y);
    }
}
