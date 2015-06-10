package ru.inteh.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import ru.inteh.view.Widget;

import java.io.IOException;
import java.net.URL;

public class ViewsInitializer
{
    private ViewsInitializer()
    {
    }

    public static <View extends Widget> View createView(String pathToFXML)
    {
        URL url = ViewsInitializer.class.getResource(pathToFXML);
        FXMLLoader mainViewLoader = new FXMLLoader();
        mainViewLoader.setLocation(url);
        Parent parent = null;
        try
        {
            parent = (Parent) mainViewLoader.load(url.openStream());
        }
        catch (IOException e)
        {
            throw new RuntimeException("View can not be created", e);
        }
        View view = mainViewLoader.getController();
        view.setRoot(parent);
        return view;
    }
}
