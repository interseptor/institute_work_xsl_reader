package ru.inteh.controller;

import com.google.common.eventbus.*;
import ru.inteh.event.FileSelectedEvent;
import ru.inteh.model.FileTableModel;
import ru.inteh.service.XLSReadingService;

import javax.inject.Inject;

public class FileDataReadController
{
    private final FileTableModel fileTableModel;
    private final XLSReadingService readingService;

    @Inject
    public FileDataReadController(FileTableModel fileTableModel,
                                  XLSReadingService readingService)
    {
        this.fileTableModel = fileTableModel;
        this.readingService = readingService;
    }

    @Subscribe
    public void onFileSelect(FileSelectedEvent fileSelectedEvent)
    {
        fileTableModel.updateTableData(readingService.readFile(fileSelectedEvent.getSelectedFileName()));
    }
}
