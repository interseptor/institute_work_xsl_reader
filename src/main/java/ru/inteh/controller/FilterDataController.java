package ru.inteh.controller;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.eventbus.Subscribe;
import ru.inteh.data.FilterCondition;
import ru.inteh.event.FilterDataEvent;
import ru.inteh.model.FileTableModel;

import javax.inject.Inject;

public class FilterDataController
{
    public static final String FORCE_COLUMN_NAME = "номинальное";
    public static final String HEIGHT_COLUMN_NAME = "высота (мм)";
    public static final String DEPTH_COLUMN_NAME = "длина (мм)";
    public static final String WIDTH_COLUMN_NAME = "ширина (мм)";

    private static final DoubleValuesComparator DOUBLE_COMPARATOR = new DoubleValuesComparator();
    private static final IntValuesComparator INT_COMPARATOR = new IntValuesComparator();

    private final FileTableModel fileTableModel;

    @Inject
    public FilterDataController(FileTableModel fileTableModel)
    {
        this.fileTableModel = fileTableModel;
    }

    @Subscribe
    public void onFilterDataEvent(FilterDataEvent event)
    {
        fileTableModel.setFilterConditions(Lists.newArrayList(
                new FilterCondition(FORCE_COLUMN_NAME, event.getForce(), DOUBLE_COMPARATOR),
                new FilterCondition(HEIGHT_COLUMN_NAME, event.getHeight(), DOUBLE_COMPARATOR),
                new FilterCondition(DEPTH_COLUMN_NAME, event.getDepth(), DOUBLE_COMPARATOR),
                new FilterCondition(WIDTH_COLUMN_NAME, event.getWidth(), DOUBLE_COMPARATOR)
        ));
    }

    private static class DoubleValuesComparator implements FilterCondition.FilterConditionComparator
    {
        public boolean isSatisfiesCondition(String valueFromCondition, String valueFromData)
        {
            return Double.valueOf(valueFromCondition) <= Double.valueOf(Strings.isNullOrEmpty(valueFromData) ? "0" : valueFromData);
        }
    }

    private static class IntValuesComparator implements FilterCondition.FilterConditionComparator
    {
        public boolean isSatisfiesCondition(String valueFromCondition, String valueFromData)
        {
            return Integer.valueOf(valueFromCondition) <= Integer.valueOf(Strings.isNullOrEmpty(valueFromData) ? "0" : valueFromData);
        }
    }
}
