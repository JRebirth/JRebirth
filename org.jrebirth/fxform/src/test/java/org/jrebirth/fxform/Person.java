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
        return firstName.get();
    }

    protected void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    protected String getLastName() {
        return lastName.get();
    }

    protected void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

}
