package org.jrebirth.af.core.ui.adapter;

import javafx.event.ActionEvent;

import org.jrebirth.af.api.exception.CoreException;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

@Ignore("Salle be rewritten using model and view")
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
