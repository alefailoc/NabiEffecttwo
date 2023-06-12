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
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;
import java.util.Optional;
import java.util.HashMap;

import static com.example.nabieffect.StartApplication.tasks;

public class TimeTableController {

    public HBox weekHBox;
    TableView<Task> mondayTable = new TableView<Task>();
    TableView<Task> tuesdayTable = new TableView<Task>();
    TableView<Task> wednesdayTable = new TableView<Task>();
    TableView<Task> thursdayTable = new TableView<Task>();
    TableView<Task> fridayTable = new TableView<Task>();
    TableView<Task> saturdayTable = new TableView<Task>();
    TableView<Task> sundayTable = new TableView<Task>();

    @FXML
    protected void monthBtn() throws IOException {
        StartApplication.setRoot("Calendar-view");
    }

    public void initialize() {


        TableColumn<Task, String> sundayColumn = new TableColumn<Task, String>("Sunday");
        sundayColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("task"));
        sundayColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn<Task, String> mondayColumn = new TableColumn<Task, String>("Monday");
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

        TableColumn<Task, String> saturdayColumn = new TableColumn<Task, String>("Saturday");
        saturdayColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("task"));
        saturdayColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        sundayTable.getColumns().add(sundayColumn);
        mondayTable.getColumns().add(mondayColumn);
        tuesdayTable.getColumns().add(tuesdayColumn);
        wednesdayTable.getColumns().add(wednesdayColumn);
        thursdayTable.getColumns().add(thursdayColumn);
        fridayTable.getColumns().add(fridayColumn);
        saturdayTable.getColumns().add(saturdayColumn);

        sundayTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        mondayTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tuesdayTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        wednesdayTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        thursdayTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        fridayTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        saturdayTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        weekHBox.getChildren().add(sundayTable);
        weekHBox.getChildren().add(mondayTable);
        weekHBox.getChildren().add(tuesdayTable);
        weekHBox.getChildren().add(wednesdayTable);
        weekHBox.getChildren().add(thursdayTable);
        weekHBox.getChildren().add(fridayTable);
        weekHBox.getChildren().add(saturdayTable);

        /**
         * 1. what is the current week date starting from the sunday.
         * https://www.baeldung.com/java-first-day-of-the-week
         **/
        LocalDate localDate = LocalDate.now();
        DayOfWeek weekStart = DayOfWeek.SUNDAY;
        LocalDate localStartOfWeekDate = localDate.with(TemporalAdjusters.previousOrSame(weekStart));
        System.out.println(localStartOfWeekDate);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("eeee d");
        sundayColumn.setText(formatWithOrdinal(localStartOfWeekDate, formatter));
        mondayColumn.setText(formatWithOrdinal(localStartOfWeekDate.plusDays(1), formatter));
        tuesdayColumn.setText(formatWithOrdinal(localStartOfWeekDate.plusDays(2), formatter));
        wednesdayColumn.setText(formatWithOrdinal(localStartOfWeekDate.plusDays(3), formatter));
        thursdayColumn.setText(formatWithOrdinal(localStartOfWeekDate.plusDays(4), formatter));
        fridayColumn.setText(formatWithOrdinal(localStartOfWeekDate.plusDays(5), formatter));
        saturdayColumn.setText(formatWithOrdinal(localStartOfWeekDate.plusDays(6), formatter));

        /**
         * 2. from the first date, calculate the last day of the week by adding 6
         **/
        LocalDate localEndOfWeekDate = localStartOfWeekDate.plusDays(6);
        System.out.println(localEndOfWeekDate);

        /**
         * 3. go through all the tasks and check if it is within the boundaries of the week.
         * https://howtodoinjava.com/java/date-time/compare-localdates/
         **/
        for (Task t:tasks) {
            //start of week
            boolean taskAfterCurrentWeekStart = t.getDueDate().isAfter(localStartOfWeekDate);
            boolean taskOnCurrentWeekStart = t.getDueDate().isEqual(localStartOfWeekDate);

            //end of week
            boolean taskBeforeCurrentWeekEnd = t.getDueDate().isBefore(localEndOfWeekDate);
            boolean taskOnCurrentWeekEnd = t.getDueDate().isEqual(localEndOfWeekDate);

            if((taskAfterCurrentWeekStart||taskOnCurrentWeekStart)&&(taskBeforeCurrentWeekEnd||taskOnCurrentWeekEnd)){

                /**
                 * 4. add the tasks to the correct column for the day.
                 **/
                if ((t.dueDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH).equals("Sunday"))) {
                    sundayTable.getItems().add(t);
                }else if ((t.dueDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH).equals("Monday"))) {
                    mondayTable.getItems().add(t);
                } else if ((t.dueDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH).equals("Tuesday"))) {
                    tuesdayTable.getItems().add(t);
                } else if ((t.dueDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH).equals("Wednesday"))) {
                    wednesdayTable.getItems().add(t);
                } else if ((t.dueDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH).equals("Thursday"))) {
                    thursdayTable.getItems().add(t);
                } else if ((t.dueDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH).equals("Friday"))) {
                    fridayTable.getItems().add(t);
                } else if ((t.dueDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH).equals("Saturday"))) {
                    saturdayTable.getItems().add(t);
                }
            }
        }

    }
    public static String formatWithOrdinal(LocalDate date, DateTimeFormatter formatter) {
        int dayOfMonth = date.getDayOfMonth();
        String ordinalSuffix;

        if (dayOfMonth >= 11 && dayOfMonth <= 13) {
            ordinalSuffix = "th";
        } else {
            int lastDigit = dayOfMonth % 10;
            switch (lastDigit) {
                case 1:
                    ordinalSuffix = "st";
                    break;
                case 2:
                    ordinalSuffix = "nd";
                    break;
                case 3:
                    ordinalSuffix = "rd";
                    break;
                default:
                    ordinalSuffix = "th";
                    break;
            }
        }

        String formattedDate = date.format(formatter);
        return formattedDate + ordinalSuffix;
    }
}

