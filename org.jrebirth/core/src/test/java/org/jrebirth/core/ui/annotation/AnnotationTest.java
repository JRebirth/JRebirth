package org.jrebirth.core.ui.annotation;

import javafx.scene.Node;
import javafx.scene.input.RotateEvent;
import javafx.scene.input.SwipeEvent;

import org.jrebirth.core.application.TestApplication;
import org.jrebirth.core.concurrent.JRebirthThread;
import org.jrebirth.core.facade.GlobalFacade;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * The class <strong>AnnotationTest</strong>.
 * 
 * @author Sébastien Bordes
 */
public class AnnotationTest {

    private static GlobalFacade globalFacade;

    private AnnotationModel model;

    /**
     * TODO To complete.
     * 
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

        // new TestApplication().start(new Stage());
        JRebirthThread.getThread().launch(new TestApplication());
        globalFacade = JRebirthThread.getThread().getFacade();
    }

    /**
     * TODO To complete.
     * 
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void triggerSwipe() {

        this.model = AnnotationTest.globalFacade.getUiFacade().retrieve(AnnotationModel.class);

        fireAllSwipeEvents(this.model.getView().getSwipeVerticalButton());
        fireAllSwipeEvents(this.model.getView().getSwipeHorizontalButton());
        fireAllSwipeEvents(this.model.getView().getSwipeAllButton());
    }

    @Test
    public void triggerRotate() {

        this.model = AnnotationTest.globalFacade.getUiFacade().retrieve(AnnotationModel.class);

        fireAllRotateEvents(this.model.getView().getRotateAllButton());
        fireAllRotateEvents(this.model.getView().getRotateButton());
        fireAllRotateEvents(this.model.getView().getRotateStartFinishButton());
    }

    /**
     * To complete.
     */
    private void fireAllSwipeEvents(final Node node) {
        node.fireEvent(SwipeEvent.impl_swipeEvent(SwipeEvent.ANY,
                0, 0, 0, 0, 2, false, false, false, false, false));
        node.fireEvent(SwipeEvent.impl_swipeEvent(SwipeEvent.SWIPE_UP,
                0, 0, 0, 0, 2, false, false, false, false, false));
        node.fireEvent(SwipeEvent.impl_swipeEvent(SwipeEvent.SWIPE_DOWN,
                0, 0, 0, 0, 2, false, false, false, false, false));
        node.fireEvent(SwipeEvent.impl_swipeEvent(SwipeEvent.SWIPE_LEFT,
                0, 0, 0, 0, 2, false, false, false, false, false));
        node.fireEvent(SwipeEvent.impl_swipeEvent(SwipeEvent.SWIPE_RIGHT,
                0, 0, 0, 0, 2, false, false, false, false, false));
    }

    /**
     * To complete.
     */
    private void fireAllRotateEvents(final Node node) {
        node.fireEvent(RotateEvent.impl_rotateEvent(RotateEvent.ANY,
                0.0, 0.0, 0.0, 0.0, 2.0, 0, false, false, false, false, false, false));

        node.fireEvent(RotateEvent.impl_rotateEvent(RotateEvent.ROTATION_STARTED,
                0, 0, 0, 0, 2, 0, false, false, false, false, false, false));

        node.fireEvent(RotateEvent.impl_rotateEvent(RotateEvent.ROTATE,
                0, 0, 0, 0, 2, 0, false, false, false, false, false, false));

        node.fireEvent(RotateEvent.impl_rotateEvent(RotateEvent.ROTATION_FINISHED,
                0, 0, 0, 0, 2, 0, false, false, false, false, false, false));
    }

    /**
     * TODO To complete.
     * 
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * TODO To complete.
     * 
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        JRebirthThread.getThread().close();
    }

}
