package org.jrebirth.af.showcase.fxml.ui.main;

import org.jrebirth.af.component.ui.stack.PageEnum;
import org.jrebirth.af.core.concurrent.JRebirthThread;
import org.jrebirth.af.core.key.UniqueKey;
import org.jrebirth.af.core.ui.Model;
import org.jrebirth.af.core.ui.fxml.DefaultFXMLModel;
import org.jrebirth.af.showcase.fxml.beans.LoremIpsum;
import org.jrebirth.af.showcase.fxml.ui.embedded.EmbeddedModel;
import org.jrebirth.af.showcase.fxml.ui.hybrid.HybridModel;
import org.jrebirth.af.showcase.fxml.ui.included.IncludedModel;
import org.jrebirth.af.showcase.fxml.ui.standalone.StandaloneModel;

/**
 * The class <strong>FXMLPage</strong>.
 * 
 * @author Sébastien Bordes
 */
public enum FXMLPage implements PageEnum {

    /** . */
    StandaloneFxml,

    /** . */
    IncludedFxml,

    /** . */
    ViewEmbeddedFxml,

    /** . */
    HybridFxml

    ;

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<? extends Model> getModelKey() {
        UniqueKey<? extends Model> modelKey;

        switch (this) {

            default:
            case ViewEmbeddedFxml:
                modelKey = JRebirthThread.getThread().getFacade().getUiFacade().buildKey(EmbeddedModel.class);
                break;
            case StandaloneFxml:
                modelKey = JRebirthThread.getThread().getFacade().getUiFacade().buildKey(StandaloneModel.class);
                break;
            case HybridFxml:
                modelKey = JRebirthThread.getThread().getFacade().getUiFacade().buildKey(HybridModel.class, DefaultFXMLModel.KEYPART_FXML_PREFIX + "org.jrebirth.af.showcase.fxml.ui.hybrid.Hybrid");
                break;
            case IncludedFxml:
                modelKey = JRebirthThread.getThread().getFacade().getUiFacade().buildKey(IncludedModel.class, new LoremIpsum());
                break;
        }

        return modelKey;
    }

}
