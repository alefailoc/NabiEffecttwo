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
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;
import java.util.Optional;
import java.util.HashMap;

import static com.example.nabieffect.StartApplication.tasks;

public class TimeTableController {
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

        //Week code, tables

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

        TableColumn<Task, String> sundayColumn = new TableColumn<Task, String>("Sunday");
        sundayColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("task"));
        sundayColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        mondayTable.getColumns().add(mondayColumn);
        tuesdayTable.getColumns().add(tuesdayColumn);
        wednesdayTable.getColumns().add(wednesdayColumn);
        thursdayTable.getColumns().add(thursdayColumn);
        fridayTable.getColumns().add(fridayColumn);
        saturdayTable.getColumns().add(saturdayColumn);
        sundayTable.getColumns().add(sundayColumn);

        mondayTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tuesdayTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        wednesdayTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        thursdayTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        fridayTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        saturdayTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        sundayTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        weekdayHBox.getChildren().add(mondayTable);
        weekdayHBox.getChildren().add(tuesdayTable);
        weekdayHBox.getChildren().add(wednesdayTable);
        weekdayHBox.getChildren().add(thursdayTable);
        weekdayHBox.getChildren().add(fridayTable);
        weekdayHBox.getChildren().add(saturdayTable);
        weekdayHBox.getChildren().add(sundayTable);

        /**
         * 1.What is the current week date starting from the sunday.
         **/
        LocalDate localDate = LocalDate.now();
        DayOfWeek weekStart = DayOfWeek.SUNDAY;
        LocalDate localStartOfWeekDate = localDate.with(TemporalAdjusters.previousOrSame(weekStart));
        System.out.println(localStartOfWeekDate);
        /**
         * 2.from the first date , calculate the last day of the week by adding 6
         **/
        LocalDate localEndOfWeekDate = localStartOfWeekDate.plusDays(6);
        System.out.println(localEndOfWeekDate);

        /**
         * 3.Go through all the tasks and check if it is within the boundaries of the week
         * add the tasks to the correct column for the day
         */
        for (Task t:tasks){
            //Start of week
            boolean taskAfterCurrentWeekStart = t.getDueDate().isAfter(localStartOfWeekDate);
            boolean taskOnCurrentWeekStart = t.getDueDate().isEqual(localStartOfWeekDate);

            // End of week
            boolean taskBeforeCurrentWeekEnd = t.getDueDate().isBefore(localStartOfWeekDate);
            boolean taskOnCurrentWeekEnd = t.getDueDate().isEqual(localStartOfWeekDate);

            if(((taskAfterCurrentWeekStart||taskOnCurrentWeekStart)&&(taskBeforeCurrentWeekEnd||taskOnCurrentWeekEnd))){

                /**
                 * 4.add the tasks to the correct table column.
                  **/

                    if ((t.dueDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH).equals("Monday"))) {
                       /** chatGPT told me to add the code below so the table headline would have the correct date number
                        * mondayColumn.setText("Monday" + t.dueDate.getDayOfMonth());
                        * It doesn't work
                        */
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
                    } else if ((t.dueDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH).equals("Sunday"))) {
                        sundayTable.getItems().add(t);
                    }



            }
        }
    }

    private void updateCalendar() {
        /**
         * clear all tasks
         * go through all the list of tasks
         *      if the task is this monday
         *          add it to the monday column
         *
         *     if the task is tuesday
         *          add task to tuesday column
         *     ect...
         */
        // mondayTable.getColumns().clear();

        LocalDate now = LocalDate.now();


        for (Task t : tasks) {
            if ((t.dueDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH).equals("Monday"))) {
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
            } else if ((t.dueDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH).equals("Sunday"))) {
                sundayTable.getItems().add(t);
            }
        }
    }

    public void monthBtn(ActionEvent actionEvent) throws IOException {
        monthBtn();

    }
}

