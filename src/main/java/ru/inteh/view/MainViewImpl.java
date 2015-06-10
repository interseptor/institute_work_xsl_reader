package ru.inteh.view;

import javafx.beans.value.*;
import javafx.collections.*;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.input.MouseEvent;
import ru.inteh.data.RowData;

import java.util.*;

import static com.google.common.collect.Maps.newHashMap;

public class MainViewImpl extends AbstractWidget implements MainView
{
    public ComboBox<String> initialFileChooser;
    public TableView<Map> table;
    public TextField forceInput;
    public TextField heightInput;
    public TextField depthInput;
    public TextField widthInput;
    public Button filterButton;

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

        filterButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent mouseEvent)
            {
                MainViewImpl.this.presenter.filterData(forceInput.getText(), heightInput.getText(), depthInput.getText(), widthInput.getText());
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
                for (int i = 0; i < rowData.getData().size(); i++)
                {
                    String data = rowData.getData().get(i);
                    TableColumn<Map, String> tableColumn = new TableColumn<Map, String>(data);
                    tableColumn.setCellValueFactory(new MapValueFactory<String>(i));
                    table.getColumns().add(tableColumn);
                }
            }
            else
            {
                Map<Integer, String> row = newHashMap();
                for (int i = 0; i < rowData.getData().size(); i++)
                {
                    row.put(i, rowData.getData().get(i));
                }
                tableData.add(row);
            }
        }
        table.setItems(tableData);
    }
}
