package ru.inteh.data;

import com.google.common.base.Preconditions;

public class FilterCondition
{
    private final String fieldName;
    private final String fieldValue;
    private final FilterConditionComparator comparator;

    public FilterCondition(String fieldName, String fieldValue, FilterConditionComparator comparator)
    {
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
        this.comparator = comparator;
    }

    public boolean checkConditionOnData(RowData headers, RowData rowData)
    {
        Preconditions.checkState(headers.isHeader(), headers + " is not a header!");

        int fieldIndex = 0;
        for (String item : headers.getData())
        {
            if (item.toLowerCase().toLowerCase().contains(fieldName))
            {
                fieldIndex = headers.getData().indexOf(item);
                break;
            }
        }
        String valueFromData = rowData.getData().get(fieldIndex);

        return comparator.isSatisfiesCondition(fieldValue, valueFromData);
    }

    public interface FilterConditionComparator
    {
        boolean isSatisfiesCondition(String valueFromCondition, String valueFromData);
    }
}
