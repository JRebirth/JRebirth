package org.jrebirth.af.core.command.basic.ref;

import org.jrebirth.af.core.command.basic.BasicCommandTest;
import org.jrebirth.af.core.command.ref.GroupRefCommand;
import org.jrebirth.af.core.command.ref.Ref;
import org.jrebirth.af.core.concurrent.RunType;
import org.jrebirth.af.core.concurrent.RunnablePriority;
import org.jrebirth.af.core.wave.Wave;

import org.junit.Test;

/**
 * The class <strong>GroupRefCommandTest</strong>.
 * 
 * @author Sébastien Bordes
 */
public class GroupRefCommandTest extends BasicCommandTest {

    @Test
    public void groupRefTest1() {
        System.out.println("Sequential Test default");

        Ref ref = Ref.group()
                .sequential(true)
                .runType(RunType.JTP)
                .priority(RunnablePriority.Highest)
                .add(
                        Ref.single()
                                .priority(RunnablePriority.Highest)
                                .runType(RunType.JTP)
                                .runner(this::sayHello),
                        Ref.single()
                                .priority(RunnablePriority.Highest)
                                .runType(RunType.JTP)
                                .waveRunner(this::sayHelloWave)
                );

        runCommand(GroupRefCommand.class, ref);
    }

    @Test
    public void groupRefTest2() {
        System.out.println("Sequential Test default");

        Ref ref = Ref.group()
                .sequential(true)
                .runType(RunType.JTP)
                .priority(RunnablePriority.Highest)
                .add(
                        // Ref.real().commandKey(UniqueKey.key(UpdateCursorCommand.class, Cursor.WAIT)),

                        Ref.single()
                                .priority(RunnablePriority.Highest)
                                .runType(RunType.JTP)
                                .runner(this::sayHello),

                        Ref.group()
                                .sequential(true)
                                .runType(RunType.JTP)
                                .priority(RunnablePriority.Highest)
                                .add(
                                        Ref.single()
                                                .priority(RunnablePriority.Highest)
                                                .runType(RunType.JTP)
                                                .runner(this::sayHello),
                                        Ref.single()
                                                .priority(RunnablePriority.Highest)
                                                .runType(RunType.JTP)
                                                .waveRunner(this::sayHelloWave),
                                        Ref.real()
                                                .commandKey(null)
                                ),

                        Ref.single()
                                .priority(RunnablePriority.Highest)
                                .runType(RunType.JTP)
                                .waveRunner(this::sayHelloWave)/*
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

    public void sayHelloWave(Wave wave) {
        System.out.println("hello " + wave.getWUID());
    }

}
