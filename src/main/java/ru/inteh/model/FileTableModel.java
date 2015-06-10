package ru.inteh.model;

import com.google.common.collect.ImmutableList;
import ru.inteh.data.*;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class FileTableModel extends AbstractEventDispatcher
{
    private final List<RowData> tableData = newArrayList();
    private final List<FilterCondition> filterConditions = newArrayList();

    public void updateTableData(List<RowData> columnDataList)
    {
        if (this.tableData.equals(columnDataList))
        {
            return;
        }

        this.tableData.clear();
        this.tableData.addAll(columnDataList);
        doDispatch();
    }

    public void setFilterConditions(List<FilterCondition> filterConditions)
    {
        if (this.filterConditions.equals(filterConditions))
        {
            return;
        }

        this.filterConditions.clear();
        this.filterConditions.addAll(filterConditions);
        doDispatch();
    }

    public void register(Object listener)
    {
        super.register(listener);
        if (!tableData.isEmpty())
        {
            doDispatch();
        }
    }

    private void doDispatch()
    {
        ImmutableList.Builder<RowData> filteredDataBuilder = ImmutableList.builder();

        if (!tableData.isEmpty())
        {
            if (!filterConditions.isEmpty())
            {
                RowData headers = tableData.get(0);
                for (RowData rowData : tableData)
                {
                    if (rowData.isHeader())
                    {
                        filteredDataBuilder.add(rowData);
                        continue;
                    }

                    boolean isSatisfiesToFilteringConditions = true;
                    for (FilterCondition filterCondition : filterConditions)
                    {
                        isSatisfiesToFilteringConditions = isSatisfiesToFilteringConditions && filterCondition.checkConditionOnData(headers, rowData);
                    }
                    if (isSatisfiesToFilteringConditions)
                    {
                        filteredDataBuilder.add(rowData);
                    }
                }
            }
            else
            {
                filteredDataBuilder.addAll(tableData);
            }
        }
        dispatchEvent(new FileTableDataChangeEvent(filteredDataBuilder.build()));
    }

    public static class FileTableDataChangeEvent
    {
        private final List<RowData> tableData;

        private FileTableDataChangeEvent(List<RowData> tableData)
        {
            this.tableData = tableData;
        }

        public List<RowData> getTableData()
        {
            return tableData;
        }
    }
}
