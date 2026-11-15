
    @Override
    public void reload() {

        final FadeTransition primaryFade = new FadeTransition(Duration.millis(600), this.primaryTitle);
        primaryFade.setDelay(Duration.millis(1000));
        primaryFade.setFromValue(0);
        primaryFade.setToValue(1.0);

        final FadeTransition secondaryFade = new FadeTransition(Duration.millis(600), this.secondaryTitle);
        secondaryFade.setDelay(Duration.millis(1200));
        secondaryFade.setFromValue(0);
        secondaryFade.setToValue(1.0);

        final TranslateTransition eclipseLogoUp = new TranslateTransition(Duration.millis(300), this.eclipseCONLogo);
        eclipseLogoUp.setDelay(Duration.millis(1400));
        eclipseLogoUp.setToY(-500);

        final TranslateTransition jrebirthLogoUp = new TranslateTransition(Duration.millis(300), this.jrebirthLogo);
        jrebirthLogoUp.setDelay(Duration.millis(1700));
        jrebirthLogoUp.setToY(-500);

        final ParallelTransition reloadAnimation = new ParallelTransition();
        reloadAnimation.getChildren().addAll(
                buildContentAnimation(),
                buildHeaderAnimation(),
                buildPageAnimation(),
                primaryFade,
                secondaryFade,
                eclipseLogoUp,
                jrebirthLogoUp);
        reloadAnimation.play();
    }

    private Animation buildHeaderAnimation() {
        final TranslateTransition headerSlide = new TranslateTransition(Duration.millis(500), this.headerRectangle);
        headerSlide.setByX(1100);
        headerSlide.setInterpolator(Interpolator.EASE_IN);

        final TranslateTransition patternDrop = new TranslateTransition(Duration.millis(500), this.headerPatternGroup);
        patternDrop.setByY(150);
        patternDrop.setInterpolator(Interpolator.EASE_IN);

        final ParallelTransition headerAnimation = new ParallelTransition(headerSlide, patternDrop);
        headerAnimation.setDelay(Duration.millis(200));
        return headerAnimation;
    }

    private Animation buildPageAnimation() {
        final RotateTransition petalRotate = new RotateTransition(Duration.millis(500), this.page3Petals);
        petalRotate.setByAngle(1080);
        petalRotate.setInterpolator(Interpolator.EASE_IN);

        final TranslateTransition petalShift = new TranslateTransition(Duration.millis(400), this.page3Petals);
        petalShift.setByX(-200);
        petalShift.setInterpolator(Interpolator.EASE_IN);

        final ParallelTransition step1 = new ParallelTransition(petalRotate, petalShift);
        step1.setNode(this.page3Petals);

        final ScaleTransition pageZoom = new ScaleTransition(Duration.millis(400), this.pageLabel);
        pageZoom.setFromX(0);
        pageZoom.setFromY(0);
        pageZoom.setToX(1.0);
        pageZoom.setToY(1.0);

        final TranslateTransition monoPetal = new TranslateTransition(Duration.millis(300), this.page1Petal);
        monoPetal.setByX(-200);
        monoPetal.setByY(200);
        monoPetal.setInterpolator(Interpolator.EASE_IN);

        final ParallelTransition step2 = new ParallelTransition(pageZoom, monoPetal);

        final FadeTransition raysFade = new FadeTransition(Duration.millis(300), this.pageRays);
        raysFade.setFromValue(0.0);
        raysFade.setToValue(1.0);

        final FadeTransition sparkFade = new FadeTransition(Duration.millis(300), this.pageSpark);
        sparkFade.setFromValue(0.0);
        sparkFade.setToValue(1.0);

        final ScaleTransition sparkZoom = new ScaleTransition(Duration.millis(200), this.pageSpark);
        sparkZoom.setFromX(0);
        sparkZoom.setFromY(0);
        sparkZoom.setToX(1.0);
        sparkZoom.setToY(1.0);

        final ParallelTransition step4 = new ParallelTransition(sparkFade, sparkZoom);
        step4.setNode(this.pageSpark);

        final SequentialTransition pageAnimation = new SequentialTransition(step1, step2, raysFade, step4);
        pageAnimation.setDelay(Duration.millis(400));
        return pageAnimation;
    }

    private SequentialTransition buildContentAnimation() {
        final TranslateTransition translate = new TranslateTransition(Duration.millis(600), this.slideContentPane);
        translate.setFromX(1024 / 2);
        translate.setFromY(768 / 2);
        translate.setToX(0);
        translate.setToY(0);

        final ScaleTransition scale = new ScaleTransition(Duration.millis(600), this.slideContentPane);
        scale.setFromX(0);
        scale.setFromY(0);
        scale.setToX(1);
        scale.setToY(1);

        final ParallelTransition slideIn = new ParallelTransition(translate, scale);
        slideIn.setDelay(Duration.millis(100));
        slideIn.setNode(this.slideContentPane);

        return new SequentialTransition(slideIn, displayRectangle());
    }

    private Animation displayRectangle() {
        final SequentialTransition rectAnimation = new SequentialTransition();
        for (final Rectangle r : this.rectangles) {
            final FadeTransition fade = new FadeTransition(Duration.millis(200), r);
            fade.setFromValue(0.0);
            fade.setToValue(1.0);
            rectAnimation.getChildren().add(fade);
        }
        return rectAnimation;
    }

    protected void bindNode(final Node node) {
        node.scaleXProperty().bind(bindWidth());
        node.scaleYProperty().bind(bindHeight());
    }

    protected NumberBinding bindHeight() {
        return Bindings.divide(model().localFacade().getGlobalFacade().application().stage().heightProperty(), 768);
    }

    protected NumberBinding bindWidth() {
        return Bindings.divide(model().localFacade().getGlobalFacade().application().stage().widthProperty(), 1024);
    }

    protected VBox buildDefaultContent(final SlideContent slideContent) {

        if (slideContent.getTitle() != null && this.secondaryTitle != null) {
            this.secondaryTitle.setText(slideContent.getTitle());
        }

        final VBox vbox = new VBox();

        if (model().getSlide().getStyle() != null) {
            vbox.getStyleClass().add(model().getSlide().getStyle());
        }

        if (slideContent != null) {
            for (final SlideItem item : slideContent.getItem()) {
                addSlideItem(vbox, item);
            }
        }

        return vbox;
    }

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

            final WebView web = new WebView();
            web.setFontScale(1.4);
            web.getEngine().loadContent(item.getValue());

            VBox.setVgrow(web, Priority.NEVER);

            node = web;

        } else if (item.getImage() != null) {

            final Image image = Resources.create(new RelImage(item.getImage())).get();
            final ImageView imageViewer = new ImageView();
            imageViewer.getStyleClass().add(ITEM_CLASS_PREFIX + item.getLevel());
            imageViewer.setImage(image);

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

    public void showSlideStep(final SlideStep slideStep) {

        if (this.subSlides.size() >= model().getStepPosition() || this.subSlides.get(model().getStepPosition()) == null) {
            addSubSlide(buildDefaultContent(model().getContent(slideStep)));
        }
        final Node nextSlide = this.subSlides.get(model().getStepPosition());

        if (this.currentSubSlide != null && nextSlide != null) {
            performStepAnimation(nextSlide);
        } else {
            this.currentSubSlide = nextSlide;
        }

    }

    protected void showCustomSlideStep(final Node node) {

        addSubSlide(node);
        final Node nextSlide = this.subSlides.get(model().getStepPosition());
        if (this.currentSubSlide != null && nextSlide != null) {

            performStepAnimation(nextSlide);
        } else {
            this.currentSubSlide = nextSlide;
        }
    }

    private void performStepAnimation(final Node nextSlide) {

        this.subSlideTransition = new ParallelTransition();
        this.subSlideTransition.setOnFinished(new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {
                AbstractBaseView.this.currentSubSlide = nextSlide;
            }
        });

        final SequentialTransition hideCurrent = new SequentialTransition();
        hideCurrent.setNode(this.currentSubSlide);

        final TranslateTransition slideOut = new TranslateTransition(Duration.millis(400), this.currentSubSlide);
        slideOut.setFromY(0);
        slideOut.setToY(-700);

        final Timeline hideTimeline = new Timeline(
                new KeyFrame(Duration.millis(0), new KeyValue(this.currentSubSlide.visibleProperty(), true)),
                new KeyFrame(Duration.millis(1), new KeyValue(this.currentSubSlide.visibleProperty(), false)));
        hideCurrent.getChildren().addAll(slideOut, hideTimeline);

        final SequentialTransition showNext = new SequentialTransition();
        showNext.setNode(nextSlide);

        final Timeline showTimeline = new Timeline(
                new KeyFrame(Duration.millis(0), new KeyValue(nextSlide.visibleProperty(), false)),
                new KeyFrame(Duration.millis(1), new KeyValue(nextSlide.visibleProperty(), true)));

        final TranslateTransition slideIn = new TranslateTransition(Duration.millis(400), nextSlide);
        slideIn.setFromY(700);
        slideIn.setToY(0);

        showNext.getChildren().addAll(showTimeline, slideIn);

        this.subSlideTransition.getChildren().addAll(hideCurrent, showNext);
        this.subSlideTransition.play();

    }

    protected StackPane getSlideContent() {
        return this.slideContentPane;
    }

    protected Node getHeaderPanel() {

        final Pane headerPane = new Pane();
        headerPane.getStyleClass().add("header");
        headerPane.setLayoutX(0.0);
        headerPane.setLayoutY(0.0);
        headerPane.setMinWidth(1024);
        headerPane.setPrefWidth(1024);

        final DropShadow primaryGlow = new DropShadow();
        primaryGlow.setBlurType(BlurType.TWO_PASS_BOX);
        primaryGlow.setColor(EcColors.SLIDE_TITLE_GLOW.get());
        primaryGlow.setRadius(8);
        primaryGlow.setSpread(0.5);
        final Bloom primaryBloom = new Bloom(1.0);
        primaryBloom.setInput(primaryGlow);

        this.primaryTitle = new Label();
        this.primaryTitle.setFont(EcFonts.SLIDE_TITLE.get());
        this.primaryTitle.setTextFill(EcColors.SLIDE_TITLE.get());
        this.primaryTitle.setText(model().getSlide().getTitle().replaceAll("\\\\n", "\n").replaceAll("\\\\t", "\t"));
        this.primaryTitle.setLayoutX(100);
        this.primaryTitle.setLayoutY(16);
        this.primaryTitle.setOpacity(0);
        this.primaryTitle.setEffect(primaryBloom);

        final DropShadow secondaryGlow = new DropShadow();
        secondaryGlow.setBlurType(BlurType.TWO_PASS_BOX);
        secondaryGlow.setColor(EcColors.SLIDE_TITLE_GLOW.get());
        secondaryGlow.setRadius(4);
        secondaryGlow.setSpread(0.6);
        final Bloom secondaryBloom = new Bloom(1.0);
        secondaryBloom.setInput(secondaryGlow);

        this.secondaryTitle = new Label();
        this.secondaryTitle.setFont(EcFonts.SLIDE_SUB_TITLE.get());
        this.secondaryTitle.setTextFill(EcColors.SLIDE_TITLE.get());
        this.secondaryTitle.setOpacity(0);
        this.secondaryTitle.setEffect(secondaryBloom);
        this.secondaryTitle.setLayoutX(100);
        this.secondaryTitle.setLayoutY(70);
        this.secondaryTitle.setMinWidth(450);
        this.secondaryTitle.setAlignment(Pos.CENTER_LEFT);
        this.secondaryTitle.setTextAlignment(TextAlignment.RIGHT);

        this.headerRectangle = new Rectangle();
        this.headerRectangle.setLayoutX(0);
        this.headerRectangle.setLayoutY(0);
        this.headerRectangle.setWidth(860);
        this.headerRectangle.setHeight(115.0);
        this.headerRectangle.setTranslateX(-1100);
        this.headerRectangle.setFill(Color.BLACK);

        this.headerPattern1 = new ImageView(EcImages.HEADER_PATTERN.get());
        this.headerPattern1.setBlendMode(BlendMode.SRC_OVER);
        this.headerPattern1.setOpacity(0.4);
        this.headerPattern1.setLayoutX(-164);
        this.headerPattern1.setLayoutY(0);

        this.headerPattern2 = new ImageView(EcImages.HEADER_PATTERN.get());
        this.headerPattern2.setBlendMode(BlendMode.SRC_OVER);
        this.headerPattern2.setOpacity(0.2);
        this.headerPattern2.setLayoutX(861);
        this.headerPattern2.setLayoutY(0);

        this.headerPatternGroup = new Group();
        this.headerPatternGroup.setTranslateY(-150);
        this.headerPatternGroup.getChildren().addAll(this.headerPattern1, this.headerPattern2);

        headerPane.getChildren().addAll(this.headerRectangle, this.headerPatternGroup, this.primaryTitle, this.secondaryTitle);

        return headerPane;

    }