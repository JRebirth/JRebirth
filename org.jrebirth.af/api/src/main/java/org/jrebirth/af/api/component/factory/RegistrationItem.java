package org.jrebirth.af.api.component.factory;

import org.jrebirth.af.api.annotation.PriorityLevel;
import org.jrebirth.af.api.component.basic.Component;

public class RegistrationItem implements Comparable<RegistrationItem> {

    private Class<? extends Component<?>> interfaceClass;

    private Class<? extends Component<?>> implClass;

    private PriorityLevel priority;

    private int weight;

    public static RegistrationItem create() {
        return new RegistrationItem();
    }

    /**
     * @return Returns the interfaceClass.
     */
    public Class<? extends Component<?>> interfaceClass() {
        return this.interfaceClass;
    }

    /**
     * @param interfaceClass The interfaceClass to set.
     */
    public RegistrationItem interfaceClass(final Class<? extends Component<?>> interfaceClass) {
        this.interfaceClass = interfaceClass;
        return this;
    }

    /**
     * @return Returns the implClass.
     */
    public Class<? extends Component<?>> implClass() {
        return this.implClass;
    }

    /**
     * @param implClass The implClass to set.
     */
    public RegistrationItem implClass(final Class<? extends Component<?>> implClass) {
        this.implClass = implClass;
        return this;
    }

    /**
     * @return Returns the priority.
     */
    public PriorityLevel priority() {
        return this.priority;
    }

    /**
     * @param priority The priority to set.
     */
    public RegistrationItem priority(final PriorityLevel priority) {
        this.priority = priority;
        return this;
    }

    /**
     * @return Returns the weight.
     */
    public int weight() {
        return this.weight;
    }

    /**
     * @param weight The weight to set.
     */
    public RegistrationItem weight(final int weight) {
        this.weight = weight;
        return this;
    }

    @Override
    public int compareTo(final RegistrationItem o) {
        int priorityDiff = 0;
        if (this.priority != null) {
            if (o.priority != null) {
                priorityDiff = this.priority.level(this.weight) - o.priority.level(o.weight);
            } else {
                priorityDiff = 1;
            }
        } else {
            if (o.priority != null) {
                priorityDiff = -1;
            }
        }
        return priorityDiff == 0 ? this.implClass.getName().compareTo(o.implClass().getName()) : priorityDiff;
    }

}
