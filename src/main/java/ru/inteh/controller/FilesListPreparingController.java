package ru.inteh.controller;

import ru.inteh.model.InitialFilesModel;

import javax.inject.Inject;
import java.io.*;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static ru.inteh.Constants.WORKING_FOLDER_NAME;

public class FilesListPreparingController
{
    private final InitialFilesModel initialFilesModel;

    @Inject
    public FilesListPreparingController(InitialFilesModel initialFilesModel)
    {
        this.initialFilesModel = initialFilesModel;
    }

    @Inject
    public void setUp()
    {
        File workingDirectory = new File(WORKING_FOLDER_NAME);
        if (!workingDirectory.exists() || !workingDirectory.isDirectory())
        {
            throw new RuntimeException("Папка с рабочими файлами не найдена: " + workingDirectory.getPath());
        }
        List<String> fileNames = newArrayList();
        File[] files = workingDirectory.listFiles(new FilenameFilter()
        {
            public boolean accept(File dir, String name)
            {
                return name.toLowerCase().endsWith("xls") ||
                        name.toLowerCase().endsWith("xlsx");
            }
        });
        for (File file : files)
        {
            fileNames.add(file.getName());
        }
        initialFilesModel.setFilesList(fileNames);
    }
}
