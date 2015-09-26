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
package org.jrebirth.af.presentation.ui.qanda;

import org.jrebirth.af.presentation.ui.base.AbstractSlideModel;
import org.jrebirth.af.presentation.ui.base.SlideStep;
import org.jrebirth.presentation.model.SlideContent;

/**
 * The class <strong>QandAModel</strong>.
 *
 * @author Sébastien Bordes
 */
public class QandAModel extends AbstractSlideModel<QandAModel, QandAView, SlideStep> {

    // public enum QandAStep implements SlideStep {
    // question, answer
    // };

    private SlideContent currentSlideContent;

    /**
     * {@inheritDoc}
     */
    @Override
    protected SlideStep[] initializeSlideStep() {
        return new SlideStep[0];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void showView() {
        super.showView();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void hideView() {
        super.hideView();
        // Reset
        this.currentSlideContent = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showSlideStep(final SlideStep slideStep) {
        // switch (slideStep) {
        // case question:
        // getView().showNext();
        // break;
        // case answer:
        // getView().showAnswer();
        // break;
        // default:
        // break;
        // }

        final String currentTitle = this.currentSlideContent != null ? formatString(this.currentSlideContent.getTitle()) : null;
        final int idx = currentTitle != null ? this.currentSlideContent.getIndex().intValue() : isForwardFlow() ? -1 : getStepCount();

        if (isForwardFlow()) {
            this.currentSlideContent = getSlide().getContent().get(idx + 1);
            getView().showNext(currentTitle, formatString(this.currentSlideContent.getTitle()));
        } else {
            this.currentSlideContent = getSlide().getContent().get(idx == 0 ? getStepCount() - 1 : idx - 1);
            getView().showPrevious(currentTitle, formatString(this.currentSlideContent.getTitle()));
        }
    }

    // public boolean checkContent() {
    // boolean res = true;
    //
    // res &= !getSlide().getContent().isEmpty();
    //
    // res &= getSlide().getContent().get(0).getItem().size() >= 2;
    //
    // return res;
    // }

    // /**
    // * Return the question text.
    // *
    // * @return the question
    // */
    // public String getQuestion() {
    // return getSlide().getContent().get(0).getItem().get(0).getValue().replaceAll("\\\\n", "\n");
    // }
    //
    // /**
    // * Return the answer text.
    // *
    // * @return the answer
    // */
    // public String getAnswer() {
    // return getSlide().getContent().get(0).getItem().get(1).getValue().replaceAll("\\\\n", "\n");
    // }

    public String formatString(final String source) {
        return source != null ? source.replaceAll("\\\\n", "\n") : "";
    }

    /**
     * Return the style class.
     *
     * @return the style class
     */
    public String getStyleClass() {
        return getSlide().getStyle();
    }

}
