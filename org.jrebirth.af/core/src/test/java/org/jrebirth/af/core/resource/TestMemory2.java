package org.jrebirth.af.core.resource;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;

import org.jrebirth.af.api.resource.color.ColorItem;
import org.jrebirth.af.core.application.ApplicationTest;
import org.jrebirth.af.core.application.FullConfApplication;
import org.jrebirth.af.core.resource.color.RGB255Color;

import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class TestMemory2 extends ApplicationTest<FullConfApplication> {

    public TestMemory2() {
        super(FullConfApplication.class);
    }

    @Test
    public void checkFullConf() {
        final int mb = 1024 * 1024;

        // Getting the runtime reference from system
        final Runtime runtime = Runtime.getRuntime();

        System.out.println("Used Memory1:"
                + (runtime.totalMemory() - runtime.freeMemory()) / mb);

        final List<Color> list = new ArrayList<>();
        for (int red = 0; red < 256; red++) {

            for (int green = 0; green < 256; green++) {

                for (int blue = 0; blue < 2; blue++) {

                    final ColorItem ci = Resources.create(new RGB255Color(0, 0, 0));
                    list.add(ci.get());

                }

            }
        }

        // ResourceBuilders.COLOR_BUILDER.get(key);

        System.out.println("Used Memory2:"
                + (runtime.totalMemory() - runtime.freeMemory()) / mb);

        callGC();

        System.out.println("Used Memory3:"
                + (runtime.totalMemory() - runtime.freeMemory()) / mb);

        list.clear();

        System.out.println("Used Memory4:"
                + (runtime.totalMemory() - runtime.freeMemory()) / mb);

        callGC();

        System.out.println("Used Memory5:"
                + (runtime.totalMemory() - runtime.freeMemory()) / mb);
    }

    /**
     * TODO To complete.
     *
     * @throws InterruptedException
     */
    private void callGC() {
        System.gc();
        try {
            Thread.sleep(2000);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
        System.gc();
    }
}
