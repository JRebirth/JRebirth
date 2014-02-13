package org.jrebirth.af.core.ui.model;

import org.jrebirth.af.core.key.KeyGenerator;

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
    public ModelBean(String name) {
        super();
        this.name = name;
    }

    /**
     * Default Constructor.
     * 
     * @param name
     * @param count
     */
    public ModelBean(String name, int count) {
        super();
        this.name = name;
        this.count = count;
    }

    /**
     * @return Returns the name.
     */
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
