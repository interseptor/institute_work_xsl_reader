package ru.inteh.view;

import ru.inteh.data.RowData;

import java.util.List;

public interface MainView extends Widget
{
    public interface Presenter
    {

        void fileSelected(String fileName);
    }

    void setPresenter(Presenter presenter);

    void setFilesList(List<String> filesNames);

    void updateTableData(List<RowData> columnDataList);
}
