package com.algeo.matrix;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class BDRegresiLinier {

    BDMatrix data, B, X, Y;
    BDMatrix equationData;
    BDMatrix Xk;
    BigDecimal result;
    String equation;

    public BDRegresiLinier()
    {

    }

    public BDRegresiLinier(BDMatrix inputData)
    {
        data = inputData;
        processData(data);
    }

    public void setData(BDMatrix inputData)
    {
        data = inputData;
        processData(data);
    }

    public BigDecimal assertY(BDMatrix inputData)
    {
        this.result = BigDecimal.ZERO;

        BDMatrix equation = new BDMatrix(1, 1, BigDecimal.ONE);
        equation.addHorizontal(inputData.transpose());
        equationData.removeHorizontal(4, 0);

        for(int i = 0; i < equationData.rows; i++)
        {
            BigDecimal val = equationData.getElmt(i, 0).multiply(equation.getElmt(0, i));
            this.result = this.result.add(val);
        }

        return this.result;
    }

    public String getEquation() {

        equation = parseEquation();
        return equation;
    }

    public BigDecimal getResult() {
        return result;
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

        data = new BDMatrix(0, N+1);


        while(moreInput)
        {
            BDMatrix row = new BDMatrix(1, N+1);
            row.readUserMatrix();
            data = data.addNewRow(row);

            System.out.println("BACA DATA LAGI? 1 jika ya, 0 jika tidak");
            int nextData = dataReader.nextInt();

            if(nextData != 1)
            {
                moreInput = false;
            }
        }

        data.printMatrix();
        processData(data);

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

    private String parseEquation()
    {
        String eq = "";
        for(int i = 0; i < equationData.rows; i++)
        {
            if(i != 0)
            {
                eq += " + ";
                eq += equationData.getElmt(i, equationData.columns-1) + "x" + i;
            } else {
                eq += equationData.getElmt(i, equationData.columns-1);
            }
        }

        eq += " = y";

        return  eq;
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
