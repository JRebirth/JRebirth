package org.jrebirth.af.component.ui.dock;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.RectangleBuilder;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextBuilder;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Demo extends Application {

	private Rectangle r1;
	private Rectangle r2;
	private Pane parent;
	private Pane overlay;

	@Override
	public void start(Stage primaryStage) {
		parent = new Pane();
		StackPane root = new StackPane();
		root.getChildren().addAll(parent, getOverlay());

		root.setOnMouseMoved(this::onMouseMoved);
		
		root.setOnDragOver(e-> System.out.println("root"));
		overlay.setOnDragOver(e-> {System.out.println("overlay");e.consume();});
		

		primaryStage.setScene(new Scene(root, 300, 250));
		primaryStage.show();
	}

	private void onMouseMoved(MouseEvent event) {

		double space = 5.0;

		if (event.getX() < parent.getWidth() / 3 || event.getX() > parent.getWidth() * 2 / 3) {
			// left right
			r1.setLayoutX(5);
			r1.setLayoutY(5);
			r1.setWidth((overlay.getWidth() - space * 3) / 2);
			r1.setHeight((overlay.getHeight() - space * 2) / 1);

			r2.setLayoutX(overlay.getWidth() / 2 + space / 2);
			r2.setLayoutY(5);
			r2.setWidth((overlay.getWidth() - space * 3) / 2);
			r2.setHeight((overlay.getHeight() - space * 2 / 1));

		} else {
			// topbottom
			r1.setLayoutX(5);
			r1.setLayoutY(5);
			r1.setWidth((overlay.getWidth() - space * 2) / 1);
			r1.setHeight((overlay.getHeight() - space * 3) / 2);

			r2.setLayoutX(5);
			r2.setLayoutY(overlay.getHeight() / 2 + space / 2);
			r2.setWidth((overlay.getWidth() - space * 2) / 1);
			r2.setHeight((overlay.getHeight() - space * 3) / 2);

		}
	}

	private Pane getOverlay() {

		overlay = new Pane();

		double stroke = 2.0;

		r1 = buidlRectangle(stroke);
		// r1.widthProperty().bind(p.widthProperty().subtract(space*2).divide(1));
		// r1.heightProperty().bind(p.heightProperty().subtract(space*3).divide(2));
		// r1.setLayoutX(5);
		// r1.setLayoutY(5);

		r2 = buidlRectangle(stroke);
		// r2.setLayoutX(5);
		// r2.layoutYProperty().bind(p.heightProperty().divide(2).add(space/2));
		// r2.widthProperty().bind(p.widthProperty().subtract(space*2).divide(1));
		// r2.heightProperty().bind(p.heightProperty().subtract(space*3).divide(2));

		overlay.getChildren().addAll(r1, r2);

		return overlay;
	}

	private Rectangle buidlRectangle(double stroke) {
		Rectangle r = new Rectangle();
		r.setStrokeType(StrokeType.INSIDE);
		r.setStrokeWidth(stroke);
		r.setStroke(Color.RED);
		r.setFill(Color.TRANSPARENT);
		return r;
	}

	public static void main(String[] args) {
		launch(args);
	}
}