package org.jrebirth.presentation.ui.template;

import javafx.animation.ParallelTransitionBuilder;
import javafx.animation.RotateTransitionBuilder;
import javafx.animation.ScaleTransitionBuilder;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.FlowPaneBuilder;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.StackPaneBuilder;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextBuilder;
import javafx.scene.web.WebView;
import javafx.scene.web.WebViewBuilder;
import javafx.util.Duration;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.presentation.PrezColors;
import org.jrebirth.presentation.PrezFonts;
import org.jrebirth.presentation.model.SlideContent;
import org.jrebirth.presentation.model.SlideItem;
import org.jrebirth.presentation.ui.base.AbstractSlideView;
import org.jrebirth.presentation.ui.base.SlideStep;

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
public abstract class AbstractTemplateView<M extends AbstractTemplateModel<?, ?, ?>, N extends BorderPane, C extends AbstractTemplateController<?, ?>> extends
        AbstractSlideView<M, N, C> {

    /** The sub title of this slide. */
    private Label subTitle;

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
        return this.subTitle;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void customInitializeComponents() {

        getRootNode().setPrefSize(1010, 750);
        getRootNode().setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        getRootNode().setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        // Attach the header panel
        getRootNode().setTop(getHeaderPanel());
        BorderPane.setMargin(getRootNode().getTop(), new Insets(0, 0, 0, 0));

        // Attach the properties view to the center place of the root border pane
        getRootNode().setCenter(getContentPanel());
        BorderPane.setAlignment(getRootNode().getCenter(), Pos.CENTER);
        BorderPane.setMargin(getRootNode().getCenter(), new Insets(10, 0, 10, 0));

        // Attach footer panel
        getRootNode().setBottom(getFooterPanel());
        BorderPane.setMargin(getRootNode().getBottom(), new Insets(0, 0, 0, 0));

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void show() {

        // FadeTransitionBuilder.create()
        // .node(getRootNode().getTop())
        // .duration(Duration.millis(600))
        // .fromValue(0).toValue(0.7)
        // .build().play();

        // FadeTransitionBuilder.create()
        // .node(getRootNode().getCenter())
        // .duration(Duration.millis(600))
        // .fromValue(0)
        // .toValue(0.4)
        // .build().play();

        ParallelTransitionBuilder
                .create()
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
                .node(getRootNode().getCenter())
                .build().play();
    }

    /**
     * Build and return the header panel.
     * 
     * @return the header panel
     */
    protected Node getHeaderPanel() {

        final StackPane sp = StackPaneBuilder.create()
                .styleClass("header")
                .build();

        final AnchorPane ap = AnchorPaneBuilder.create().build();

        final Label text = LabelBuilder.create()
                // .styleClass("slideTitle")
                .font(PrezFonts.SLIDE_TITLE.get())
                .textFill(PrezColors.SLIDE_TITLE.get())
                .text(getModel().getSlide().getTitle().replaceAll("\\\\n", "\n").replaceAll("\\\\t", "\t"))
                .build();

        this.subTitle = LabelBuilder.create()
                // .styleClass("slideTitle")
                .prefHeight(40)
                .font(PrezFonts.SLIDE_TITLE.get())
                .textFill(PrezColors.SLIDE_TITLE.get())
                .scaleX(1.5)
                .scaleY(1.5)
                .alignment(Pos.BASELINE_RIGHT)
                .build();

        final FlowPane fp = FlowPaneBuilder.create()
                .orientation(Orientation.HORIZONTAL)
                .alignment(Pos.BASELINE_CENTER)
                .children(this.subTitle)
                // .style("-fx-background-color:#CCCCCC")
                .build();

        ap.getChildren().addAll(text, fp);

        AnchorPane.setLeftAnchor(text, 30.0);
        AnchorPane.setRightAnchor(fp, 30.0);

        // ap.setStyle("-fx-background-color:#002266");

        // sp.setStyle("-fx-background-color:#663366");
        StackPane.setAlignment(ap, Pos.BOTTOM_CENTER);
        sp.getChildren().add(ap);

        return sp;

    }

    /**
     * TODO To complete.
     * 
     * @param text
     */
    protected void bindNode(final Node node) {
        node.scaleXProperty().bind(bindWidth());
        node.scaleYProperty().bind(bindHeight());
    }

    /**
     * TODO To complete.
     * 
     * @return
     */
    protected NumberBinding bindHeight() {
        return Bindings.divide(getModel().getLocalFacade().getGlobalFacade().getApplication().getStage().heightProperty(), 768);
    }

    /**
     * TODO To complete.
     * 
     * @return
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
        final Label page = LabelBuilder.create()
                .text(String.valueOf(getModel().getSlideNumber()))
                .font(PrezFonts.PAGE.get())
                .build();

        final AnchorPane ap = AnchorPaneBuilder.create()
                .children(page)
                .build();
        AnchorPane.setRightAnchor(page, 20.0);

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
     * @return the vbox with default content items
     */
    protected VBox buildDefaultContent(final SlideContent slideContent) {

        if (slideContent.getTitle() != null) {
            this.subTitle.setText(slideContent.getTitle());
        }

        final VBox vbox = new VBox();
        vbox.getStyleClass().add("content");

        if (getModel().getSlide().getStyle() != null) {
            vbox.getStyleClass().add(getModel().getSlide().getStyle());
        }

        if (slideContent != null) {
            for (final SlideItem item : slideContent.getItem()) {
                addSlideItem(vbox, item);
            }
        }

        return vbox;
    }

    /**
     * Add a slide item by managing level.
     * 
     * @param vbox the layout node
     * @param item the slide item to add
     * @param level the current level od the slide item
     */
    protected void addSlideItem(final VBox vbox, final SlideItem item) {

        Node node = null;
        if (item.getLink()) {

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

        } else if (item.getHtml()) {

            final WebView web = WebViewBuilder.create()
                    .fontScale(1.4)
                    // .effect(ReflectionBuilder.create().fraction(0.4).build())
                    .build();
            web.getEngine().loadContent(item.getValue());

            VBox.setVgrow(web, Priority.NEVER);

            node = web;// StackPaneBuilder.create().children(web).style("-fx-border-width:2;-fx-border-color:#000000").build();

        } else if (item.getImage() != null) {

            final Image image = loadImage(item.getImage());
            final ImageView imageViewer = ImageViewBuilder.create()
                    .styleClass("item" + item.getLevel())
                    .image(image)
                    // .effect(ReflectionBuilder.create().fraction(0.9).build())
                    .build();

            node = imageViewer;
        } else {

            final Text text = TextBuilder.create()
                    .styleClass("item" + item.getLevel())
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
     * Show the slide step store which match with xml file.
     * 
     * @param slideStep the slide step to show
     */
    public void showSlideStep(final SlideStep slideStep) {
        getRootNode().setCenter(buildDefaultContent(getModel().getContent(slideStep)));
    }
}
