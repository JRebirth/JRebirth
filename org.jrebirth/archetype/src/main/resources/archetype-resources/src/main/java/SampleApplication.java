#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.jrebirth.core.resource.font.FontEnum;
import org.jrebirth.core.ui.Model;
import org.jrebirth.core.wave.Wave;
import org.jrebirth.presentation.javafx.JfxFonts;
import org.jrebirth.presentation.javafx.Presentation;
import org.jrebirth.presentation.ui.stack.StackModel;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import ${groupId}.core.application.AbstractApplication;
import ${groupId}.core.ui.Model;
import ${package}.ui.SampleModel;

/**
 * The class <strong>SampleApplication</strong>.
 * 
 * @author
 */
public final class SampleApplication extends AbstractApplication<StackPane> {

    /**
     * Application launcher.
     * 
     * @param args the command line arguments
     */
    public static void main(final String... args) {
        Application.launch(SampleApplication.class, args);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<? extends Model> getFirstModelClass() {
        return SampleModel.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getApplicationTitle() {
        return "JavaFX 2.0 - Sample Application";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customizeStage(final Stage stage) {
        stage.setFullScreen(false);
        stage.setWidth(800);
        stage.setHeight(600);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customizeScene(final Scene scene) {
        scene.getStylesheets().add("style/sample.css");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<FontEnum> getFontToPreload() {
        return Arrays.asList(new FontEnum[] {
                JfxFonts.SPLASH
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Wave> getPreBootWaveList() {
        return Collections.emptyList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Wave> getPostBootWaveList() {
        return Collections.emptyList();
    }
    
}
