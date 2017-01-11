package org.jrebirth.af.core.command.basic.ref;

import org.jrebirth.af.api.annotation.PriorityLevel;
import org.jrebirth.af.api.concurrent.RunType;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.core.command.basic.BasicCommandTest;
import org.jrebirth.af.core.command.ref.GroupRefCommand;
import org.jrebirth.af.core.command.ref.Ref;

import org.junit.Ignore;
import org.junit.Test;

/**
 * The class <strong>GroupRefCommandTest</strong>.
 *
 * @author Sébastien Bordes
 */
@Ignore("Keypart not propagated for single ref into groupref")
public class GroupRefCommandTest extends BasicCommandTest {

    @Test
    public void groupRefTest1() {
        System.out.println("Sequential Test default");

        final Ref ref = Ref.group()
                           .sequential(true)
                           .runInto(RunType.JTP)
                           .priority(PriorityLevel.Highest)
                           .add(
                                Ref.single()
                                   .priority(PriorityLevel.Highest)
                                   .runInto(RunType.JTP)
                                   .run(this::sayHello),
                                Ref.single()
                                   .priority(PriorityLevel.Highest)
                                   .runInto(RunType.JTP)
                                   .runWave(this::sayHelloWave));

        runCommand(GroupRefCommand.class, ref);
    }

    @Test
    public void groupRefTest2() {
        System.out.println("Sequential Test default");

        final Ref ref = Ref.group()
                           .sequential(true)
                           .runInto(RunType.JTP)
                           .priority(PriorityLevel.Highest)
                           .add(
                                // Ref.real().commandKey(UniqueKey.key(UpdateCursorCommand.class, Cursor.WAIT)),

                                Ref.single()
                                   .priority(PriorityLevel.Highest)
                                   .runInto(RunType.JTP)
                                   .run(this::sayHello),

                                Ref.group()
                                   .sequential(true)
                                   .runInto(RunType.JTP)
                                   .priority(PriorityLevel.Highest)
                                   .add(
                                        Ref.single()
                                           .priority(PriorityLevel.Highest)
                                           .runInto(RunType.JTP)
                                           .run(this::sayHello),
                                        Ref.single()
                                           .priority(PriorityLevel.Highest)
                                           .runInto(RunType.JTP)
                                           .runWave(this::sayHelloWave),
                                        Ref.real()
                                           .key(null)),

                                Ref.single()
                                   .priority(PriorityLevel.Highest)
                                   .runInto(RunType.JTP)
                                   .runWave(this::sayHelloWave)/*
                                                                * ,
                                                                *
                                                                * Ref.real().commandKey(UniqueKey.key(UpdateCursorCommand.class, Cursor.DEFAULT))
                                                                */
        );

        runCommand(GroupRefCommand.class, ref);
    }

    public void sayHello() {
        System.out.println("hello");
    }

    public void sayHelloWave(final Wave wave) {
        System.out.println("hello " + wave.wUID());
    }

}
