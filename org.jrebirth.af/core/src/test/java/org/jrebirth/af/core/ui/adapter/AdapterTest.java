package org.jrebirth.af.core.ui.adapter;

import javafx.event.ActionEvent;

import org.jrebirth.af.core.exception.CoreException;

import org.junit.Before;
import org.junit.Test;

public class AdapterTest {

    private AdapterController controller;

    @Before
    public void setUp() {
        try {
            controller = new AdapterController(null);
        } catch (NullPointerException | CoreException e) {
        }
    }

    @Test()
    public void checkWaveTypes() {

        controller.getButton().fireEvent(new ActionEvent());

    }

}
