package ru.inteh;

import com.google.inject.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.inteh.module.*;

public class Main extends Application
{
    public void start(Stage primaryStage) throws Exception
    {
        Injector injector = Guice.createInjector(new ApplicationModule());

        primaryStage.setTitle("Поиск подходящего оборудования");
        primaryStage.setScene(new Scene(injector.getInstance(ApplicationLifeCycle.class).getRootView(), 900, 600));
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
