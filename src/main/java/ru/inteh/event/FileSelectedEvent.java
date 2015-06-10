package ru.inteh.event;

public class FileSelectedEvent
{
    private final String selectedFileName;

    public FileSelectedEvent(String selectedFileName)
    {
        this.selectedFileName = selectedFileName;
    }

    public String getSelectedFileName()
    {
        return selectedFileName;
    }
}
