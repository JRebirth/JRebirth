import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.ParallelTransitionBuilder;
import javafx.animation.TranslateTransitionBuilder;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.RectangleBuilder;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * The class <strong>JRebirthAnalyzer</strong>.
 * 
 * The application that demonstrate how to use JRebirth Framework. It also allow to show all JRebirth events.
 * 
 * @author Sébastien Bordes
 * 
 */
public final class SlideTransition extends Application {

    /**
     * Application launcher.
     * 
     * @param args the command line arguments
     */
    public static void main(final String... args) {
        Application.launch(SlideTransition.class, args);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(Stage stage) throws Exception {

        Pane p = new Pane();
        Scene scene = new Scene(p, 800, 600);

        stage.setScene(scene);

        p.setMaxWidth(800);
        p.setMaxHeight(600);
        // p.getChildren().addAll(
        // RectangleBuilder.create().x(0).y(0).width(100).height(600).fill(Color.AZURE).build(),
        // RectangleBuilder.create().x(100).y(0).width(100).height(600).fill(Color.BEIGE).build(),
        // RectangleBuilder.create().x(200).y(0).width(100).height(600).fill(Color.AZURE).build(),
        // RectangleBuilder.create().x(300).y(0).width(100).height(600).fill(Color.BEIGE).build(),
        // RectangleBuilder.create().x(400).y(0).width(100).height(600).fill(Color.AZURE).build(),
        // RectangleBuilder.create().x(500).y(0).width(100).height(600).fill(Color.BEIGE).build(),
        // RectangleBuilder.create().x(600).y(0).width(100).height(600).fill(Color.AZURE).build(),
        // RectangleBuilder.create().x(700).y(0).width(100).height(600).fill(Color.BEIGE).build()
        // );

        for (int i = 0; i < 20; i++) {
            p.getChildren().add(RectangleBuilder.create().x(i * 40).y(0).width(40).height(600).fill(Color.AZURE).build());
        }
        stage.show();

        ParallelTransition st = ParallelTransitionBuilder.create().delay(Duration.seconds(1)).autoReverse(true).cycleCount(10).build();
        int i = 0;
        for (Node n : p.getChildren()) {

            st.getChildren().add(
                    TranslateTransitionBuilder.create().delay(Duration.millis((i * 1))).node(n).fromY(0).toY(1000).duration(Duration.millis(500)).interpolator(Interpolator.EASE_IN).build());
            i++;
        }

        st.play();
    }
}
