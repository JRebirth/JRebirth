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
        return interfaceClass;
    }

    /**
     * @param interfaceClass The interfaceClass to set.
     */
    public RegistrationItem interfaceClass(Class<? extends Component<?>> interfaceClass) {
        this.interfaceClass = interfaceClass;
        return this;
    }

    /**
     * @return Returns the implClass.
     */
    public Class<? extends Component<?>> implClass() {
        return implClass;
    }

    /**
     * @param implClass The implClass to set.
     */
    public RegistrationItem implClass(Class<? extends Component<?>> implClass) {
        this.implClass = implClass;
        return this;
    }

    /**
     * @return Returns the priority.
     */
    public PriorityLevel priority() {
        return priority;
    }

    /**
     * @param priority The priority to set.
     */
    public RegistrationItem priority(PriorityLevel priority) {
        this.priority = priority;
        return this;
    }

    /**
     * @return Returns the weight.
     */
    public int weight() {
        return weight;
    }

    /**
     * @param weight The weight to set.
     */
    public RegistrationItem weight(int weight) {
        this.weight = weight;
        return this;
    }

    @Override
    public int compareTo(RegistrationItem o) {
        int priorityDiff = 0;
        if (priority != null) {
            if (o.priority != null) {
                priorityDiff = priority.level(weight) - o.priority.level(o.weight);
            } else {
                priorityDiff = 1;
            }
        } else {
            if (o.priority != null) {
                priorityDiff = -1;
            }
        }
        return priorityDiff == 0 ? implClass.getName().compareTo(o.implClass().getName()) : priorityDiff;
    }

}
