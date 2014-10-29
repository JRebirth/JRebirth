package org.jrebirth.af.core.command.dataflow;

import org.jrebirth.af.core.command.basic.BasicCommandTest;
import org.junit.Ignore;
import org.junit.Test;

/**
 * The class <strong>RefCommandTest</strong>.
 *
 * @author Sébastien Bordes
 */
@Ignore
public class DataFlowCommandTest extends BasicCommandTest {

    @Test
    public void refTest1() {
        System.out.println("DataFlowCommandTest");

        runCommand(FlowMultiCommand.class);
    }

}
