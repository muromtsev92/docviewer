package ru.test.docviewer.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import ru.test.docviewer.exception.FileException;

import java.time.LocalDate;

public class Payroll extends Document {

    private DoubleProperty sum = new SimpleDoubleProperty();
    private StringProperty employee = new SimpleStringProperty();

    public Payroll() {
        super("Платежка");
    }

    public void setSum(double sum) {
        this.sum.set(sum);
    }

    public void setEmployee(String employee) {
        this.employee.set(employee);
    }

    @Override
    public String toString() {
        return "Тип: Платежка\n" + super.toString() + "\nСумма: " + sum.get() + "\nСотрудник: " + employee.get();
    }

    public static Payroll fromString(String[] stringsFromFile) {
        try{
            Payroll payroll = new Payroll();
            payroll.setNumber(stringsFromFile[1].substring(7));
            payroll.setDate(LocalDate.parse(stringsFromFile[2].substring(6)));
            payroll.setUser(stringsFromFile[3].substring(14));
            payroll.setSum(Double.parseDouble(stringsFromFile[4].substring(7)));
            payroll.setEmployee(stringsFromFile[5].substring(11));
            return payroll;
        } catch (Exception e) {
            throw new FileException("Ошибка при чтении платежки из файла");
        }
    }
}
