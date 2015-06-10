package ru.inteh.service;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import ru.inteh.data.RowData;

import java.io.*;
import java.util.*;

import static com.google.common.collect.Lists.newArrayList;
import static ru.inteh.Constants.WORKING_FOLDER_NAME;

public class XLSReadingService
{
    public List<RowData> readFile(String path)
    {
        try
        {
            if (path.toLowerCase().endsWith("xlsx"))
            {
                return readXlsx(path);
            }
            else
            {
                return readXls(path);
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    private List<RowData> readXlsx(String path) throws Exception
    {
        List<List<String>> fileData = newArrayList();

        InputStream ExcelFileToRead = new FileInputStream(WORKING_FOLDER_NAME + File.separator + path);
        XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead);

        XSSFWorkbook test = new XSSFWorkbook();

        XSSFSheet sheet = wb.getSheetAt(0);
        return convertData(sheet);
    }

    private List<RowData> readXls(String path) throws Exception
    {
        InputStream ExcelFileToRead = new FileInputStream(WORKING_FOLDER_NAME + File.separator + path);
        HSSFWorkbook wb = new HSSFWorkbook(ExcelFileToRead);

        HSSFSheet sheet = wb.getSheetAt(0);
        return convertData(sheet);
    }

    private List<RowData> convertData(Iterable<Row> rows)
    {
        List<RowData> result = newArrayList();

        boolean isFirstRow = true;
        for (Row row : rows)
        {
            List<String> rowData = newArrayList();
            for (Cell cell : row)
            {
                String value;

                if (cell.getCellType() == Cell.CELL_TYPE_STRING)
                {
                    value = cell.getStringCellValue();
                }
                else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC)
                {
                    value = String.valueOf(cell.getNumericCellValue());
                }
                else
                {
                    value = "";
                    //U Can Handel Boolean, Formula, Errors
                }
                rowData.add(value);
            }
            result.add(new RowData(isFirstRow, rowData));
            isFirstRow = false;
        }

        return result;
    }
}
