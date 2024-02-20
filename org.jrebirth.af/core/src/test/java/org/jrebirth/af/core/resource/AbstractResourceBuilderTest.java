package org.jrebirth.af.core.resource;

import org.jrebirth.af.api.resource.color.ColorItem;
import org.jrebirth.af.core.resource.builder.AbstractResourceBuilder;
import org.jrebirth.af.core.resource.color.RGB255Color;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.jrebirth.af.core.resource.Resources.create;
import static org.junit.Assert.assertEquals;

public class AbstractResourceBuilderTest {

    @Test
    public void multiThreadedItemCreationTest() throws NoSuchFieldException, IllegalAccessException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(5);

        List<ColorItem> allItems = new CopyOnWriteArrayList<>();

        List<Callable<Void>> tasks = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            tasks.add(() -> {
                List<ColorItem> items = new ArrayList<>();
                for (int j = 0; j < 1000; j++) {
                    items.add(create(new RGB255Color(107, 69, 251)));
                }
                allItems.addAll(items);
                return null;
            });
        }

        // Invoke all tasks and wait for them to finish
        service.invokeAll(tasks);

        Field privateField = AbstractResourceBuilder.class.getDeclaredField("paramsMap");
        privateField.setAccessible(true);
        Map paramsMap = (Map) privateField.get(ResourceBuilders.COLOR_BUILDER);

        assertEquals(5000, allItems.size());
        assertEquals(5000, paramsMap.size());
    }
}
