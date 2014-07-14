package org.jrebirth.af.core.ui.model;

import org.jrebirth.af.core.key.KeyGenerator;

public class ModelBean2 {

    private String name;

    private int count;

    public ModelBean2() {
        super();
    }

    /**
     * Default Constructor.
     *
     * @param name
     * @param count
     */
    public ModelBean2(final String name, final int count) {
        super();
        this.name = name;
        this.count = count;
    }

    /**
     * @return Returns the name.
     */
    @KeyGenerator
    public String getName() {
        return this.name;
    }

    /**
     * @param name The name to set.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @return Returns the count.
     */
    @KeyGenerator
    public int getCount() {
        return this.count;
    }

    /**
     * @param count The count to set.
     */
    public void setCount(final int count) {
        this.count = count;
    }

}
