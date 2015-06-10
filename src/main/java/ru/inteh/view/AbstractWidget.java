package ru.inteh.view;

import javafx.scene.Parent;

public abstract class AbstractWidget implements Widget
{
    private Parent root;

    public final Parent getRoot()
    {
        return root;
    }

    public final void setRoot(Parent root)
    {
        if (this.root != null)
        {
            throw new IllegalStateException("Parent can be set only ones");
        }
        this.root = root;
    }
}
