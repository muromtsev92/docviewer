package ru.test.docviewer;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Docviewer extends Application {
    private ObservableList<Document> documents = FXCollections.observableArrayList();
    private TableView<Document> table = new TableView<>();
    @Override
    public void start(Stage stage) throws IOException {
        HBox buttonBox = getButtonBox();

        TableColumn<Document, Boolean> checkBoxColumn = new TableColumn<>("");
        TableColumn<Document, String> nameColumn = new TableColumn<>("Наименование");
        TableColumn<Document, String> numberColumn = new TableColumn<>("Номер");
        TableColumn<Document, String> dateColumn = new TableColumn<>("Дата");
        TableColumn<Document, String> userColumn = new TableColumn<>("Пользователь");

        checkBoxColumn.setMaxWidth(40);
        checkBoxColumn.setCellValueFactory(cellData -> {
            Document document = cellData.getValue();
            BooleanProperty checkBoxValue = new SimpleBooleanProperty(document.getIsSelected());
            checkBoxValue.addListener((obs, oldVal, newVal) -> document.setSelected(newVal));
            return checkBoxValue;
        });
        checkBoxColumn.setCellFactory(CheckBoxTableCell.forTableColumn(checkBoxColumn));
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        numberColumn.setCellValueFactory(cellData -> cellData.getValue().getNumberProperty());
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().getDateProperty().asString());
        userColumn.setCellValueFactory(cellData -> cellData.getValue().getUserProperty());

        table.getColumns().addAll(checkBoxColumn, nameColumn, numberColumn, dateColumn, userColumn);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setEditable(true);

        VBox contentBox = new VBox(10, buttonBox, table);

        VBox.setVgrow(table, Priority.ALWAYS);
        contentBox.setPadding(new Insets(10));

        Scene scene = new Scene(contentBox, 850, 800);
        stage.setScene(scene);
        stage.setTitle("Тест");
        stage.show();

    }


    public static void main(String[] args) {
        launch();
    }

    private void showDialog(Document document) {
        Dialog<Document> dialog = new Dialog<>();
        dialog.setTitle("Создание документа");
        dialog.setHeaderText(null);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        TextField numberField = new TextField();
        DatePicker dateField = new DatePicker();
        TextField userField = new TextField();

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.addRow(0, new Label("Номер:"), numberField);
        grid.addRow(1, new Label("Дата:"), dateField);
        grid.addRow(2, new Label("Пользователь:"), userField);

        if (document instanceof Invoice) {
            Invoice invoice = (Invoice) document;
            TextField sumField = new TextField();
            TextField currencyField = new TextField();
            TextField currencyRateField = new TextField();
            TextField productField = new TextField();
            TextField quantityField = new TextField();

            grid.addRow(3, new Label("Сумма:"), sumField);
            grid.addRow(4, new Label("Валюта:"), currencyField);
            grid.addRow(5, new Label("Курс валюты:"), currencyRateField);
            grid.addRow(6, new Label("Товар:"), productField);
            grid.addRow(7, new Label("Количество:"), quantityField);

            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == ButtonType.OK) {
                    invoice.setNumber(numberField.getText());
                    invoice.setDate(dateField.getValue());
                    invoice.setUser(userField.getText());
                    invoice.setSum(Double.parseDouble(sumField.getText()));
                    invoice.setCurrency(currencyField.getText());
                    invoice.setCurrencyRate(Double.parseDouble(currencyRateField.getText()));
                    invoice.setProduct(productField.getText());
                    invoice.setQuantity(Double.parseDouble(quantityField.getText()));
                    return invoice;
                }
                return null;
            });
        } else if (document instanceof Payroll) {
            Payroll payroll = (Payroll) document;
            TextField sumField = new TextField();
            TextField employeeField = new TextField();

            grid.addRow(3, new Label("Сумма:"), sumField);
            grid.addRow(4, new Label("Сотрудник:"), employeeField);

            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == ButtonType.OK) {
                    payroll.setNumber(numberField.getText());
                    payroll.setDate(dateField.getValue());
                    payroll.setUser(userField.getText());
                    payroll.setSum(Double.parseDouble(sumField.getText()));
                    payroll.setEmployee(employeeField.getText());
                    return payroll;
                }
                return null;
            });
        } else if (document instanceof PaymentRequest) {
            PaymentRequest paymentRequest = (PaymentRequest) document;
            TextField contractorField = new TextField();
            TextField sumField = new TextField();
            TextField currencyField = new TextField();
            TextField currencyRateField = new TextField();
            TextField commissionField = new TextField();

            grid.addRow(3, new Label("Контрагент:"), contractorField);
            grid.addRow(4, new Label("Сумма:"), sumField);
            grid.addRow(5, new Label("Валюта:"), currencyField);
            grid.addRow(6, new Label("Курс валюты:"), currencyRateField);
            grid.addRow(7, new Label("Комиссия:"), commissionField);

            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == ButtonType.OK) {
                    paymentRequest.setNumber(numberField.getText());
                    paymentRequest.setDate(dateField.getValue());
                    paymentRequest.setUser(userField.getText());
                    paymentRequest.setContractor(contractorField.getText());
                    paymentRequest.setSum(Double.parseDouble(sumField.getText()));
                    paymentRequest.setCurrency(currencyField.getText());
                    paymentRequest.setCurrencyRate(Double.parseDouble(currencyRateField.getText()));
                    paymentRequest.setCommission(Double.parseDouble(commissionField.getText()));
                    return paymentRequest;
                }
                return null;
            });
        }

        dialog.getDialogPane().setContent(grid);
        dialog.showAndWait().ifPresent(result -> {
            documents.add(result);
            table.getItems().add(result);
        });
    }

    private void saveToFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Сохранить документы");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Текстовые файлы", "*.txt"));
        File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            try (FileWriter writer = new FileWriter(file)) {
                for (Document document : documents) {
                    writer.write(document.toString());
                    writer.write(System.lineSeparator());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadFromFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Загрузить документы");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Текстовые файлы", "*.txt"));
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            try (FileReader reader = new FileReader(file)) {
                String contentWithHead = Files.readString(Path.of(file.getPath()));
                String content = contentWithHead.substring(0);
                String[] stringsFromContent = content.split("\n");

                switch (stringsFromContent[0]) {
                    case "Тип: Платежка":
                        Payroll payroll = Payroll.fromString(stringsFromContent);
                        table.getItems().add(payroll);
                        break;
                    case "Тип: Накладная":
                        System.out.println("rdy");
                        break;
                    case "Тип: Заявка на оплату":
                        System.out.println("rdy");
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void viewDocument() {
        Document selectedDocument = table.getSelectionModel().getSelectedItem();
        if (selectedDocument != null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Просмотр документа");
            alert.setHeaderText(null);
            alert.setContentText(selectedDocument.toString());
            alert.showAndWait();
        }
    }

    private void deleteSelectedDocuments() {
        List<Document> selectedDocuments = new ArrayList<>();
        for (Document document : table.getItems()) {
            if (document.getIsSelected()) {
                selectedDocuments.add(document);
            }
        }
        documents.removeAll(selectedDocuments);
        table.getItems().removeAll(selectedDocuments);
    }

    private HBox getButtonBox() {
        Button InvoiceButton = new Button("Накладная");
        InvoiceButton.setOnAction(event -> showDialog(new Invoice()));

        Button PayrollButton = new Button("Платежка");
        PayrollButton.setOnAction(event -> showDialog(new Payroll()));

        Button PaymentRequestButton = new Button("Заявка на оплату");
        PaymentRequestButton.setOnAction(event -> showDialog(new PaymentRequest()));

        Button saveButton = new Button("Сохранить");
        saveButton.setOnAction(event -> saveToFile());

        Button loadButton = new Button("Загрузить");
        loadButton.setOnAction(event -> loadFromFile());

        Button viewButton = new Button("Просмотреть");
        viewButton.setOnAction(event -> viewDocument());

        Button deleteButton = new Button("Удалить");
        deleteButton.setOnAction(event -> deleteSelectedDocuments());

        HBox buttonsBox = new HBox(10, InvoiceButton, PayrollButton, PaymentRequestButton,
                saveButton, loadButton, viewButton, deleteButton);
        buttonsBox.setPadding(new Insets(10));
        return buttonsBox;
    }

}