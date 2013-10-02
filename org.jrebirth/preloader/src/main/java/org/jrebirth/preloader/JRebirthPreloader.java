package org.jrebirth.preloader;

import javafx.application.Preloader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Hello world!
 * 
 */
public class JRebirthPreloader extends Preloader
{
    ProgressBar bar;
    Stage stage;
    private Text text;

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void start(final Stage stage) throws Exception {
        this.stage = stage;
        stage.centerOnScreen();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(createPreloaderScene());
        stage.show();
    }

    private Scene createPreloaderScene() {

        final StackPane p = new StackPane();

        final ImageView logo = new ImageView(new Image("JRebirth_Title.png"));
        p.getChildren().add(logo);
        StackPane.setAlignment(logo, Pos.CENTER);

        this.bar = new ProgressBar();
        this.bar.setPrefSize(460, 20);
        p.getChildren().add(this.bar);
        StackPane.setAlignment(this.bar, Pos.BOTTOM_CENTER);
        StackPane.setMargin(this.bar, new Insets(30));

        this.text = new Text("Boooooooooooooting up");
        p.getChildren().add(this.text);
        StackPane.setAlignment(this.text, Pos.BOTTOM_CENTER);
        StackPane.setMargin(this.text, new Insets(10));

        return new Scene(p, 600, 200);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stop() throws Exception {
        // Nothing to do yet
        super.stop();
    }

    @Override
    public void handleProgressNotification(final ProgressNotification pn) {
        this.bar.setProgress(pn.getProgress());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleApplicationNotification(final PreloaderNotification n) {
        if (n instanceof MessageNotification) {
            this.text.setText(((MessageNotification) n).getMessage());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleStateChangeNotification(final StateChangeNotification event) {
        switch (event.getType()) {
            case BEFORE_LOAD:
            case BEFORE_INIT:
                event.getApplication();
                break;
            case BEFORE_START:
                this.stage.hide();
                break;
            default:
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean handleErrorNotification(final ErrorNotification arg0) {
        return super.handleErrorNotification(arg0);
    }

}
