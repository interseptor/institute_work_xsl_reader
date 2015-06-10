package ru.inteh.module;

import com.google.common.eventbus.EventBus;
import com.google.inject.*;
import ru.inteh.controller.*;
import ru.inteh.model.*;
import ru.inteh.presenter.MainViewPresenter;
import ru.inteh.util.ViewsInitializer;
import ru.inteh.view.MainView;

import javax.inject.Singleton;
import java.io.IOException;

public class ApplicationModule extends AbstractModule
{
    protected void configure()
    {
        //event bus
        bind(EventBus.class).asEagerSingleton();

        //models
        bind(InitialFilesModel.class).asEagerSingleton();
        bind(FileTableModel.class).asEagerSingleton();

        //controllers
        bind(FilesListPreparingController.class).asEagerSingleton();
        bind(FileDataReadController.class).asEagerSingleton();

        //presenters
        bind(MainView.Presenter.class).to(MainViewPresenter.class).asEagerSingleton();
    }

    @Provides
    @Singleton
    MainView getMainView() throws IOException
    {
        return ViewsInitializer.createView("/ru/inteh/view/MainView.fxml");
    }
}
