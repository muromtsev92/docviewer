package ru.test.docviewer;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Invoice extends Document{
    private DoubleProperty sum = new SimpleDoubleProperty();
    private StringProperty currency = new SimpleStringProperty();
    private DoubleProperty currencyRate = new SimpleDoubleProperty();
    private StringProperty product = new SimpleStringProperty();
    private DoubleProperty quantity = new SimpleDoubleProperty();

    public Invoice() {
        super("Накладная");
    }

    public double getSum() {
        return sum.get();
    }

    public DoubleProperty getSumProperty() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum.set(sum);
    }

    public String getCurrency() {
        return currency.get();
    }

    public StringProperty getCurrencyProperty() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency.set(currency);
    }

    public double getCurrencyRate() {
        return currencyRate.get();
    }

    public DoubleProperty getCurrencyRateProperty() {
        return currencyRate;
    }

    public void setCurrencyRate(double currencyRate) {
        this.currencyRate.set(currencyRate);
    }

    public String getProduct() {
        return product.get();
    }

    public StringProperty getProductProperty() {
        return product;
    }

    public void setProduct(String product) {
        this.product.set(product);
    }

    public double getQuantity() {
        return quantity.get();
    }

    public DoubleProperty getQuantityProperty() {
        return quantity;
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

}
