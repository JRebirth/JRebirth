package org.jrebirth.af.core.command.basic.multi;

import org.jrebirth.af.core.command.basic.BasicCommandTest;

import org.junit.Test;

public class MultiCommandTest extends BasicCommandTest {

    @Test
    public void sequentialTest1() {
        System.out.println("Sequential Test default");
        runCommand(DefaultSequentialCommand.class);
    }

    @Test
    public void sequentialTest2() {
        System.out.println("Sequential Test annotation");
        runCommand(AnnotatedSequentialCommand.class);
    }

    @Test
    public void sequentialTest3() {
        System.out.println("Sequential Test constructor");
        runCommand(ConstructorSequentialCommand.class);
    }

    @Test
    public void parallelTest1() {
        System.out.println("Parallel Test default");
        runCommand(DefaultParallelCommand.class);
    }

    @Test
    public void parallelTest2() {
        System.out.println("Parallel Test annotation");
        runCommand(AnnotatedParallelCommand.class);
    }

    @Test
    public void parallelTest3() {
        System.out.println("Parallel Test constructor");
        runCommand(ConstructorParallelCommand.class);
    }
}
