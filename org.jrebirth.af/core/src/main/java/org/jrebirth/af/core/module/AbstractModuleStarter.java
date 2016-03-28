package org.jrebirth.af.core.module;

import org.jrebirth.af.api.annotation.PriorityLevel;
import org.jrebirth.af.api.command.Command;
import org.jrebirth.af.api.component.basic.Component;
import org.jrebirth.af.api.component.factory.RegistrationItem;
import org.jrebirth.af.api.exception.CoreRuntimeException;
import org.jrebirth.af.api.facade.GlobalFacade;
import org.jrebirth.af.api.module.ModuleStarter;
import org.jrebirth.af.api.service.Service;
import org.jrebirth.af.api.ui.Model;
import org.jrebirth.af.core.concurrent.JRebirthThread;

public abstract class AbstractModuleStarter implements ModuleStarter {

    protected void register(Class<? extends Component<?>> interfaceClass, Class<? extends Component<?>> implClass, PriorityLevel priority, int weigh) {

        preloadClass(interfaceClass);

        getFacade().getComponentFactory().register(RegistrationItem.create().interfaceClass(interfaceClass).implClass(implClass));
    }

    @SuppressWarnings("unchecked")
    protected void bootComponent(Class<? extends Component<?>> componentClass) {

        preloadClass(componentClass);

        try {
            if (Command.class.isAssignableFrom(componentClass)) {
                getFacade().getCommandFacade().retrieve((Class<Command>) componentClass);
            } else if (Service.class.isAssignableFrom(componentClass)) {
                getFacade().getServiceFacade().retrieve((Class<Service>) componentClass);
            } else if (Model.class.isAssignableFrom(componentClass)) {
                getFacade().getUiFacade().retrieve((Class<Model>) componentClass);
            }
        } catch (final CoreRuntimeException e) {
            e.printStackTrace();
        }
    }

    /**
     * TODO To complete.
     * 
     * @param objectClass
     */
    protected void preloadClass(Class<?> objectClass) {
        try {
            Class.forName(objectClass.getName());
        } catch (final ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * TODO To complete.
     */
    protected GlobalFacade getFacade() {
        return JRebirthThread.getThread().getFacade();
    }
}
