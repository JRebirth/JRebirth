package org.jrebirth.showcase.fxml.ui.main;

import org.jrebirth.component.ui.stack.PageEnum;
import org.jrebirth.core.concurrent.JRebirthThread;
import org.jrebirth.core.key.UniqueKey;
import org.jrebirth.core.ui.Model;
import org.jrebirth.showcase.fxml.ui.standalone.StandaloneModel;

public enum FXMLPage implements PageEnum {

    ViewEmbeddedFxml,

    StandaloneFxml,

    HybridFxml

    ;

    @Override
    public UniqueKey<? extends Model> getModelKey() {
        UniqueKey<? extends Model> modelKey;

        switch (this) {

            default:
            case ViewEmbeddedFxml:
                modelKey = JRebirthThread.getThread().getFacade().getUiFacade().buildKey(StandaloneModel.class);
                break;
            case StandaloneFxml:
                modelKey = JRebirthThread.getThread().getFacade().getUiFacade().buildKey(StandaloneModel.class);
                break;
            case HybridFxml:
                modelKey = JRebirthThread.getThread().getFacade().getUiFacade().buildKey(StandaloneModel.class);
                break;
        }

        return modelKey;
    }

}
