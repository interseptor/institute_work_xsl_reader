package ru.inteh.model;

import com.google.common.collect.ImmutableList;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class InitialFilesModel extends AbstractEventDispatcher
{
    private final List<String> filesList = newArrayList();

    public void setFilesList(List<String> filesList)
    {
        if (this.filesList.equals(filesList))
        {
            return;
        }
        this.filesList.clear();
        this.filesList.addAll(filesList);
        doDispatch();
    }

    public void register(Object listener)
    {
        super.register(listener);
        if (!filesList.isEmpty())
        {
            doDispatch();
        }
    }

    private void doDispatch()
    {
        dispatchEvent(new FilesListChangeEvent(ImmutableList.copyOf(this.filesList)));
    }

    public static class FilesListChangeEvent
    {
        private final List<String> filesList;

        private FilesListChangeEvent(List<String> filesList)
        {
            this.filesList = filesList;
        }

        public List<String> getFilesList()
        {
            return filesList;
        }
    }
}
