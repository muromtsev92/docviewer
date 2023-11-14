package ru.test.docviewer;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PaymentRequest extends Document{

    private StringProperty contractor = new SimpleStringProperty();
    private DoubleProperty sum = new SimpleDoubleProperty();
    private StringProperty currency = new SimpleStringProperty();
    private DoubleProperty currencyRate = new SimpleDoubleProperty();
    private DoubleProperty commission = new SimpleDoubleProperty();

    public PaymentRequest() {
        super("Заявка на оплату");
    }

    public String getContractor() {
        return contractor.get();
    }

    public StringProperty getContractorProperty() {
        return contractor;
    }

    public void setContractor(String contractor) {
        this.contractor.set(contractor);
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

    public double getCommission() {
        return commission.get();
    }

    public DoubleProperty getCommissionProperty() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission.set(commission);
    }

    @Override
    public String toString() {
        return "Тип: Заявка на оплату\n" + super.toString() + "\nКонтрагент: " + contractor.get() + "\nСумма: "
                + sum.get() + "\nВалюта: " + currency.get() + "\nКурс валюты: " + currencyRate.get()
                + "\nКомиссия: " + commission.get();
    }

}
