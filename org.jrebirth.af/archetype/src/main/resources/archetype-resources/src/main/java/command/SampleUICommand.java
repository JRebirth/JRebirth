#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.command;

import javafx.scene.SceneBuilder;
import javafx.scene.control.LabelBuilder;
import javafx.stage.Stage;
import javafx.stage.StageBuilder;
import javafx.stage.StageStyle;

import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.api.wave.WaveBean;
import org.jrebirth.af.core.command.single.ui.DefaultUIBeanCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>SampleUICommand</strong> use to update the user interface.
 * 
 * @author
 */
public final class SampleUICommand extends DefaultUIBeanCommand<WaveBean> {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(SampleUICommand.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void initCommand() {
        // You must put your initialization code here (if any)
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void perform(final Wave wave) {

        LOGGER.info("Display a pop up from JAT");

        final Stage s = new Stage();
        s.setTitle("Sample Ui Command Test");
        s.setStyle(StageStyle.DECORATED);

        Scene scene = new Scene();
        Label label = new LaBuilder.create().text("Run into JAT").build())
        scene.setRoot(label);

        s.setScene(scene);

        s.show();
        // Sample for popup => Attach owner !!!
        // getLocalFacade().getGlobalFacade().getApplication().getStage()
    }

}
