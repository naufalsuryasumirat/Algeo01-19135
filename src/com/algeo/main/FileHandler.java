package com.algeo.main;

import com.algeo.matrix.BDMatrix;

import java.io.*;
import java.math.BigDecimal;
import java.util.Scanner;

public class FileHandler {

    static int fileSuffix = 0;
    BDMatrix data;
    String outputString, fileAddress;

    enum Command
    {
        READ,
        WRITE
    }

    /**
     * CONSTRUCTORS
     * */

    public FileHandler(String fileAddress)
    {
        this.fileAddress = fileAddress;

    }

    public FileHandler(String fileAddress, String output)
    {
        outputString = output;
        this.fileAddress = fileAddress;
    }

    /**
     * GETTERS AND SETTERS
     * */

    public BDMatrix getData()
    {
        return this.data;
    }

    public void setOutputString(String output)
    {
        outputString = output;
    }


    /**
     * FILE READING AND WRITING
     * */

    public void readFile()
    {
        determineSize(fileAddress);
        try {
            File dataFile = new File(fileAddress);
            Scanner inputData = new Scanner(dataFile);

            for(int i = 0; i < data.getRows(); i++)
            {
                for(int j = 0; j < data.getColumns(); j++)
                {
                    data.setElmt(i, j, inputData.nextBigDecimal());
                }
            }
        }
        catch (IOException error)
        {
            System.out.println("Error writing file");
        }


    }

    public void readTestFile()
    {
        File data = new File(fileAddress);

        try {
            Scanner inputData = new Scanner(data);

            while (inputData.hasNextLine())
            {
                System.out.println(inputData.nextLine());
            }
            inputData.close();

        } catch (IOException error)
        {
            System.out.println("Error reading file");
            error.printStackTrace();
        }


    }


    public void writeFile(String fileAddress)
    {
        File writeThis = new File(fileAddress);
        try(FileWriter fileWriter = new FileWriter(writeThis)) {
            fileWriter.write(outputString);
        } catch (IOException error)
        {
            System.out.println("Error writing file");
        }
    }


    /**
     * PRIVATE METHODS
     * */

    private void determineSize(String fileAddress)
    {
        int rows = 0;
        int columns = 0;

        File input = new File(fileAddress);
        try {
            Scanner inputData = new Scanner(input);

            while(inputData.hasNextLine())
            {
                ++rows;
                inputData.nextLine();
            }
            inputData.close();

            Scanner inputDataCol = new Scanner(input);
            while(inputDataCol.hasNextBigDecimal())
            {
                columns++;
                inputDataCol.nextBigDecimal();
            }

//            System.out.println(rows + " " + columns);

            columns = columns/rows;
            inputDataCol.close();

        } catch (IOException error)
        {
            System.out.println("Error reading file");
            error.printStackTrace();
        }

        data = new BDMatrix(rows, columns);
    }
}
