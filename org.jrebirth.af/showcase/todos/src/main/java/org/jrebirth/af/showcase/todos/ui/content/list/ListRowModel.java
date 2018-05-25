package org.jrebirth.af.showcase.todos.ui.content.list;

import org.jrebirth.af.core.ui.object.DefaultObjectModel;
import org.jrebirth.af.showcase.todos.bean.Todo;

public class ListRowModel extends DefaultObjectModel<ListRowModel, ListRowView, Todo> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initModel() {
        super.initModel();

    }

    @Override
    protected void bind() {
        super.bind();

        // view().getDeleteButton().visibleProperty().bind(node().hoverProperty());
        //
        // view().getDone().selectedProperty().bindBidirectional(object().pDone());
        // view().getContentInput().textProperty().bindBidirectional(object().pText());
        // view().getContentLabel().textProperty().bind(object().pText());

    }

}
