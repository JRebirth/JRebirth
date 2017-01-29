package org.jrebirth.af.component.ui.stack;

import javafx.scene.text.Text;

import org.jrebirth.af.core.ui.simple.DefaultSimpleModel;

public class TextModel extends DefaultSimpleModel<Text> {
    @Override
    protected void initSimpleView() {
        node().setText(getFirstKeyPart().toString());
    }

}
