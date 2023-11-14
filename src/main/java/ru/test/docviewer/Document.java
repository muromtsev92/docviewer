package ru.test.docviewer;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public abstract class Document {
    private StringProperty name = new SimpleStringProperty();
    private StringProperty number = new SimpleStringProperty();
    private ObjectProperty<LocalDate> date = new SimpleObjectProperty<>();
    private StringProperty user = new SimpleStringProperty();
    private Boolean isSelected = false;

    public Document(String name) {
        this.name.set(name);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty getNameProperty() {
        return name;
    }

    public String getNumber() {
        return number.get();
    }

    public StringProperty getNumberProperty() {
        return number;
    }

    public void setNumber(String number) {
        this.number.set(number);
    }

    public LocalDate getDate() {
        return date.get();
    }

    public ObjectProperty<LocalDate> getDateProperty() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date.set(date);
    }

    public String getUser() {
        return user.get();
    }

    public StringProperty getUserProperty() {
        return user;
    }

    public void setUser(String user) {
        this.user.set(user);
    }

    public void setSelected(Boolean newVal) {
        isSelected = newVal;
    }

    public Boolean getIsSelected(){
        return isSelected;
    }

    @Override
    public String toString() {
        return "Номер: " + number.get() + "\nДата: " + date.get() + "\nПользователь: " + user.get();
    }
}
