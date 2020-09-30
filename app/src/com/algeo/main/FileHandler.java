package com.algeo.main;

import com.algeo.matrix.BDMatrix;

import java.io.*;
import java.math.BigDecimal;
import java.util.Scanner;

public class FileHandler {

    static int fileSuffix = 0;
    BDMatrix data;
    Command com = Command.NONE;

    enum Command
    {
        READ,
        WRITE,
        NONE
    }

    /**
     * CONSTRUCTORS
     * */

    public FileHandler(String fileAddress, Command command)
    {
        if(command == Command.READ)
        {
            readFile(fileAddress);
        }

        if(command == Command.WRITE)
        {
            writeFile(fileAddress);
        }
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
            fileWriter.write(data.convertToString());
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

        while(sizeCounter.hasNextBigDecimal())
        {
            columns++;
        }

        while(sizeCounter.hasNextLine())
        {
            rows++;
        }
        sizeCounter.close();
        data = new BDMatrix(rows, columns);
    }
}
