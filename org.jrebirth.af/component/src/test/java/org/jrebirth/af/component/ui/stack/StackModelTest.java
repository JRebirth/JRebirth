package org.jrebirth.af.component.ui.stack;

import static org.hamcrest.CoreMatchers.instanceOf;

import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import org.jrebirth.af.api.key.UniqueKey;
import org.jrebirth.af.api.ui.Model;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.component.ui.beans.StackConfig;
import org.jrebirth.af.core.application.apps.BorderPaneModel;
import org.jrebirth.af.core.command.basic.showmodel.DisplayModelWaveBean;
import org.jrebirth.af.core.command.basic.showmodel.ShowModelCommand;
import org.jrebirth.af.core.key.Key;
import org.jrebirth.af.core.test.AbstractBorderPaneTest;
import org.jrebirth.af.core.wave.WBuilder;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StackModelTest extends AbstractBorderPaneTest {

    private void check(final Wave wave, final String value) {

        waitWave(wave);

        final Node center = getBorderPaneModel().node().getCenter();

        Assert.assertThat(center, instanceOf(StackPane.class));
        Assert.assertEquals(1, ((StackPane) center).getChildren().size());

        final Node stack = ((StackPane) center).getChildren().get(0);

        Assert.assertThat(stack, instanceOf(StackPane.class));
        Assert.assertEquals(1, ((StackPane) stack).getChildren().size());

        final Node text = ((StackPane) stack).getChildren().get(0);

        Assert.assertThat(text, instanceOf(Text.class));
        Assert.assertEquals(value, ((Text) text).getText());
    }

    @Test
    public void stackNameConfigData() {

        final String stackName = "Stack #0";
        final BorderPaneModel model = getBorderPaneModel();

        final Wave wave = model.callCommand(ShowModelCommand.class).waveBean(
                                                                             DisplayModelWaveBean.create()
                                                                                                 .uniquePlaceHolder(model.node().centerProperty())
                                                                                                 .showModelData(StackConfig.create().stackName(stackName)));
        waitWave(wave);
        switchStackModels(model, stackName);

    }

    @Test
    public void stackNameString() {

        final String stackName = "Stack #1";

        final BorderPaneModel model = getBorderPaneModel();

        final UniqueKey<? extends org.jrebirth.af.api.ui.Model> stack1 = Key.create(StackModel.class, stackName);

        final DisplayModelWaveBean dmwb = DisplayModelWaveBean.create()
                                                              .uniquePlaceHolder(model.node().centerProperty())
                                                              .showModelKey(stack1);

        final Wave wave = model.callCommand(ShowModelCommand.class).waveBean(dmwb);

        waitWave(wave);

        Assert.assertThat(dmwb.showModel(), instanceOf(StackModel.class));
        final StackModel sm = (StackModel) dmwb.showModel();

        // FIXME Dont work
        // sm.setDefaultPageModelKey(Key.create(TextModel.class, "text0"));

        switchStackModels(model, stackName);

    }

    @Test
    public void stackNameConfig() {

        final String stackName = "Stack #2";

        final BorderPaneModel model = getBorderPaneModel();

        final UniqueKey<? extends org.jrebirth.af.api.ui.Model> stack2 = Key.create(StackModel.class, StackConfig.create().stackName(stackName));

        final Wave wave = model.callCommand(ShowModelCommand.class).waveBean(
                                                                             DisplayModelWaveBean.create()
                                                                                                 .uniquePlaceHolder(model.node().centerProperty())
                                                                                                 .showModelKey(stack2));
        waitWave(wave);
        switchStackModels(model, stackName);

    }

    private void switchStackModels(final BorderPaneModel model, String stackName) {

        Wave wave = null;

        wave = model.sendWave(StackWaves.SHOW_PAGE_MODEL,
                              WBuilder.waveData(StackWaves.PAGE_MODEL_KEY, Key.create(TextModel.class, "text1")),
                              WBuilder.waveData(StackWaves.STACK_NAME, stackName));

        check(wave, "text1");

        wave = model.sendWave(StackWaves.SHOW_PAGE_MODEL,
                              WBuilder.waveData(StackWaves.PAGE_MODEL_KEY, Key.create(TextModel.class, "text2")),
                              WBuilder.waveData(StackWaves.STACK_NAME, stackName));

        check(wave, "text2");

        wave = model.sendWave(StackWaves.SHOW_PAGE_MODEL,
                              WBuilder.waveData(StackWaves.PAGE_MODEL_KEY, Key.create(TextModel.class, "text3")),
                              WBuilder.waveData(StackWaves.STACK_NAME, stackName));

        check(wave, "text3");

        wave = model.sendWave(StackWaves.SHOW_PAGE_MODEL,
                              WBuilder.waveData(StackWaves.PAGE_MODEL_KEY, Key.create(TextModel.class, "text4")),
                              WBuilder.waveData(StackWaves.STACK_NAME, stackName));

        check(wave, "text4");
    }

    @Test
    public void stackPagesEnum() {

        final BorderPaneModel model = getBorderPaneModel();

        final UniqueKey<? extends org.jrebirth.af.api.ui.Model> stack3 = Key.create(StackModel.class, Pages.class);

        final Wave wave = model.callCommand(ShowModelCommand.class).waveBean(
                                                                             DisplayModelWaveBean.create()
                                                                                                 .uniquePlaceHolder(model.node().centerProperty())
                                                                                                 .showModelKey(stack3));
        waitWave(wave);
        switchStackPages(model, Pages.values());

    }

    @Test
    public void stackPagesConfig() {

        final BorderPaneModel model = getBorderPaneModel();

        final UniqueKey<? extends org.jrebirth.af.api.ui.Model> stack4 = Key.create(StackModel.class, StackConfig.create().pageEnumClass(Pages2.class));

        final Wave wave = model.callCommand(ShowModelCommand.class).waveBean(
                                                                             DisplayModelWaveBean.create()
                                                                                                 .uniquePlaceHolder(model.node().centerProperty())
                                                                                                 .showModelKey(stack4));
        waitWave(wave);
        switchStackPages(model, Pages2.values());

    }

    private void switchStackPages(final BorderPaneModel model, final PageEnum[] values) {

        Wave wave = null;

        wave = model.sendWave(StackWaves.SHOW_PAGE_ENUM, WBuilder.waveData(StackWaves.PAGE_ENUM, values[1]));
        check(wave, "2");

        wave = model.sendWave(StackWaves.SHOW_PAGE_ENUM, WBuilder.waveData(StackWaves.PAGE_ENUM, values[2]));
        check(wave, "3");

        wave = model.sendWave(StackWaves.SHOW_PAGE_ENUM, WBuilder.waveData(StackWaves.PAGE_ENUM, values[3]));
        check(wave, "4");

        wave = model.sendWave(StackWaves.SHOW_PAGE_ENUM, WBuilder.waveData(StackWaves.PAGE_ENUM, values[0]));
        check(wave, "1");
    }

    public enum Pages implements PageEnum {
        Text1, Text2, Text3, Text4;

        @Override
        public UniqueKey<? extends Model> getModelKey() {
            UniqueKey<? extends Model> modelKey;

            switch (this) {

                default:
                case Text1:
                    modelKey = Key.create(TextModel.class, "1");
                    break;
                case Text2:
                    modelKey = Key.create(TextModel.class, "2");
                    break;
                case Text3:
                    modelKey = Key.create(TextModel.class, "3");
                    break;
                case Text4:
                    modelKey = Key.create(TextModel.class, "4");
                    break;
            }

            return modelKey;
        }

    }

    public enum Pages2 implements PageEnum {
        Text1, Text2, Text3, Text4;

        @Override
        public UniqueKey<? extends Model> getModelKey() {
            UniqueKey<? extends Model> modelKey;

            switch (this) {

                default:
                case Text1:
                    modelKey = Key.create(TextModel.class, "1");
                    break;
                case Text2:
                    modelKey = Key.create(TextModel.class, "2");
                    break;
                case Text3:
                    modelKey = Key.create(TextModel.class, "3");
                    break;
                case Text4:
                    modelKey = Key.create(TextModel.class, "4");
                    break;
            }

            return modelKey;
        }

    }
}
