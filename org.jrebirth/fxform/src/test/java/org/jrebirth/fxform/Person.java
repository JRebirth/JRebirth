package org.jrebirth.fxform;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * The class <strong>Person</strong>.
 * 
 * @author Sébastien Bordes
 */
public class Person {

    private final StringProperty firstName = new SimpleStringProperty("Sebastien");

    private final StringProperty lastName = new SimpleStringProperty("Bordes");

    protected String getFirstName() {
        return this.firstName.get();
    }

    protected void setFirstName(final String firstName) {
        this.firstName.set(firstName);
    }

    public StringProperty firstNameProperty() {
        return this.firstName;
    }

    protected String getLastName() {
        return this.lastName.get();
    }

    protected void setLastName(final String lastName) {
        this.lastName.set(lastName);
    }

    public StringProperty lastNameProperty() {
        return this.lastName;
    }

}
