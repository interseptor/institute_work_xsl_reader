package ru.inteh.model;

import com.google.common.collect.ImmutableList;
import ru.inteh.data.RowData;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class FileTableModel extends AbstractEventDispatcher
{
    private final List<RowData> tableData = newArrayList();

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
        dispatchEvent(new FileTableDataChangeEvent(ImmutableList.copyOf(this.tableData)));
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
