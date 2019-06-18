package org.jrebirth.af.component.service;

import java.util.List;

import org.jrebirth.af.api.module.RegistrationPoint;
import org.jrebirth.af.api.service.Service;
import org.jrebirth.af.api.ui.Model;

@RegistrationPoint
public interface SelectionService extends Service {

    boolean toggleSelection(Model model, boolean toggle);

    List<Model> getMovableModel();

    List<Model> getResizableModel();
}
