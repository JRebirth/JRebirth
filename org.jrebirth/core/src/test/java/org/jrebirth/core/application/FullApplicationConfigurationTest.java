package org.jrebirth.core.application;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicBoolean;

import javafx.stage.Stage;

import org.jrebirth.core.application.TestApplication;
import org.jrebirth.core.concurrent.AbstractJrbRunnable;
import org.jrebirth.core.concurrent.JRebirth;
import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.exception.JRebirthThreadException;
import org.jrebirth.core.facade.CommandFacade;
import org.jrebirth.core.facade.GlobalFacadeBase;
import org.jrebirth.core.resource.provided.JRebirthParameters;
import org.jrebirth.core.service.basic.StageService;
import org.jrebirth.core.wave.Wave;
import org.jrebirth.core.wave.WaveListener;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;


/**
 * The class <strong>StageTest</strong>.
 * 
 * @author Sébastien Bordes
 */
@Ignore("JavaFX can't be run in headless mode yet")
public class FullApplicationConfigurationTest extends ApplicationTest<FullConfApplication> {

    
    public FullApplicationConfigurationTest() {
		super(FullConfApplication.class);
	}

    @Test
    public void checkFullConf() {

    	//Check that default value is used not those of properties file
    	Assert.assertEquals(new Integer(1024), JRebirthParameters.APPLICATION_STAGE_WIDTH.get());
    	Assert.assertEquals(new Integer(768), JRebirthParameters.APPLICATION_STAGE_HEIGHT.get());
    	
    	    	
    }

}
