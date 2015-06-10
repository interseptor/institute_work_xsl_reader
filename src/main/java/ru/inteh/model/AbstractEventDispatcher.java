package ru.inteh.model;

import com.google.common.eventbus.EventBus;

public class AbstractEventDispatcher
{
    private final EventBus eventBus;

    public AbstractEventDispatcher()
    {
        eventBus = new EventBus();
    }

    public void register(Object listener)
    {
        eventBus.register(listener);
    }

    protected void dispatchEvent(Object event)
    {
        eventBus.post(event);
    }
}
