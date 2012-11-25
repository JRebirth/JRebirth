/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2013
 * Contact : sebastien.bordes@jrebirth.org
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
import org.jrebirth.core.service.ServiceBase;

/**
 * The class <strong>ImageSlicerService</strong>.
 * 
 * @author Sébastien Bordes
 */
public class ImageSlicerService extends ServiceBase {

    /** The column property. */
    private final IntegerProperty columnProperty = new SimpleIntegerProperty();

    /** The row property. */
    private final IntegerProperty rowProperty = new SimpleIntegerProperty();

    /** The tile width property. */
    private final IntegerProperty tileWidthProperty = new SimpleIntegerProperty();

    /** The tile height property. */
    private final IntegerProperty tileHeightProperty = new SimpleIntegerProperty();

    /** The image property. */
    private final ObjectProperty<Image> imageProperty = new SimpleObjectProperty<Image>();

    /** The slices. */
    private final List<ImageView> slices = new ArrayList<>();

    /**
     * Gets the slices.
     * 
     * @return Returns the slices.
     */
    public List<ImageView> getSlices() {
        return this.slices;
    }

    /**
     * Sets the column.
     * 
     * @param column the new column
     */
    public void setColumn(final Integer column) {
        this.columnProperty.set(column);
    }

    /**
     * Gets the column.
     * 
     * @return the column
     */
    public Integer getColumn() {
        return this.columnProperty.get();
    }

    /**
     * Column property.
     * 
     * @return the integer property
     */
    public IntegerProperty columnProperty() {
        return this.columnProperty;
    }

    /**
     * Sets the row.
     * 
     * @param row the new row
     */
    public void setRow(final Integer row) {
        this.rowProperty.set(row);
    }

    /**
     * Gets the row.
     * 
     * @return the row
     */
    public Integer getRow() {
        return this.rowProperty.get();
    }

    /**
     * Row property.
     * 
     * @return the integer property
     */
    public IntegerProperty rowProperty() {
        return this.rowProperty;
    }

    /**
     * Sets the tile width.
     * 
     * @param tileWidth the new tile width
     */
    public void setTileWidth(final Integer tileWidth) {
        this.tileWidthProperty.set(tileWidth);
    }

    /**
     * Gets the tile width.
     * 
     * @return the tile width
     */
    public Integer getTileWidth() {
        return this.tileWidthProperty.get();
    }

    /**
     * Tile width property.
     * 
     * @return the integer property
     */
    public IntegerProperty tileWidthProperty() {
        return this.tileWidthProperty;
    }

    /**
     * Sets the tile height.
     * 
     * @param tileHeight the new tile height
     */
    public void setTileHeight(final Integer tileHeight) {
        this.tileHeightProperty.set(tileHeight);
    }

    /**
     * Gets the tile height.
     * 
     * @return the tile height
     */
    public Integer getTileHeight() {
        return this.tileHeightProperty.get();
    }

    /**
     * Tile height property.
     * 
     * @return the integer property
     */
    public IntegerProperty tileHeightProperty() {
        return this.tileHeightProperty;
    }

    /**
     * Sets the image.
     * 
     * @param image the new image
     */
    public void setImage(final Image image) {
        this.imageProperty.set(image);
    }

    /**
     * Gets the image.
     * 
     * @return the image
     */
    public Image getImage() {
        return this.imageProperty.get();
    }

    /**
     * Image property.
     * 
     * @return the object property
     */
    public ObjectProperty<Image> imageProperty() {
        return this.imageProperty;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void ready() throws CoreException {
        // Nothing to do
    }

    /**
     * Do it.
     */
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

                this.slices.add(iv);
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
