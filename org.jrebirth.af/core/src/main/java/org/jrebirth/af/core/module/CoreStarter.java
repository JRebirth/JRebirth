package org.jrebirth.af.core.module;

import org.jrebirth.af.core.service.basic.StageService;
import org.jrebirth.af.core.service.basic.impl.StageServiceImpl;
import org.jrebirth.af.core.ui.Showable;

/**
 * The class <strong>CoreStarter</strong> is used to set up provided Core components.
 *
 * @author Sébastien Bordes
 */
public class CoreStarter extends AbstractModuleStarter {

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {

        preloadClass(Showable.class);

        define(StageService.class, true, false);
        register(StageService.class, StageServiceImpl.class);

    }

}
