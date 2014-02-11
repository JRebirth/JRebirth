package org.jrebirth.form.builder;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.effect.DropShadowBuilder;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.HBoxBuilder;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineBuilder;

public class DefaultFormBuilder implements FormBuilder {

	

	@Override
	public Node createForm() {

    	VBox rootBox = VBoxBuilder.create()
    			.spacing(10)
    			.build();
    	
    	
    	rootBox.getChildren().addAll(
    			buildVBox(),
    			buildVBox(),
    			buildVBox()
    			);
    	
        AnchorPane.setTopAnchor(rootBox, 10.0);
        AnchorPane.setLeftAnchor(rootBox, 10.0);

        return rootBox;

    }

	private VBox buildVBox() {
		final VBox contentBox = VBoxBuilder.create()
                .styleClass("Form")
                .prefWidth(200)
                
                .build();



        contentBox.getChildren().add(createField("FIELD", "km/h"));
        contentBox.getChildren().add(createLine());
        contentBox.getChildren().add(createField("Field", "km/h"));
        contentBox.getChildren().add(createLine());
        contentBox.getChildren().add(createField("Field", "km/h"));
        contentBox.getChildren().add(createLine());
        contentBox.getChildren().add(createField("Field", "km/h"));
        contentBox.getChildren().add(createLine());
        contentBox.getChildren().add(createField("Field", "km/h"));
		return contentBox;
	}

    private Node createLine() {

        return LineBuilder.create()
        		.styleClass("FormSeparator")
                .startX(0).startY(0)
                .endX(200).endY(0)
                .stroke(Color.GREY)
                .strokeWidth(1)
                .effect(DropShadowBuilder.create()
                        .offsetX(0)
                        .offsetY(1)
                        .radius(1)
                        .color(Color.web("262626"))
                        .build())
                .build();
    }

    private Node createField(final String string, final String string2) {

        final HBox box = HBoxBuilder.create()
        		.styleClass("FormRow")
        		.alignment(Pos.CENTER_LEFT)
        		.build();

        final Label fieldLabel = LabelBuilder.create()
                .text(string)
                .styleClass("FieldLabel")
                .alignment(Pos.BASELINE_LEFT)
//                .effect(DropShadowBuilder.create()
//
//                        .input(InnerShadowBuilder.create()
//
//                                .color(Color.web("dfdfdf"))
//                                .build())
//
//                        .color(Color.web("#bfbfbf"))
//                        .height(5)
//                        .offsetX(1)
//                        .offsetY(-1)
//                        .radius(1)
//                        .spread(0)
//                        .width(2)
//                        .build())

                .build();
        
        HBox.setHgrow(fieldLabel, Priority.NEVER);
        HBox.setMargin(fieldLabel, new Insets(0, 10, 0, 10));

        final Label unity = LabelBuilder.create()
                .text(string2)
                .styleClass("FieldUnity")
//                .effect(DropShadowBuilder.create()
//
//                        .color(Color.web("#262626"))
//                        .height(5)
//                        .offsetX(1)
//                        .offsetY(-1)
//                        .radius(1)
//                        .spread(0)
//                        .width(2)
//                        .build())
                .build();
        
        HBox.setHgrow(unity, Priority.NEVER);
        HBox.setMargin(unity, new Insets(0, 0, 0, 0));

        final Label fieldValue = LabelBuilder.create()
                .text("...")
                .styleClass("FieldValue")
                .build();
        
        HBox.setHgrow(fieldValue, Priority.ALWAYS);
        HBox.setMargin(fieldValue, new Insets(0, 0, 0, 0));

        box.getChildren().addAll(fieldLabel, unity, fieldValue);

        return box;
    }

}
