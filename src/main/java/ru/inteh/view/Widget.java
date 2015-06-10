package ru.inteh.view;

import javafx.scene.Parent;

public interface Widget
{
    Parent getRoot();

    void setRoot(Parent root);
}
