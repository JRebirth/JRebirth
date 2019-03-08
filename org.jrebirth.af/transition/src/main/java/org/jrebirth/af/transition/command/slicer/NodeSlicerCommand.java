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
package org.jrebirth.af.transition.command.slicer;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.shape.Rectangle;
import org.jrebirth.af.api.wave.contract.WaveType;
import org.jrebirth.af.core.service.DefaultService;
import org.jrebirth.af.core.wave.WBuilder;
import org.jrebirth.af.core.wave.WaveBase;
import org.jrebirth.af.transition.slicer.TransitionWaves;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * The class <strong>ImageSlicerService</strong>.
 *
 * @author Sébastien Bordes
 */
public class NodeSlicerCommand extends DefaultService {

    /** Wave type use to load events. */
    public static final WaveType DO_SLICE_NODE = WBuilder.waveType("SLICE_NODE").items(TransitionWaves.NODE).returnAction("NODE_SLICED").returnItem(TransitionWaves.SLICES);

    /** Wave type to return events loaded. */
    // public static final WaveType RE_NODE_SLICED = WaveType.create("NODE_SLICED").items(TransitionWaves.SLICES);

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(NodeSlicerCommand.class);

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
     * {@inheritDoc}
     */
    @Override
    public void initService() {

        listen(DO_SLICE_NODE);
    }

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
     * Do it.
     */
    public void sliceNode(final List<Node> nodes, final WaveBase wave) {
        final Node node = nodes.get(0);
        final long start = System.currentTimeMillis();

        if (node != null) {
            if (node instanceof ImageView) {
                this.imageProperty.set(((ImageView) node).getImage());
            } else {
                final WritableImage wi = node.snapshot(new SnapshotParameters(), null);
                this.imageProperty.set(wi);
            }
        }

        final double width = getImage().getWidth();
        final double height = getImage().getHeight();

        // StackPane sp = StackPaneBuilder.create().build();

        //
        // final List<Animation> fades = new ArrayList<>();
        //
        for (double x = 0; x < width; x += getTileWidth()) {
            for (double y = 0; y < height; y += getTileHeight()) {

                // TODO OPTIMIZE
                final ImageView iv = new ImageView();
                iv.setImage(getImage());
                Rectangle rectangle = new Rectangle();
                rectangle.setX(x);
                rectangle.setY(y);
                rectangle.setWidth(getTileWidth());
                rectangle.setHeight(getTileHeight());
                iv.setClip(rectangle);
                iv.setOpacity(1.0);
                iv.setScaleX(0.9); // TODO
                iv.setLayoutX(x);
                iv.setLayoutY(y);

                this.slices.add(iv);
            }
        }

        final long sliced = System.currentTimeMillis() - start;
        System.out.println("Sliced in " + sliced + " ms");

        LOGGER.debug("Sliced in {} ms", sliced);
    }
}
