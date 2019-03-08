/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2013
 * Contact : sebastien.bordes@jrebirth.org
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jrebirth.af.presentation.ui.template;

import com.sun.javafx.fxml.builder.web.WebViewBuilder;
import javafx.animation.*;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.web.WebView;
import javafx.util.Duration;
import org.jrebirth.af.api.exception.CoreException;
import org.jrebirth.af.core.resource.Resources;
import org.jrebirth.af.core.resource.image.RelImage;
import org.jrebirth.af.presentation.model.SlideContent;
import org.jrebirth.af.presentation.model.SlideItem;
import org.jrebirth.af.presentation.resources.PrezColors;
import org.jrebirth.af.presentation.resources.PrezFonts;
import org.jrebirth.af.presentation.resources.PrezImages;
import org.jrebirth.af.presentation.ui.base.AbstractSlideView;
import org.jrebirth.af.presentation.ui.base.SlideStep;

import java.util.ArrayList;
import java.util.List;

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

    private Animation slideStepAnimation;

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

        if (!model().hasStep()) {
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

        node().getChildren().addAll(/* footer, */this.slideContent, header);
    }

    /**
     * Show en aempty slide.
     */
    protected void showEmptySlide() {
        this.subSlides.add(model().getStepPosition(), null);
    }

    /**
     * Add a subslide node.
     *
     * @param defaultSubSlide the subslide node
     */
    private void addSubSlide(final Node defaultSubSlide) {

        this.subSlides.add(model().getStepPosition(), defaultSubSlide);
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
        ParallelTransition parallelTransition = new ParallelTransition();
        ScaleTransition scaleTransition = new ScaleTransition();
        scaleTransition.setNode(this.circle);
        scaleTransition.setDuration(Duration.millis(600));
        scaleTransition.setFromY(0);
        scaleTransition.setFromY(0);
        scaleTransition.setToX(1);
        scaleTransition.setToY(1);
        Timeline timeline = new Timeline();
        timeline.setDelay(Duration.millis(200));
        timeline.getKeyFrames().setAll(
                new KeyFrame(Duration.millis(0), new KeyValue(this.rectangle.widthProperty(), 0)),
                new KeyFrame(Duration.millis(600), new KeyValue(this.rectangle.widthProperty(), 90)));
        ParallelTransition parallelTransition1 = new ParallelTransition();
        RotateTransition rotateTransition = new RotateTransition();

        rotateTransition.setFromAngle(-180);
        rotateTransition.setToAngle(0);
        ScaleTransition scaleTransition1 = new ScaleTransition();
        scaleTransition1.setDuration(Duration.millis(600));
        scaleTransition1.setFromX(0);
        scaleTransition1.setFromY(0);
        scaleTransition1.setToX(1);
        scaleTransition1.setToY(1);
        parallelTransition1.setDelay(Duration.millis(400));
        parallelTransition1.getChildren().setAll(rotateTransition, scaleTransition1);
        parallelTransition.getChildren().setAll(scaleTransition, timeline, parallelTransition1);
    }

    /**
     * Build and return the header panel.
     *
     * @return the header panel
     */
    protected Node getHeaderPanel() {

        final Pane headerPane = new Pane();
        headerPane.getStyleClass().add("header");
        headerPane.setLayoutX(0.0);
        headerPane.setLayoutY(0.0);
        headerPane.minWidth(1024);
        headerPane.prefWidth(1024);
        // sp.getStyleClass().add("header");

        final Label primaryTitle = new Label();
        // .styleClass("slideTitle")
        primaryTitle.setFont(PrezFonts.SLIDE_TITLE.get());
        primaryTitle.setTextFill(PrezColors.SLIDE_TITLE.get());
        primaryTitle.setText(model().getSlide().getTitle().replaceAll("\\\\n", "\n").replaceAll("\\\\t", "\t"));
        primaryTitle.setLayoutX(40);
        primaryTitle.setLayoutY(45);
        // .style("-fx-background-color:#CCCB20")

        this.secondaryTitle = new Label();
        // .styleClass("slideTitle")
        this.secondaryTitle.setFont(PrezFonts.SLIDE_SUB_TITLE.get());
        this.secondaryTitle.setTextFill(PrezColors.SLIDE_TITLE.get());
        // .scaleX(1.5)
        // .scaleY(1.5)
        this.secondaryTitle.setLayoutX(450);
        this.secondaryTitle.setLayoutY(14);
        this.secondaryTitle.setMinWidth(450);
        // .style("-fx-background-color:#E53B20")
        this.secondaryTitle.setAlignment(Pos.CENTER_RIGHT);
        this.secondaryTitle.setTextAlignment(TextAlignment.RIGHT);

        final ImageView breizhcamp = new ImageView();
        breizhcamp.setLayoutX(680.0);
        breizhcamp.setLayoutY(-14.0);
        breizhcamp.setScaleX(0.6);
        breizhcamp.setScaleY(0.6);
        breizhcamp.setImage(PrezImages.HEADER_LOGO.get());

        final Polyline pl = new Polyline();
        pl.setStrokeWidth(3);
        pl.setStroke(Color.BLACK);
        pl.getPoints().setAll(684.0, 12.0, 946.0, 12.0, 946.0, 107.0);

        this.rectangle = new Rectangle();
        this.rectangle.setLayoutX(108.0);
        this.rectangle.setLayoutY(95.0);
        this.rectangle.setWidth(0.0); // 60.0
        this.rectangle.setHeight(14.0);
        ;
        this.rectangle.setFill(Color.web("1C9A9A"));

        this.circle = new Circle();
        this.circle.setScaleX(0);
        this.circle.setScaleY(0);
        this.circle.setLayoutX(18 + 54);
        this.circle.setLayoutY(18 + 54);
        this.circle.setRadius(54);
        this.circle.setFill(Color.web("444442"));

        this.pageLabel = new Label();
        this.pageLabel.setLayoutX(970);
        this.pageLabel.setLayoutY(18.0);
        this.pageLabel.setText(String.valueOf(model().getSlide().getPage()));
        this.pageLabel.setFont(PrezFonts.PAGE.get());
        this.pageLabel.setRotate(90.0);

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
        return Bindings.divide(model().localFacade().globalFacade().application().stage().heightProperty(), 768);
    }

    /**
     * Returns the width ratio.
     *
     * @return the width ratio
     */
    protected NumberBinding bindWidth() {
        return Bindings.divide(model().localFacade().globalFacade().application().stage().widthProperty(), 1024);
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
        this.pageLabel = new Label();
        this.pageLabel.setText(String.valueOf(model().getSlide().getPage()));
        this.pageLabel.setFont(PrezFonts.PAGE.get());

        final AnchorPane ap = new AnchorPane(this.pageLabel);
        AnchorPane.setRightAnchor(this.pageLabel, 20.0);

        final StackPane sp = new StackPane();
        sp.getStyleClass().add("footer");
        sp.setPrefHeight(35.0);
        sp.setMinHeight(Region.USE_PREF_SIZE);
        sp.setMaxHeight(Region.USE_PREF_SIZE);
        sp.getChildren().addAll(ap);

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
        if (model().getSlide().getStyle() != null) {
            vbox.getStyleClass().add(model().getSlide().getStyle());
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

            final Hyperlink link = new Hyperlink();
            link.setOpacity(1.0);
            link.setText(item.getValue());

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
            final ImageView imageViewer = new ImageView();
            imageViewer.getStyleClass().add(ITEM_CLASS_PREFIX + item.getLevel());
            imageViewer.setImage(image);
            // .effect(ReflectionBuilder.create().fraction(0.9).build())

            node = imageViewer;
        } else {

            final Text text = new Text();
            text.getStyleClass().add(ITEM_CLASS_PREFIX + item.getLevel());
            text.setText(item.getValue() == null ? "" : item.getValue());

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

        if (this.subSlides.size() >= model().getStepPosition() || this.subSlides.get(model().getStepPosition()) == null) {
            addSubSlide(buildDefaultContent(model().getContent(slideStep)));
        }
        final Node nextSlide = this.subSlides.get(model().getStepPosition());

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
        final Node nextSlide = this.subSlides.get(model().getStepPosition());

        if (this.currentSubSlide == null || nextSlide == null) {
            // No Animation
            this.currentSubSlide = nextSlide;
        } else {
            performStepAnimation(nextSlide);
        }
    }

    @Override
    public boolean isReadyForSlidesStepUpdate(final boolean isReverse) {

        if (this.slideStepAnimation != null && this.slideStepAnimation.getStatus() == Animation.Status.RUNNING) {

            this.slideStepAnimation.setRate(isReverse ? -5 : 5);

            return false;
        }
        return true;
    }

    /**
     * Create an Launch the animation between two sub slides.
     *
     * @param nextSlide the next subSlide to show
     */
    private void performStepAnimation(final Node nextSlide) {

        // setSlideLocked(true);
        this.slideStepAnimation = new ParallelTransition();
        this.slideStepAnimation.setOnFinished(new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {
                AbstractTemplateView.this.currentSubSlide = nextSlide;
                // AbstractTemplateView.this.setSlideLocked(false);
            }
        });
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.millis(400));
        translateTransition.setFromY(0);
        translateTransition.setToY(-700);
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().setAll(
                new KeyFrame(
                        Duration.millis(0),
                        new KeyValue(
                                this.currentSubSlide.visibleProperty(),
                                true)),
                new KeyFrame(
                        Duration.millis(1),
                        new KeyValue(
                                this.currentSubSlide.visibleProperty(),
                                false)));
        Timeline timeline1 = new Timeline();
        timeline1.getKeyFrames().setAll(
                new KeyFrame(
                        Duration.millis(0),
                        new KeyValue(
                                nextSlide.visibleProperty(),
                                false)),
                new KeyFrame(
                        Duration.millis(1),
                        new KeyValue(
                                nextSlide.visibleProperty(),
                                true)));
        TranslateTransition translateTransition1 = new TranslateTransition();
        translateTransition1.setDuration(Duration.millis(400));
        translateTransition1.setFromY(700);
        translateTransition1.setToY(0);
        ((ParallelTransition) this.slideStepAnimation).getChildren().setAll(
                new SequentialTransition(this.currentSubSlide, translateTransition, timeline),
                new SequentialTransition(nextSlide, timeline1, translateTransition1)
        );
        this.slideStepAnimation.play();

    }

    /**
     * @return Returns the slideContent.
     */
    protected StackPane getSlideContent() {
        return this.slideContent;
    }
}
