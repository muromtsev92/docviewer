package ru.test.docviewer.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import ru.test.docviewer.exception.FileException;

import java.time.LocalDate;

public class Invoice extends Document {
    private DoubleProperty sum = new SimpleDoubleProperty();
    private StringProperty currency = new SimpleStringProperty();
    private DoubleProperty currencyRate = new SimpleDoubleProperty();
    private StringProperty product = new SimpleStringProperty();
    private DoubleProperty quantity = new SimpleDoubleProperty();

    public Invoice() {
        super("Накладная");
    }

    public void setSum(double sum) {
        this.sum.set(sum);
    }

    public void setCurrency(String currency) {
        this.currency.set(currency);
    }

    public void setCurrencyRate(double currencyRate) {
        this.currencyRate.set(currencyRate);
    }

    public void setProduct(String product) {
        this.product.set(product);
    }

    public void setQuantity(double quantity) {
        this.quantity.set(quantity);
    }

    @Override
    public String toString() {
        return "Тип: Накладная\n" + super.toString() + "\nСумма: " + sum.get() + "\nВалюта: " + currency.get()
                + "\nКурс валюты: " + currencyRate.get() + "\nТовар: " + product.get()
                + "\nКоличество: " + quantity.get();
    }

    public static Invoice fromString(String[] stringsFromFile) {
        try{
            Invoice invoice = new Invoice();
            invoice.setNumber(stringsFromFile[1].substring(7));
            invoice.setDate(LocalDate.parse(stringsFromFile[2].substring(6)));
            invoice.setUser(stringsFromFile[3].substring(14));
            invoice.setSum(Double.parseDouble(stringsFromFile[4].substring(7)));
            invoice.setCurrency(stringsFromFile[5].substring(8));
            invoice.setCurrencyRate(Double.parseDouble(stringsFromFile[6].substring(12)));
            invoice.setProduct(stringsFromFile[7].substring(7));
            invoice.setQuantity(Double.parseDouble(stringsFromFile[8].substring(12)));
            return invoice;
        } catch (Exception e){
            throw new FileException("Ошибка при чтении накладной из файла");
        }

    }
}
