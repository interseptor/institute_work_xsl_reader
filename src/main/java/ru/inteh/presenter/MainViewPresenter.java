package ru.inteh.presenter;

import com.google.common.eventbus.*;
import ru.inteh.event.FileSelectedEvent;
import ru.inteh.model.*;
import ru.inteh.view.MainView;

import javax.inject.Inject;

public class MainViewPresenter implements MainView.Presenter
{
    private final EventBus eventBus;
    private final MainView mainView;
    private final InitialFilesModel initialFilesModel;
    private final FileTableModel fileTableModel;

    @Inject
    public MainViewPresenter(EventBus eventBus,
                             MainView mainView,
                             InitialFilesModel initialFilesModel,
                             FileTableModel fileTableModel)
    {
        this.eventBus = eventBus;
        this.mainView = mainView;
        this.initialFilesModel = initialFilesModel;
        this.fileTableModel = fileTableModel;

        mainView.setPresenter(this);
    }

    @Inject
    public void setUp()
    {
        initialFilesModel.register(this);
        fileTableModel.register(this);
    }

    @Subscribe
    public void onFilesListChange(InitialFilesModel.FilesListChangeEvent changeEvent)
    {
        mainView.setFilesList(changeEvent.getFilesList());
    }

    @Subscribe
    public void onFileTableDataChange(FileTableModel.FileTableDataChangeEvent changeEvent)
    {
        mainView.updateTableData(changeEvent.getTableData());
    }

    public void fileSelected(String fileName)
    {
        eventBus.post(new FileSelectedEvent(fileName));
    }
}
