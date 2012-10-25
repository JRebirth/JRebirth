package org.jrebirth.core.component;

import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.control.Button;

import org.jrebirth.core.ui.Model;
import org.jrebirth.core.wave.WaveType;

/**
 * The class <strong>JButton</strong>.
 * 
 * @author Sébastien Bordes
 */
public class JButton extends Button {

    private final Model model;

    /**
     * 
     */
    public JButton(final Model model) {
        super();
        this.model = model;
    }

    /**
     * @param arg0
     */
    public JButton(final String arg0, final Model model) {
        super(arg0);
        this.model = model;
    }

    /**
     * @param arg0
     * @param arg1
     */
    public JButton(final String arg0, final Node arg1, final Model model) {
        super(arg0, arg1);
        this.model = model;
    }

    public void link(final Event event, final WaveType waveType) {

    }

}
