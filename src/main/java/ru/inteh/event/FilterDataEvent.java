package ru.inteh.event;

public class FilterDataEvent
{
    private final String force;
    private final String height;
    private final String depth;
    private final String width;

    public FilterDataEvent(String force, String height, String depth, String width)
    {
        this.force = force;
        this.height = height;
        this.depth = depth;
        this.width = width;
    }

    public String getForce()
    {
        return force;
    }

    public String getHeight()
    {
        return height;
    }

    public String getDepth()
    {
        return depth;
    }

    public String getWidth()
    {
        return width;
    }
}
