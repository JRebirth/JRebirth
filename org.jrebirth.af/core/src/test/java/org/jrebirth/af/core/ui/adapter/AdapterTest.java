package org.jrebirth.af.core.ui.adapter;

import javafx.event.ActionEvent;

import org.jrebirth.af.api.exception.CoreException;
import org.junit.Before;
import org.junit.Test;

public class AdapterTest {

    private AdapterController controller;

    @Before
    public void setUp() {
        try {
            this.controller = new AdapterController(null);
        } catch (NullPointerException | CoreException e) {
        }
    }

    @Test()
    public void checkWaveTypes() {

        this.controller.getButton().fireEvent(new ActionEvent());

    }

}
