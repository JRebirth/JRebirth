package org.jrebirth.af.component.service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.jrebirth.af.api.module.Register;
import org.jrebirth.af.api.ui.Model;
import org.jrebirth.af.component.behavior.movable.MovableBehavior;
import org.jrebirth.af.component.behavior.resizable.ResizableBehavior;
import org.jrebirth.af.component.behavior.selectable.SelectableBehavior;
import org.jrebirth.af.core.service.DefaultService;

@Register(value = SelectionService.class)
public class SelectionServiceImpl extends DefaultService implements SelectionService {

    private final Set<Model> selectedModels = new LinkedHashSet<>();

    @Override
    public boolean toggleSelection(final Model model, final boolean toggle) {

        if (this.selectedModels.contains(model)) {
            if (toggle) {
                this.selectedModels.remove(model);
            } else {
            	this.selectedModels.stream().forEach(m -> m.getBehavior(SelectableBehavior.class).unselect());
                this.selectedModels.clear();
                this.selectedModels.add(model);
            }
        } else {
            if (!toggle) {
                this.selectedModels.stream().forEach(m -> m.getBehavior(SelectableBehavior.class).unselect());
                this.selectedModels.clear();
            }
            this.selectedModels.add(model);
        }

        return this.selectedModels.contains(model);
    }

    @Override
    public List<Model> getMovableModel() {
        return this.selectedModels.stream().filter(m -> m.hasBehavior(MovableBehavior.class)).collect(Collectors.toList());
    }

    @Override
    public List<Model> getResizableModel() {
        return this.selectedModels.stream().filter(m -> m.hasBehavior(ResizableBehavior.class)).collect(Collectors.toList());
    }

}
