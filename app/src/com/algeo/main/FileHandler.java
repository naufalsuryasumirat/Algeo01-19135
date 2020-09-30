package com.algeo.main;

import com.algeo.matrix.BDMatrix;

import java.io.*;
import java.math.BigDecimal;
import java.util.Scanner;

public class FileHandler {

    static int fileSuffix = 0;
    BDMatrix data;
    String outputString, address;

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
        address = fileAddress;
    }

    public FileHandler(String fileAddress, String output)
    {
        outputString = output;
        address = fileAddress;
    }

    /**
     * GETTERS AND SETTERS
     * */

    public BDMatrix getData()
    {
        return this.data;
    }

    /**
     * FILE READING AND WRITING
     * */
    public void doCommand(Command command)
    {
        if (command == Command.READ)
        {
            readFile(address);
        }

        if (command == Command.WRITE)
        {
            writeFile(address);
        }
    }

    public void readFile(String fileAddress)
    {
        determineSize(fileAddress);
        Scanner inputData = new Scanner(fileAddress);

        for(int i = 0; i < data.getRows(); i++)
        {
            for(int j = 0; j < data.getColumns(); j++)
            {
                data.setElmt(i, j, inputData.nextBigDecimal());
            }
        }
    }

    public void writeFile(String fileAddress)
    {
        try {
            FileWriter fileWriter = new FileWriter(fileAddress);
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
        Scanner sizeCounter = new Scanner(fileAddress);
        int rows, columns;

        rows = 0;
        columns = 0;

        while(sizeCounter.hasNextLine())
        {
            rows++;
            sizeCounter.nextLine();
        }

        while(sizeCounter.hasNextBigDecimal())
        {
            columns++;
        }
        sizeCounter.close();
        data = new BDMatrix(rows, columns);
    }
}
