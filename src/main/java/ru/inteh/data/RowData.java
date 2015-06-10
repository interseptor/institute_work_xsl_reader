package ru.inteh.data;

import java.util.List;

public class RowData
{
    private final boolean isHeader;
    private final List<String> data;

    public RowData(boolean isHeader, List<String> data)
    {
        this.isHeader = isHeader;
        this.data = data;
    }

    public boolean isHeader()
    {
        return isHeader;
    }

    public List<String> getData()
    {
        return data;
    }

    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }

        RowData rowData = (RowData)o;

        if (isHeader != rowData.isHeader)
        {
            return false;
        }
        if (data != null ? !data.equals(rowData.data) : rowData.data != null)
        {
            return false;
        }

        return true;
    }

    public int hashCode()
    {
        int result = (isHeader ? 1 : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }
}
