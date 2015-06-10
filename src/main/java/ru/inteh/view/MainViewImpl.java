package ru.inteh.view;

import javafx.beans.value.*;
import javafx.collections.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;
import ru.inteh.data.RowData;

import java.util.*;

import static com.google.common.collect.Maps.newHashMap;

public class MainViewImpl extends AbstractWidget implements MainView
{
    public ComboBox<String> initialFileChooser;
    public TableView<Map> table;

    private Presenter presenter;

    public void setPresenter(Presenter presenter)
    {
        this.presenter = presenter;

        initialFileChooser.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>()
        {
            public void changed(ObservableValue observableValue, String oldValue, String newValue)
            {
                MainViewImpl.this.presenter.fileSelected(newValue);
            }
        });
    }

    public void setFilesList(List<String> filesNames)
    {
        if (filesNames.isEmpty())
        {
            return;
        }
        initialFileChooser.setItems(FXCollections.observableArrayList(filesNames));
        //initialFileChooser.setValue(filesNames.iterator().next());
    }

    public void updateTableData(List<RowData> rows)
    {
        table.getColumns().clear();
        table.getItems().clear();

        ObservableList<Map> tableData = FXCollections.observableArrayList();

        for (RowData rowData : rows)
        {
            if (rowData.isHeader())
            {
                for (String data : rowData.getData())
                {
                    TableColumn<Map, String> tableColumn = new TableColumn<Map, String>(data);
                    tableColumn.setCellValueFactory(new MapValueFactory<String>(rowData.getData().indexOf(data)));
                    table.getColumns().add(tableColumn);
                }
            }
            else
            {
                Map<Integer, String> row = newHashMap();
                for (String data : rowData.getData())
                {
                    row.put(rowData.getData().indexOf(data), data);
                }
                tableData.add(row);
            }
        }
        table.setItems(tableData);
    }
}
