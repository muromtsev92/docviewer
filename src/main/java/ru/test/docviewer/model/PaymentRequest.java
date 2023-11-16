package ru.test.docviewer.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import ru.test.docviewer.exception.FileException;

import java.time.LocalDate;

public class PaymentRequest extends Document {

    private StringProperty contractor = new SimpleStringProperty();
    private DoubleProperty sum = new SimpleDoubleProperty();
    private StringProperty currency = new SimpleStringProperty();
    private DoubleProperty currencyRate = new SimpleDoubleProperty();
    private DoubleProperty commission = new SimpleDoubleProperty();

    public PaymentRequest() {
        super("Заявка на оплату");
    }

    public void setContractor(String contractor) {
        this.contractor.set(contractor);
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

    public void setCommission(double commission) {
        this.commission.set(commission);
    }

    @Override
    public String toString() {
        return "Тип: Заявка на оплату\n" + super.toString() + "\nКонтрагент: " + contractor.get() + "\nСумма: "
                + sum.get() + "\nВалюта: " + currency.get() + "\nКурс валюты: " + currencyRate.get()
                + "\nКомиссия: " + commission.get();
    }

    public static PaymentRequest fromString(String[] stringsFromFile) {
        try{
            PaymentRequest paymentRequest = new PaymentRequest();
            paymentRequest.setNumber(stringsFromFile[1].substring(7));
            paymentRequest.setDate(LocalDate.parse(stringsFromFile[2].substring(6)));
            paymentRequest.setUser(stringsFromFile[3].substring(14));
            paymentRequest.setContractor(stringsFromFile[4].substring(12));
            paymentRequest.setSum(Double.parseDouble(stringsFromFile[5].substring(7)));
            paymentRequest.setCurrency(stringsFromFile[6].substring(8));
            paymentRequest.setCurrencyRate(Double.parseDouble(stringsFromFile[7].substring(12)));
            paymentRequest.setCommission(Double.parseDouble(stringsFromFile[8].substring(10)));
            return paymentRequest;
        } catch (Exception e) {
            throw new FileException("Ошибка при чтении запроса на оплату из файла");
        }
    }


}
