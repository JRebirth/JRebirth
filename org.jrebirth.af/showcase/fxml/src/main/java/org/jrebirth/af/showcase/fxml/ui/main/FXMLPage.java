package org.jrebirth.af.showcase.fxml.ui.main;

import org.jrebirth.af.api.key.UniqueKey;
import org.jrebirth.af.api.ui.Model;
import org.jrebirth.af.api.ui.fxml.FXMLModel;
import org.jrebirth.af.component.ui.stack.PageEnum;
import org.jrebirth.af.core.key.Key;
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
                modelKey = Key.create(EmbeddedModel.class);
                break;
            case StandaloneFxml:
                modelKey = Key.create(StandaloneModel.class);
                break;
            case HybridFxml:
                modelKey = Key.create(HybridModel.class, FXMLModel.KEYPART_FXML_PREFIX + "org.jrebirth.af.showcase.fxml.ui.hybrid.Hybrid");
                break;
            case IncludedFxml:
                modelKey = Key.create(IncludedModel.class, new LoremIpsum());
                break;
        }

        return modelKey;
    }

}
