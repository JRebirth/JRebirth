package org.jrebirth.af.showcase.todos.resource;

import static org.jrebirth.af.core.resource.Resources.create;

import org.jrebirth.af.api.resource.style.StyleSheetItem;
import org.jrebirth.af.core.resource.style.StyleSheet;

public interface TodosStyles {

    /** The application main style sheet. */
    StyleSheetItem MAIN = create(new StyleSheet("Main"));

}
