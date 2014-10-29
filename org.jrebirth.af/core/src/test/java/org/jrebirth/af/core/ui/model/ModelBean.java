package org.jrebirth.af.core.ui.model;

import org.jrebirth.af.api.key.KeyGenerator;

@KeyGenerator(value = "getName")
public class ModelBean {

    private String name;

    private int count;

    public ModelBean() {
        super();
    }

    /**
     * Default Constructor.
     *
     * @param name
     */
    public ModelBean(final String name) {
        super();
        this.name = name;
    }

    /**
     * Default Constructor.
     *
     * @param name
     * @param count
     */
    public ModelBean(final String name, final int count) {
        super();
        this.name = name;
        this.count = count;
    }

    /**
     * @return Returns the name.
     */
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
