package org.jrebirth.af.core.command.basic.stage;

import static org.jrebirth.af.core.wave.WBuilder.callCommand;

import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.api.wave.WaveListener;
import org.jrebirth.af.core.concurrent.JRebirth;
import org.jrebirth.af.core.concurrent.JrbReferenceRunnable;
import org.jrebirth.af.core.test.AbstractTest;

import org.junit.Ignore;
import org.junit.Test;

/**
 * The class <strong>StageTest</strong>.
 *
 * @author Sébastien Bordes
 */
@Ignore("StageService not ready yet")
public class StageTest extends AbstractTest {

    @Test
    public void openDefaultStage() {

        final String stageKey = "defaultStage";
        final Wave wave = callCommand(StageCommand.class)
                                                         .waveBean(StageWaveBean.create()
                                                                                .action(StageAction.show)
                                                                                .stageKey(stageKey));

        wave.addWaveListener(new WaveListener() {

            @Override
            public void waveSent(final Wave wave) {
                // Nothing to do yet

            }

            @Override
            public void waveProcessed(final Wave wave) {
                // Nothing to do yet

            }

            @Override
            public void waveFailed(final Wave wave) {
                // Nothing to do yet

            }

            @Override
            public void waveDestroyed(final Wave wave) {
                // Nothing to do yet

            }

            @Override
            public void waveCreated(final Wave wave) {
                // Nothing to do yet

            }

            @Override
            public void waveConsumed(final Wave wave) {
                // final Stage stage = StageTest.globalFacade.serviceFacade().retrieve(StageService.class).getStage(stageKey);
                // Assert.assertNotNull(stage);
                // System.out.println("dddd");
            }

            @Override
            public void waveHandled(final Wave wave) {
                // Nothing to do yet

            }

            @Override
            public void waveCancelled(final Wave wave) {
                // Nothing to do yet

            }

        });

        JRebirth.runIntoJIT("Send Wave " + wave.toString(), () -> AbstractTest.globalFacade.notifier().sendWave(wave));

        try {
            Thread.sleep(2000);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }

    }

}
