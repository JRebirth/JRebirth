package org.jrebirth.transition.slicer;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.shape.RectangleBuilder;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.service.ServiceImpl;

/**
 * The class <strong>ImageSlicerService</strong>.
 * 
 * @author Sébastien Bordes
 */
public class ImageSlicerService extends ServiceImpl {

    private final IntegerProperty columnProperty = new SimpleIntegerProperty();

    private final IntegerProperty rowProperty = new SimpleIntegerProperty();

    private final IntegerProperty tileWidthProperty = new SimpleIntegerProperty();

    private final IntegerProperty tileHeightProperty = new SimpleIntegerProperty();

    private final ObjectProperty<Image> imageProperty = new SimpleObjectProperty<Image>();

    private final List<ImageView> slices = new ArrayList<>();

    /**
     * @return Returns the slices.
     */
    public List<ImageView> getSlices() {
        return slices;
    }

    public void setColumn(Integer column) {
        columnProperty.set(column);
    }

    public Integer getColumn() {
        return columnProperty.get();
    }

    public IntegerProperty columnProperty() {
        return columnProperty;
    }

    public void setRow(Integer row) {
        rowProperty.set(row);
    }

    public Integer getRow() {
        return rowProperty.get();
    }

    public IntegerProperty rowProperty() {
        return rowProperty;
    }

    public void setTileWidth(Integer tileWidth) {
        tileWidthProperty.set(tileWidth);
    }

    public Integer getTileWidth() {
        return tileWidthProperty.get();
    }

    public IntegerProperty tileWidthProperty() {
        return tileWidthProperty;
    }

    public void setTileHeight(Integer tileHeight) {
        tileHeightProperty.set(tileHeight);
    }

    public Integer getTileHeight() {
        return tileHeightProperty.get();
    }

    public IntegerProperty tileHeightProperty() {
        return tileHeightProperty;
    }

    public void setImage(Image image) {
        imageProperty.set(image);
    }

    public Image getImage() {
        return imageProperty.get();
    }

    public ObjectProperty<Image> imageProperty() {
        return imageProperty;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void ready() throws CoreException {

    }

    public void doIt() {

        final double width = getImage().getWidth();
        final double height = getImage().getHeight();

        // StackPane sp = StackPaneBuilder.create().build();

        //
        // final List<Animation> fades = new ArrayList<>();
        //
        for (double x = 0; x < width; x += getTileWidth()) {
            for (double y = 0; y < height; y += getTileHeight()) {

                final ImageView iv = ImageViewBuilder
                        .create()
                        .image(getImage())
                        .clip(RectangleBuilder.create()
                                .x(x)
                                .y(y)
                                .width(getTileWidth())
                                .height(getTileHeight())
                                .build())
                        .opacity(1.0)
                        // .scaleX(10.0)
                        .layoutX(x)
                        .layoutY(y)
                        .build();

                slices.add(iv);
                // sp.getChildren().add(iv);
                //
                // fades.add(SequentialTransitionBuilder
                // .create()
                // .children(
                // PauseTransitionBuilder.create()
                // .duration(getRandomDuration())
                // .build(),
                // FadeTransitionBuilder.create().node(iv)
                // .fromValue(0.0).toValue(1.0)
                // .duration(getRandomDuration())
                // .build()).build());
                //
            }

        }

    }

}
