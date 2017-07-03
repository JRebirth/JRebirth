package org.jrebirth.af.showcase.todos.ui.main;

import org.jrebirth.af.api.annotation.Link;
import org.jrebirth.af.api.component.basic.InnerComponent;
import org.jrebirth.af.api.module.Register;
import org.jrebirth.af.api.ui.ModuleModel;
import org.jrebirth.af.core.ui.DefaultModel;
import org.jrebirth.af.showcase.todos.ui.content.ContentModel;
import org.jrebirth.af.showcase.todos.ui.header.HeaderModel;
import org.jrebirth.af.showcase.todos.ui.status.StatusModel;

@Register(value = ModuleModel.class)
public class MainModel extends DefaultModel<MainModel, MainView> implements ModuleModel {

    /** The Expression UI inner component. */
    @Link
    protected InnerComponent<HeaderModel> headerModel;

    /** The Content UI inner component. */
    @Link
    protected InnerComponent<ContentModel> contentModel;

    /** The Status UI inner component. */
    @Link
    protected InnerComponent<StatusModel> statusModel;

    /** Provides getter to model. */

    HeaderModel headerModel() {
        return this.headerModel.get();
    }

    ContentModel contentModel() {
        return this.contentModel.get();
    }

    StatusModel statusModel() {
        return this.statusModel.get();
    }

    @Override
    public String moduleName() {
        return "Todos";
    }

}
