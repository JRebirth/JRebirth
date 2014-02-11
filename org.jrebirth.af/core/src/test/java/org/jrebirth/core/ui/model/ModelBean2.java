package org.jrebirth.core.ui.model;

import org.jrebirth.core.key.KeyGenerator;

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
    public ModelBean2(String name, int count) {
        super();
        this.name = name;
        this.count = count;
    }

    /**
     * @return Returns the name.
     */
    @KeyGenerator
    public String getName() {
        return name;
    }

    /**
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Returns the count.
     */
    @KeyGenerator
    public int getCount() {
        return count;
    }

    /**
     * @param count The count to set.
     */
    public void setCount(int count) {
        this.count = count;
    }

}
