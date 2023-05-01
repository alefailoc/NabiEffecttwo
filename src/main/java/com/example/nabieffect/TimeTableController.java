package com.example.nabieffect;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Optional;
import java.util.HashMap;
public class TimeTableController {
    public ObservableList<Task> tasks = FXCollections.observableArrayList();
    public HBox weekendHBox;
    public HBox weekdayHBox;
    TableColumn<Task, String> mondayColumn = new TableColumn<Task, String>("Monday");
    TableView<Task> mondayTable = new TableView<Task>();
    TableView<Task> tuesdayTable = new TableView<Task>();
    TableView<Task> wednesdayTable = new TableView<Task>();
    TableView<Task> thursdayTable = new TableView<Task>();
    TableView<Task> fridayTable = new TableView<Task>();
    TableView<Task> saturdayTable = new TableView<Task>();
    TableView<Task> sundayTable = new TableView<Task>();
    @FXML
    private Label welcomeText;

    public TimeTableController() {
    }

    @FXML
    protected void monthBtn() throws IOException {
        StartApplication.setRoot("Calendar-view");
    }

    public void initialize() {



        saturdayTable.setPrefWidth(200);
        sundayTable.setPrefWidth(200);

        TableColumn<Task, String> saturdayColumn = new TableColumn<Task, String>("Saturday");
        saturdayColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("task"));
        saturdayColumn.setPrefWidth(200);
        saturdayTable.getColumns().add(saturdayColumn);

        TableColumn<Task, String> sundayColumn = new TableColumn<Task, String>("Sunday");
        sundayColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("task"));
        sundayColumn.setPrefWidth(200);
        sundayTable.getColumns().add(sundayColumn);

        weekendHBox.getChildren().add(saturdayTable);
        weekendHBox.getChildren().add(sundayTable);

        //Weekday code, tables

        //mondayTable.setEditable(true);
        //tuesdayTable.setEditable(true);
        //wednesdayTable.setEditable(true);
        //thursdayTable.setEditable(true);
        //fridayTable.setEditable(true);

        mondayColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("task"));
        mondayColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn<Task, String> tuesdayColumn = new TableColumn<Task, String>("Tuesday");
        tuesdayColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("task"));
        tuesdayColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn<Task, String> wednesdayColumn = new TableColumn<Task, String>("Wednesday");
        wednesdayColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("task"));
        wednesdayColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn<Task, String> thursdayColumn = new TableColumn<Task, String>("Thursday");
        thursdayColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("task"));
        thursdayColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn<Task, String> fridayColumn = new TableColumn<Task, String>("Friday");
        fridayColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("task"));
        fridayColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        mondayTable.getColumns().add(mondayColumn);
        tuesdayTable.getColumns().add(tuesdayColumn);
        wednesdayTable.getColumns().add(wednesdayColumn);
        thursdayTable.getColumns().add(thursdayColumn);
        fridayTable.getColumns().add(fridayColumn);

        mondayTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tuesdayTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        wednesdayTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        thursdayTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        fridayTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        weekdayHBox.getChildren().add(mondayTable);
        weekdayHBox.getChildren().add(tuesdayTable);
        weekdayHBox.getChildren().add(wednesdayTable);
        weekdayHBox.getChildren().add(thursdayTable);
        weekdayHBox.getChildren().add(fridayTable);

    }



    public void monthBtn(ActionEvent actionEvent) throws IOException { monthBtn();

    }
    }

