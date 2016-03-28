package org.jrebirth.af.core.command.basic.ref;

import org.jrebirth.af.api.annotation.PriorityLevel;
import org.jrebirth.af.api.concurrent.RunType;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.core.command.basic.BasicCommandTest;
import org.jrebirth.af.core.command.ref.Ref;
import org.jrebirth.af.core.command.ref.RefCommand;

import org.junit.Ignore;
import org.junit.Test;

/**
 * The class <strong>RefCommandTest</strong>.
 *
 * @author Sébastien Bordes
 */
@Ignore
public class RefCommandTest extends BasicCommandTest {

    @Test
    public void refTest1() {
        System.out.println("Sequential Test default");

        final Ref ref = Ref.single()
                           .priority(PriorityLevel.Highest)
                           .runInto(RunType.JTP)
                           .run(this::sayHello);

        runCommand(RefCommand.class, ref);
    }

    @Test
    public void refTest2() {
        System.out.println("Sequential Test default");

        final Ref ref = Ref.single()
                           .priority(PriorityLevel.Highest)
                           .runInto(RunType.JTP)
                           .runWave(this::sayHelloWave);

        runCommand(RefCommand.class, ref);
    }

    public void sayHello() {
        System.out.println("hello");
    }

    public void sayHelloWave(final Wave wave) {
        System.out.println("hello " + wave.getWUID());
    }

}
