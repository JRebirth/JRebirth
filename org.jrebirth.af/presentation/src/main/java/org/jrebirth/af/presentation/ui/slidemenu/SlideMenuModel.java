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
package org.jrebirth.af.presentation.ui.slidemenu;

import java.util.ArrayList;
import java.util.List;

import org.jrebirth.af.api.annotation.LinkComponent;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.core.ui.object.DefaultObjectModel;
import org.jrebirth.af.presentation.service.PresentationService;
import org.jrebirth.presentation.model.Slide;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>SlideMenuModel</strong>.
 *
 * @author Sébastien Bordes
 */
public final class SlideMenuModel extends DefaultObjectModel<SlideMenuModel, SlideMenuView, Slide> {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(SlideMenuModel.class);

    /** Store a strong reference to the service to avoid reloading. */
    @LinkComponent
    private PresentationService presentationService;

    private final List<Slide> slides = new ArrayList<>();

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initModel() {

        final List<Slide> allSlide = presentationService.getPresentation().getSlides().getSlide();

        final int index = allSlide.indexOf(getObject());

        for (int i = Math.max(0, index - 3); i < index; i++) {
            slides.add(allSlide.get(i));
        }

        for (int i = Math.max(allSlide.size() - 1, index + 1); i <= index + 3; i++) {
            slides.add(allSlide.get(i));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initInnerComponents() {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void showView() {
        // Nothing to do generic
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void hideView() {
        // Nothing to do generic
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void processWave(final Wave wave) {
        // Nothing to do yet
    }

    public List<Slide> getSlides() {
        return slides;
    }

}
