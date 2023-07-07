package com.example.nabieffect;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;
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
    Label noContentLabel = new Label(" ");

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

        setCellFactoryAndEventHandler(sundayColumn);
        setCellFactoryAndEventHandler(mondayColumn);
        setCellFactoryAndEventHandler(tuesdayColumn);
        setCellFactoryAndEventHandler(wednesdayColumn);
        setCellFactoryAndEventHandler(thursdayColumn);
        setCellFactoryAndEventHandler(fridayColumn);
        setCellFactoryAndEventHandler(saturdayColumn);

        sundayTable.setPlaceholder(noContentLabel);
        mondayTable.setPlaceholder(noContentLabel);
        tuesdayTable.setPlaceholder(noContentLabel);
        wednesdayTable.setPlaceholder(noContentLabel);
        thursdayTable.setPlaceholder(noContentLabel);
        fridayTable.setPlaceholder(noContentLabel);
        saturdayTable.setPlaceholder(noContentLabel);


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
                    updatePlaceholderVisibility();
                }else if ((t.dueDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH).equals("Monday"))) {
                    mondayTable.getItems().add(t);
                    updatePlaceholderVisibility();
                } else if ((t.dueDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH).equals("Tuesday"))) {
                    tuesdayTable.getItems().add(t);
                    updatePlaceholderVisibility();
                } else if ((t.dueDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH).equals("Wednesday"))) {
                    wednesdayTable.getItems().add(t);
                    updatePlaceholderVisibility();
                } else if ((t.dueDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH).equals("Thursday"))) {
                    thursdayTable.getItems().add(t);
                    updatePlaceholderVisibility();
                } else if ((t.dueDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH).equals("Friday"))) {
                    fridayTable.getItems().add(t);
                    updatePlaceholderVisibility();
                } else if ((t.dueDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH).equals("Saturday"))) {
                    saturdayTable.getItems().add(t);
                    updatePlaceholderVisibility();
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

    private void setCellFactoryAndEventHandler(TableColumn<Task, String> column) {
        column.setCellFactory(columnData -> {

            CalendarController calendarController = new CalendarController();
            TableCell<Task, String> cell = new TableCell<Task, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty || item == null) {
                        setText(null);
                        setStyle("");  // Reset cell style
                    } else {
                        Task task = getTableView().getItems().get(getIndex());
                        setText(item);
                        LocalDate currentDate = LocalDate.now();
                        int daysUntilDue = (int) ChronoUnit.DAYS.between(currentDate, task.getDueDate());

                        if (daysUntilDue == 1) {
                            setStyle("-fx-background-color: red;");
                        } else if (daysUntilDue == 2) {
                            setStyle("-fx-background-color: orange;");
                        } else {
                            setStyle("-fx-background-color: transparent;");
                        }
                    }
                }
            };

            cell.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !cell.isEmpty()) {
                    Task selectedTask = cell.getTableView().getItems().get(cell.getIndex());
                    calendarController.showTaskDetailsPopup(selectedTask);
                }
        });
        return cell;
    });
    }
    public void updatePlaceholderVisibility() {
        boolean isEmpty = sundayTable.getItems().isEmpty() &&
                mondayTable.getItems().isEmpty() &&
                tuesdayTable.getItems().isEmpty() &&
                wednesdayTable.getItems().isEmpty() &&
                thursdayTable.getItems().isEmpty() &&
                fridayTable.getItems().isEmpty() &&
                saturdayTable.getItems().isEmpty();

        noContentLabel.setVisible(isEmpty);
    }

}