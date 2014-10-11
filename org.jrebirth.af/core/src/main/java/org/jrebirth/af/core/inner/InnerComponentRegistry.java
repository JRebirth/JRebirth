package org.jrebirth.af.core.inner;

import java.util.HashMap;
import java.util.Map;

import org.jrebirth.af.core.facade.Component;
import org.jrebirth.af.core.key.UniqueKey;
import org.jrebirth.af.core.ui.Model;

public class InnerComponentRegistry {

    private final Map<InnerComponent<?>, UniqueKey<? extends Component<?>>> innerModelMap = new HashMap<>();

    public static InnerComponentRegistry instance;

    public static InnerComponentRegistry getInstance() {
        if (instance == null) {
            instance = new InnerComponentRegistry();
        }
        return instance;
    }

    public <C extends Component<C>> void storeKey(final InnerComponent<C> innerModel, final UniqueKey<C> uniqueKey) {
        this.innerModelMap.put(innerModel, uniqueKey);
    }

    @SuppressWarnings("unchecked")
    public <M extends Model> UniqueKey<M> getKey(final InnerComponent<?> innerModel) {
        final Class<M> cls = (Class<M>) this.innerModelMap.get(innerModel).getClassField();
        return (UniqueKey<M>) this.innerModelMap.get(innerModel);
    }
}
