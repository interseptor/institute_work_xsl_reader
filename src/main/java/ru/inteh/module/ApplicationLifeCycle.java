package ru.inteh.module;

import com.google.common.eventbus.EventBus;
import javafx.scene.Parent;
import ru.inteh.controller.*;
import ru.inteh.view.MainView;

import javax.inject.Inject;

public class ApplicationLifeCycle
{
    private final MainView mainView;

    @Inject
    public ApplicationLifeCycle(
            EventBus eventBus,
            MainView mainView,
            MainView.Presenter presenter,
            FileDataReadController fileDataReadController,
            FilterDataController filterDataController)
    {
        this.mainView = mainView;

        eventBus.register(fileDataReadController);
        eventBus.register(filterDataController);
    }

    public Parent getRootView()
    {
        return mainView.getRoot();
    }
}
