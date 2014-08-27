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
package org.jrebirth.af.presentation.ui.template;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.ParallelTransitionBuilder;
import javafx.animation.RotateTransitionBuilder;
import javafx.animation.ScaleTransitionBuilder;
import javafx.animation.SequentialTransitionBuilder;
import javafx.animation.TimelineBuilder;
import javafx.animation.TranslateTransitionBuilder;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.HyperlinkBuilder;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.AnchorPaneBuilder;
import javafx.scene.layout.Pane;
import javafx.scene.layout.PaneBuilder;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.StackPaneBuilder;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CircleBuilder;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.PolylineBuilder;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.RectangleBuilder;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextBuilder;
import javafx.scene.web.WebView;
import javafx.scene.web.WebViewBuilder;
import javafx.util.Duration;

import org.jrebirth.af.core.exception.CoreException;
import org.jrebirth.af.core.resource.Resources;
import org.jrebirth.af.core.resource.image.RelImage;
import org.jrebirth.af.presentation.resources.PrezColors;
import org.jrebirth.af.presentation.resources.PrezFonts;
import org.jrebirth.af.presentation.resources.PrezImages;
import org.jrebirth.af.presentation.ui.base.AbstractSlideView;
import org.jrebirth.af.presentation.ui.base.SlideStep;
import org.jrebirth.presentation.model.SlideContent;
import org.jrebirth.presentation.model.SlideItem;

/**
 * 
 * The class <strong>AbstractTemplateView</strong>.
 * 
 * The view used to display a standard template slide.
 * 
 * @author Sébastien Bordes
 * 
 * @param <M> the template model class
 * @param <N> the layout node
 * @param <C> the template controller class
 */
public abstract class AbstractTemplateView<M extends AbstractTemplateModel<?, ?, ?>, N extends AnchorPane, C extends AbstractTemplateController<?, ?>> extends
        AbstractSlideView<M, N, C> {

    /** Prefix used for css class. */
    private static final String ITEM_CLASS_PREFIX = "item";

    /** The sub title of this slide. */
    private Label secondaryTitle;

    /** The label that display the number of the page. */
    private Label pageLabel;

    /** The pane that hold the content. */
    private StackPane slideContent;

    /** The list of nodes that represent each sub slide. */
    private final List<Node> subSlides = new ArrayList<>();

    /** The current subslide node displayed. */
    private Node currentSubSlide;

    /** A lock managed by subslide. */
    // private final boolean subSlideLock = false;

    /** The transition used between subslides. */
    // private ParallelTransition subSlideTransition;

    /** The circle shape used in the top left corner. */
    private Circle circle;

    /** The rectangle shape used to underline the slide title. */
    private Rectangle rectangle;

    /**
     * Default Constructor.
     * 
     * @param model the controls view model
     * 
     * @throws CoreException if build fails
     */
    public AbstractTemplateView(final M model) throws CoreException {
        super(model);
    }

    /**
     * @return Returns the subTitle.
     */
    protected Label getSubTitle() {
        return this.secondaryTitle;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initView() {

        super.initView();

        // getRootNode().setPrefSize(1010, 750);
        // getRootNode().setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        // getRootNode().setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        this.slideContent = new StackPane();
        this.slideContent.getStyleClass().add("content");

        this.slideContent.setMinSize(952, 642);
        this.slideContent.setMaxSize(952, 642);
        this.slideContent.setPrefSize(952, 642);

        // this.slideContent.setLayoutX(240);
        // this.slideContent.setLayoutY(420);
        // this.slideContent.setMinWidth(952);
        // this.slideContent.setPrefWidth(952);
        // this.slideContent.setMaxWidth(952);
        // this.slideContent.setMinHeight(642);

        // this.slideContent.setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        // this.slideContent.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        // this.slideContent.setStyle("-fx-background-color:#000CCC");

        // Attach the properties view to the center place of the root border pane

        // final Pane bp = PaneBuilder.create().children(this.slideContent).build();
        // bp.relocate(20, 100);
        // bp.setStyle("-fx-background-color:#000CCC");
        // bp.setMinWidth(952);
        // bp.setPrefWidth(952);
        // bp.setMaxWidth(952);
        //
        // bp.setMinHeight(642);

        if (!getModel().hasStep()) {
            addSubSlide(getContentPanel());
        }

        // initialize the begin properties for the transition
        this.slideContent.setScaleX(0);
        this.slideContent.setScaleY(0);
        this.slideContent.setRotate(-180);

        final Node header = getHeaderPanel();
        // final Node footer = getFooterPanel();

        AnchorPane.setTopAnchor(header, 0.0);
        AnchorPane.setTopAnchor(this.slideContent, 109.0);
        AnchorPane.setLeftAnchor(this.slideContent, 48.0);
        // AnchorPane.setBottomAnchor(footer, 95.0);

        getRootNode().getChildren().addAll(/* footer, */this.slideContent, header);
    }

    /**
     * Show en aempty slide.
     */
    protected void showEmptySlide() {
        this.subSlides.add(getModel().getStepPosition(), null);
    }

    /**
     * Add a subslide node.
     * 
     * @param defaultSubSlide the subslide node
     */
    private void addSubSlide(final Node defaultSubSlide) {

        this.subSlides.add(getModel().getStepPosition(), defaultSubSlide);
        this.slideContent.getChildren().add(defaultSubSlide);

        StackPane.setAlignment(defaultSubSlide, Pos.CENTER);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        reload();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reload() {

        ParallelTransitionBuilder.create().children(

                // ParallelTransitionBuilder.create().children(
                ScaleTransitionBuilder
                        .create()
                        .node(this.circle)
                        .duration(Duration.millis(600))
                        .fromX(0)
                        .fromY(0)
                        .toX(1)
                        .toY(1)
                        .build(),
                TimelineBuilder.create()
                        .delay(Duration.millis(200))
                        .keyFrames(
                                new KeyFrame(Duration.millis(0), new KeyValue(this.rectangle.widthProperty(), 0)),
                                new KeyFrame(Duration.millis(600), new KeyValue(this.rectangle.widthProperty(), 90))
                        )
                        .build(),
                ParallelTransitionBuilder
                        .create().delay(Duration.millis(400))
                        .children(
                                RotateTransitionBuilder
                                        .create()
                                        .duration(Duration.millis(600))
                                        .fromAngle(-180)
                                        .toAngle(0)
                                        .build(),
                                ScaleTransitionBuilder
                                        .create()
                                        .duration(Duration.millis(600))
                                        .fromX(0)
                                        .fromY(0)
                                        .toX(1)
                                        .toY(1)
                                        .build()
                        )
                        .node(this.slideContent)
                        .build()
                )
                .build().play();
    }

    /**
     * Build and return the header panel.
     * 
     * @return the header panel
     */
    protected Node getHeaderPanel() {

        final Pane headerPane = PaneBuilder.create()
                .styleClass("header")
                .layoutX(0.0)
                .layoutY(0.0)
                .minWidth(1024)
                .prefWidth(1024)
                .build();
        // sp.getStyleClass().add("header");

        final Label primaryTitle = LabelBuilder.create()
                // .styleClass("slideTitle")
                .font(PrezFonts.SLIDE_TITLE.get())
                .textFill(PrezColors.SLIDE_TITLE.get())
                .text(getModel().getSlide().getTitle().replaceAll("\\\\n", "\n").replaceAll("\\\\t", "\t"))
                .layoutX(40)
                .layoutY(45)
                // .style("-fx-background-color:#CCCB20")
                .build();

        this.secondaryTitle = LabelBuilder.create()
                // .styleClass("slideTitle")
                .font(PrezFonts.SLIDE_SUB_TITLE.get())
                .textFill(PrezColors.SLIDE_TITLE.get())
                // .scaleX(1.5)
                // .scaleY(1.5)
                .layoutX(450)
                .layoutY(14)
                .minWidth(450)
                // .style("-fx-background-color:#E53B20")
                .alignment(Pos.CENTER_RIGHT)
                .textAlignment(TextAlignment.RIGHT)
                .build();

        final ImageView breizhcamp = ImageViewBuilder.create()
                .layoutX(680.0)
                .layoutY(-14.0)
                .scaleX(0.6)
                .scaleY(0.6)
                .image(PrezImages.HEADER_LOGO.get())
                .build();

        final Polyline pl = PolylineBuilder.create()
                .strokeWidth(3)
                .stroke(Color.BLACK)
                .points(684.0, 12.0, 946.0, 12.0, 946.0, 107.0)
                .build();

        this.rectangle = RectangleBuilder.create()
                .layoutX(108.0)
                .layoutY(95.0)
                .width(0.0) // 60.0
                .height(14.0)
                .fill(Color.web("1C9A9A"))
                .build();

        this.circle = CircleBuilder.create()
                .scaleX(0)
                .scaleY(0)
                .layoutX(18 + 54)
                .layoutY(18 + 54)
                .radius(54)
                .fill(Color.web("444442"))
                .build();

        this.pageLabel = LabelBuilder.create()
                .layoutX(970)
                .layoutY(18.0)
                .text(String.valueOf(getModel().getSlide().getPage()))
                .font(PrezFonts.PAGE.get())
                .rotate(90.0)
                .build();

        // final FlowPane fp = FlowPaneBuilder.create()
        // .orientation(Orientation.HORIZONTAL)
        // .alignment(Pos.BASELINE_CENTER)
        // .children(this.secondaryTitle)
        // // .style("-fx-background-color:#CCCCCC")
        // .build();

        headerPane.getChildren().addAll(this.circle, primaryTitle, breizhcamp, this.secondaryTitle, pl, this.rectangle, this.pageLabel);

        // AnchorPane.setLeftAnchor(primaryTitle, 40.0);
        // AnchorPane.setTopAnchor(primaryTitle, 45.0);
        //
        // AnchorPane.setRightAnchor(this.secondaryTitle, 80.0);
        // AnchorPane.setTopAnchor(primaryTitle, 20.0);

        // ap.setStyle("-fx-background-color:#002266");

        // sp.setStyle("-fx-background-color:#663366");
        // StackPane.setAlignment(ap, Pos.BOTTOM_CENTER);
        // sp.getChildren().add(ap);

        return headerPane;

    }

    /**
     * Bind node's scale properties to stage size.
     * 
     * @param node the bound node
     */
    protected void bindNode(final Node node) {
        node.scaleXProperty().bind(bindWidth());
        node.scaleYProperty().bind(bindHeight());
    }

    /**
     * Returns the height ratio.
     * 
     * @return the height ratio
     */
    protected NumberBinding bindHeight() {
        return Bindings.divide(getModel().getLocalFacade().getGlobalFacade().getApplication().getStage().heightProperty(), 768);
    }

    /**
     * Returns the width ratio.
     * 
     * @return the width ratio
     */
    protected NumberBinding bindWidth() {
        return Bindings.divide(getModel().getLocalFacade().getGlobalFacade().getApplication().getStage().widthProperty(), 1024);
    }

    /**
     * Build and return the content panel.
     * 
     * @return the content panel
     */
    protected abstract Node getContentPanel();

    /**
     * Build and return the footer panel.
     * 
     * @return the footer panel
     */
    protected Node getFooterPanel() {
        this.pageLabel = LabelBuilder.create()
                .text(String.valueOf(getModel().getSlide().getPage()))
                .font(PrezFonts.PAGE.get())
                .build();

        final AnchorPane ap = AnchorPaneBuilder.create()
                .children(this.pageLabel)
                .build();
        AnchorPane.setRightAnchor(this.pageLabel, 20.0);

        final StackPane sp = StackPaneBuilder.create()
                .styleClass("footer")
                .prefHeight(35.0)
                .minHeight(Region.USE_PREF_SIZE)
                .maxHeight(Region.USE_PREF_SIZE)
                .children(ap)
                .build();

        StackPane.setAlignment(ap, Pos.CENTER_RIGHT);

        return sp;
    }

    /**
     * Build the default content slide.
     * 
     * @param slideContent the content of the slide to build
     * 
     * @return the vbox with default content items
     */
    protected VBox buildDefaultContent(final SlideContent slideContent) {

        final VBox vbox = new VBox();
        // vbox.getStyleClass().add("content");

        // Link the class style of this slide content
        if (getModel().getSlide().getStyle() != null) {
            vbox.getStyleClass().add(getModel().getSlide().getStyle());
        }

        if (slideContent != null) {
            // Add all slide item into the vbox panel
            for (final SlideItem item : slideContent.getItem()) {
                addSlideItem(vbox, item);
            }

            // Manage the secondary title if any
            if (slideContent.getTitle() != null) {
                this.secondaryTitle.setText(slideContent.getTitle());
            }
        }
        return vbox;
    }

    /**
     * Add a slide item by managing level.
     * 
     * @param vbox the layout node
     * @param item the slide item to add
     */
    protected void addSlideItem(final VBox vbox, final SlideItem item) {

        Node node = null;
        if (item.isLink()) {

            final Hyperlink link = HyperlinkBuilder.create()
                    .opacity(1.0)
                    .text(item.getValue())
                    .build();

            link.getStyleClass().add("link" + item.getLevel());

            link.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent e) {
                    final ClipboardContent content = new ClipboardContent();
                    content.putString("http://" + ((Hyperlink) e.getSource()).getText());
                    Clipboard.getSystemClipboard().setContent(content);
                }
            });
            node = link;

        } else if (item.isHtml()) {

            final WebView web = WebViewBuilder.create()
                    .fontScale(1.4)
                    // .effect(ReflectionBuilder.create().fraction(0.4).build())
                    .build();
            web.getEngine().loadContent(item.getValue());

            VBox.setVgrow(web, Priority.NEVER);

            node = web; // StackPaneBuilder.create().children(web).style("-fx-border-width:2;-fx-border-color:#000000").build();

        } else if (item.getImage() != null) {

            final Image image = Resources.create(new RelImage(item.getImage())).get();
            final ImageView imageViewer = ImageViewBuilder.create()
                    .styleClass(ITEM_CLASS_PREFIX + item.getLevel())
                    .image(image)
                    // .effect(ReflectionBuilder.create().fraction(0.9).build())
                    .build();

            node = imageViewer;
        } else {

            final Text text = TextBuilder.create()
                    .styleClass(ITEM_CLASS_PREFIX + item.getLevel())
                    .text(item.getValue() == null ? "" : item.getValue())
                    .build();

            node = text;
        }

        if (item.getStyle() != null) {
            node.getStyleClass().add(item.getStyle());
        }

        if (item.getScale() != 1.0) {
            node.setScaleX(item.getScale());
            node.setScaleY(item.getScale());
        }

        vbox.getChildren().add(node);
    }

    /**
     * Show the slide step store which match with XML file.
     * 
     * @param slideStep the slide step to show
     */
    public void showSlideStep(final SlideStep slideStep) {

        if (this.subSlides.size() >= getModel().getStepPosition() || this.subSlides.get(getModel().getStepPosition()) == null) {
            addSubSlide(buildDefaultContent(getModel().getContent(slideStep)));
        }
        final Node nextSlide = this.subSlides.get(getModel().getStepPosition());

        if (this.currentSubSlide == null || nextSlide == null) {
            // No Animation
            this.currentSubSlide = nextSlide;
        } else {
            performStepAnimation(nextSlide);
        }

    }

    /**
     * Show a programmatic built node as a sub slide.
     * 
     * @param node the node built programmatically
     */
    protected void showCustomSlideStep(final Node node) {

        addSubSlide(node);
        final Node nextSlide = this.subSlides.get(getModel().getStepPosition());

        if (this.currentSubSlide == null || nextSlide == null) {
            // No Animation
            this.currentSubSlide = nextSlide;
        } else {
            performStepAnimation(nextSlide);
        }
    }

    /**
     * Create an Launch the animation between two sub slides.
     * 
     * @param nextSlide the next subSlide to show
     */
    private void performStepAnimation(final Node nextSlide) {

        setSlideLocked(true);
        final ParallelTransition subSlideTransition = ParallelTransitionBuilder.create()

                .onFinished(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(final ActionEvent event) {
                        AbstractTemplateView.this.currentSubSlide = nextSlide;
                        AbstractTemplateView.this.setSlideLocked(false);
                    }
                })

                .children(
                        SequentialTransitionBuilder.create()
                                .node(this.currentSubSlide)
                                .children(
                                        TranslateTransitionBuilder.create()
                                                .duration(Duration.millis(400))
                                                .fromY(0)
                                                .toY(-700)
                                                // .fromZ(-10)
                                                .build(),
                                        TimelineBuilder.create()
                                                .keyFrames(
                                                        new KeyFrame(Duration.millis(0), new KeyValue(this.currentSubSlide.visibleProperty(), true)),
                                                        new KeyFrame(Duration.millis(1), new KeyValue(this.currentSubSlide.visibleProperty(), false))
                                                )
                                                .build()
                                )

                                .build(),
                        SequentialTransitionBuilder.create()
                                .node(nextSlide)
                                .children(
                                        TimelineBuilder.create()
                                                .keyFrames(
                                                        new KeyFrame(Duration.millis(0), new KeyValue(nextSlide.visibleProperty(), false)),
                                                        new KeyFrame(Duration.millis(1), new KeyValue(nextSlide.visibleProperty(), true))
                                                )
                                                .build(),
                                        TranslateTransitionBuilder.create()
                                                .duration(Duration.millis(400))
                                                .fromY(700)
                                                .toY(0)
                                                // .fromZ(-10)
                                                .build()
                                )
                                .build()
                )
                .build();
        subSlideTransition.play();

    }

    /**
     * @return Returns the slideContent.
     */
    protected StackPane getSlideContent() {
        return this.slideContent;
    }
}
