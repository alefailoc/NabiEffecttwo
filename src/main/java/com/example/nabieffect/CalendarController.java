package com.example.nabieffect;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.*;

import static com.example.nabieffect.StartApplication.tasks;


public class CalendarController {

    public HBox weekOneHBox;
    public HBox weekTwoHBox;
    public HBox weekThreeHBox;
    public HBox weekFourHBox;
    TableView<Task> sunOneTable = new TableView<Task>();
    TableView<Task> monOneTable = new TableView<Task>();
    TableView<Task> tuesOneTable = new TableView<Task>();
    TableView<Task> wedOneTable = new TableView<Task>();
    TableView<Task> thursOneTable = new TableView<Task>();
    TableView<Task> friOneTable = new TableView<Task>();
    TableView<Task> satOneTable = new TableView<Task>();

    TableView<Task> sunTwoTable = new TableView<Task>();
    TableView<Task> monTwoTable = new TableView<Task>();
    TableView<Task> tuesTwoTable = new TableView<Task>();
    TableView<Task> wedTwoTable = new TableView<Task>();
    TableView<Task> thursTwoTable = new TableView<Task>();
    TableView<Task> friTwoTable = new TableView<Task>();
    TableView<Task> satTwoTable = new TableView<Task>();

    TableView<Task> sunThreeTable = new TableView<Task>();
    TableView<Task> monThreeTable = new TableView<Task>();
    TableView<Task> tuesThreeTable = new TableView<Task>();
    TableView<Task> wedThreeTable = new TableView<Task>();
    TableView<Task> thursThreeTable = new TableView<Task>();
    TableView<Task> friThreeTable = new TableView<Task>();
    TableView<Task> satThreeTable = new TableView<Task>();


    TableView<Task> sunFourTable = new TableView<Task>();
    TableView<Task> monFourTable = new TableView<Task>();
    TableView<Task> tuesFourTable = new TableView<Task>();
    TableView<Task> wedFourTable = new TableView<Task>();
    TableView<Task> thursFourTable = new TableView<Task>();
    TableView<Task> friFourTable = new TableView<Task>();
    TableView<Task> satFourTable = new TableView<Task>();


    //Monday
    TableView[] mondayTables = {monOneTable, monTwoTable, monThreeTable, monFourTable};
    //Tuesday
    TableView[] tuesdayTables = {tuesOneTable, tuesTwoTable, tuesThreeTable, tuesFourTable};
    //Wednesday
    TableView[] wednesdayTables = {wedOneTable, wedTwoTable, wedThreeTable, wedFourTable};
    //Thursday
    TableView[] thursdayTables = {thursOneTable, thursTwoTable, thursThreeTable, thursFourTable};
    //Friday
    TableView[] fridayTables = {friOneTable, friTwoTable, friThreeTable, friFourTable};
    //Saturday
    TableView[] saturdayTables = {satOneTable, satTwoTable, satThreeTable, satFourTable};
    //Sunday
    TableView[] sundayTables = {sunOneTable, sunTwoTable, sunThreeTable, sunFourTable};


    @FXML
    protected void monthBtn() throws IOException {
        StartApplication.setRoot("hello-view");
    }

    public void initialize() {
        loadTasks();
/**
        TableColumn<Task, String> sundayColumn = new TableColumn<Task, String>("sun");
        sundayColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("task"));
        sundayColumn.setPrefWidth(82);
        sunOneTable.getColumns().add(sundayColumn);

        TableColumn<Task, String> mondayColumn = new TableColumn<Task, String>("mon");
        mondayColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("task"));
        mondayColumn.setPrefWidth(82);
        monOneTable.getColumns().add(mondayColumn);

        TableColumn<Task, String> tuesdayColumn = new TableColumn<Task, String>("tues");
        tuesdayColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("task"));
        tuesdayColumn.setPrefWidth(82);
        tuesOneTable.getColumns().add(tuesdayColumn);

        TableColumn<Task, String> wednesdayColumn = new TableColumn<Task, String>("wed");
        wednesdayColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("task"));
        wednesdayColumn.setPrefWidth(82);
        wedOneTable.getColumns().add(wednesdayColumn);

        TableColumn<Task, String> thursdayColumn = new TableColumn<Task, String>("thurs");
        thursdayColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("task"));
        thursdayColumn.setPrefWidth(82);
        thursOneTable.getColumns().add(thursdayColumn);

        TableColumn<Task, String> fridayColumn = new TableColumn<Task, String>("fri");
        fridayColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("task"));
        fridayColumn.setPrefWidth(82);
        friOneTable.getColumns().add(fridayColumn);

        TableColumn<Task, String> saturdayColumn = new TableColumn<Task, String>("sat");
        saturdayColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("task"));
        saturdayColumn.setPrefWidth(82);
        satOneTable.getColumns().add(saturdayColumn);

        weekOneHBox.getChildren().add(sunOneTable);
        weekOneHBox.getChildren().add(monOneTable);
        weekOneHBox.getChildren().add(tuesOneTable);
        weekOneHBox.getChildren().add(wedOneTable);
        weekOneHBox.getChildren().add(thursOneTable);
        weekOneHBox.getChildren().add(friOneTable);
        weekOneHBox.getChildren().add(satOneTable);

        // week 2


        TableColumn<Task, String> sundayTwoColumn = new TableColumn<Task, String>("sun");
        sundayTwoColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("task"));
        sundayTwoColumn.setPrefWidth(82);
        sundayTwoColumn.setCellFactory(column -> {
            return new TextFieldTableCell<Task, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty || item == null) {
                        setText(null);
                        setStyle("");  // Reset cell style
                    } else {
                        Task task = getTableView().getItems().get(getIndex());
                        setText(item);
                        setStyle("-fx-background-color: " + toHexCode(task.getColor()));

                    }
                }
            };
        });
        sunTwoTable.getColumns().add(sundayTwoColumn);

        TableColumn<Task, String> mondayTwoColumn = new TableColumn<Task, String>("mon");
        mondayTwoColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("task"));
        mondayTwoColumn.setPrefWidth(82);
        mondayTwoColumn.setCellFactory(column -> {
            return new TextFieldTableCell<Task, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty || item == null) {
                        setText(null);
                        setStyle("");  // Reset cell style
                    } else {
                        Task task = getTableView().getItems().get(getIndex());
                        setText(item);
                        setStyle("-fx-background-color: " + toHexCode(task.getColor()));

                    }
                }
            };
        });
        monTwoTable.getColumns().add(mondayTwoColumn);

        TableColumn<Task, String> tuesdayTwoColumn = new TableColumn<Task, String>("tues");
        tuesdayTwoColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("task"));
        tuesdayTwoColumn.setPrefWidth(82);
        tuesdayTwoColumn.setCellFactory(column -> {
            return new TextFieldTableCell<Task, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty || item == null) {
                        setText(null);
                        setStyle("");  // Reset cell style
                    } else {
                        Task task = getTableView().getItems().get(getIndex());
                        setText(item);
                        setStyle("-fx-background-color: " + toHexCode(task.getColor()));

                    }
                }
            };
        });
        tuesTwoTable.getColumns().add(tuesdayTwoColumn);

        TableColumn<Task, String> wednesdayTwoColumn = new TableColumn<Task, String>("wed");
        wednesdayTwoColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("task"));
        wednesdayTwoColumn.setPrefWidth(82);
        wednesdayTwoColumn.setCellFactory(column -> {
            return new TextFieldTableCell<Task, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty || item == null) {
                        setText(null);
                        setStyle("");  // Reset cell style
                    } else {
                        Task task = getTableView().getItems().get(getIndex());
                        setText(item);
                        setStyle("-fx-background-color: " + toHexCode(task.getColor()));

                    }
                }
            };
        });
        wedTwoTable.getColumns().add(wednesdayTwoColumn);

        TableColumn<Task, String> thursdayTwoColumn = new TableColumn<Task, String>("thurs");
        thursdayTwoColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("task"));
        thursdayTwoColumn.setPrefWidth(82);
        thursdayTwoColumn.setCellFactory(column -> {
            return new TextFieldTableCell<Task, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty || item == null) {
                        setText(null);
                        setStyle("");  // Reset cell style
                    } else {
                        Task task = getTableView().getItems().get(getIndex());
                        setText(item);
                        setStyle("-fx-background-color: " + toHexCode(task.getColor()));

                    }
                }
            };
        });
        thursTwoTable.getColumns().add(thursdayTwoColumn);

        TableColumn<Task, String> fridayTwoColumn = new TableColumn<Task, String>("fri");
        fridayTwoColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("task"));
        fridayTwoColumn.setPrefWidth(82);
        fridayTwoColumn.setCellFactory(column -> {
            return new TextFieldTableCell<Task, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty || item == null) {
                        setText(null);
                        setStyle("");  // Reset cell style
                    } else {
                        Task task = getTableView().getItems().get(getIndex());
                        setText(item);
                        setStyle("-fx-background-color: " + toHexCode(task.getColor()));

                    }
                }
            };
        });
        friTwoTable.getColumns().add(fridayTwoColumn);

        TableColumn<Task, String> saturdayTwoColumn = new TableColumn<Task, String>("sat");
        saturdayTwoColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("task"));
        saturdayTwoColumn.setPrefWidth(82);

        // Set cell factory to change cell color
        saturdayTwoColumn.setCellFactory(column -> {
            return new TextFieldTableCell<Task, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty || item == null) {
                        setText(null);
                        setStyle("");  // Reset cell style
                    } else {
                        Task task = getTableView().getItems().get(getIndex());
                        setText(item);
                        setStyle("-fx-background-color: " + toHexCode(task.getColor()));

                    }
                }
            };
        });

        satTwoTable.getColumns().add(saturdayTwoColumn);

        weekTwoHBox.getChildren().add(sunTwoTable);
        weekTwoHBox.getChildren().add(monTwoTable);
        weekTwoHBox.getChildren().add(tuesTwoTable);
        weekTwoHBox.getChildren().add(wedTwoTable);
        weekTwoHBox.getChildren().add(thursTwoTable);
        weekTwoHBox.getChildren().add(friTwoTable);
        weekTwoHBox.getChildren().add(satTwoTable);

        //week 3


        TableColumn<Task, String> sundayThreeColumn = new TableColumn<Task, String>("sun");
        sundayThreeColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("task"));
        sundayThreeColumn.setPrefWidth(82);
        sunThreeTable.getColumns().add(sundayThreeColumn);

        TableColumn<Task, String> mondayThreeColumn = new TableColumn<Task, String>("mon");
        mondayThreeColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("task"));
        mondayThreeColumn.setPrefWidth(82);
        monThreeTable.getColumns().add(mondayThreeColumn);

        TableColumn<Task, String> tuesdayThreeColumn = new TableColumn<Task, String>("tues");
        tuesdayThreeColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("task"));
        tuesdayThreeColumn.setPrefWidth(82);
        tuesThreeTable.getColumns().add(tuesdayThreeColumn);

        TableColumn<Task, String> wednesdayThreeColumn = new TableColumn<Task, String>("wed");
        wednesdayThreeColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("task"));
        wednesdayThreeColumn.setPrefWidth(82);
        wedThreeTable.getColumns().add(wednesdayThreeColumn);

        TableColumn<Task, String> thursdayThreeColumn = new TableColumn<Task, String>("thurs");
        thursdayThreeColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("task"));
        thursdayThreeColumn.setPrefWidth(82);
        thursThreeTable.getColumns().add(thursdayThreeColumn);

        TableColumn<Task, String> fridayThreeColumn = new TableColumn<Task, String>("fri");
        fridayThreeColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("task"));
        fridayThreeColumn.setPrefWidth(82);
        friThreeTable.getColumns().add(fridayThreeColumn);

        TableColumn<Task, String> saturdayThreeColumn = new TableColumn<Task, String>("sat");
        saturdayThreeColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("task"));
        saturdayThreeColumn.setPrefWidth(82);
        satThreeTable.getColumns().add(saturdayThreeColumn);

        weekThreeHBox.getChildren().add(sunThreeTable);
        weekThreeHBox.getChildren().add(monThreeTable);
        weekThreeHBox.getChildren().add(tuesThreeTable);
        weekThreeHBox.getChildren().add(wedThreeTable);
        weekThreeHBox.getChildren().add(thursThreeTable);
        weekThreeHBox.getChildren().add(friThreeTable);
        weekThreeHBox.getChildren().add(satThreeTable);


        //week 4

        TableColumn<Task, String> sundayFourColumn = new TableColumn<Task, String>("sun");
        sundayFourColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("task"));
        sundayFourColumn.setPrefWidth(82);
        sunFourTable.getColumns().add(sundayFourColumn);

        TableColumn<Task, String> mondayFourColumn = new TableColumn<Task, String>("mon");
        mondayFourColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("task"));
        mondayFourColumn.setPrefWidth(82);
        monFourTable.getColumns().add(mondayFourColumn);

        TableColumn<Task, String> tuesdayFourColumn = new TableColumn<Task, String>("tues");
        tuesdayFourColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("task"));
        tuesdayFourColumn.setPrefWidth(82);
        tuesFourTable.getColumns().add(tuesdayFourColumn);

        TableColumn<Task, String> wednesdayFourColumn = new TableColumn<Task, String>("wed");
        wednesdayFourColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("task"));
        wednesdayFourColumn.setPrefWidth(82);
        wedFourTable.getColumns().add(wednesdayFourColumn);

        TableColumn<Task, String> thursdayFourColumn = new TableColumn<Task, String>("thurs");
        thursdayFourColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("task"));
        thursdayFourColumn.setPrefWidth(82);
        thursFourTable.getColumns().add(thursdayFourColumn);

        TableColumn<Task, String> fridayFourColumn = new TableColumn<Task, String>("fri");
        fridayFourColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("task"));
        fridayFourColumn.setPrefWidth(82);
        friFourTable.getColumns().add(fridayFourColumn);

        TableColumn<Task, String> saturdayFourColumn = new TableColumn<Task, String>("sat");
        saturdayFourColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("task"));
        saturdayFourColumn.setPrefWidth(82);
        satFourTable.getColumns().add(saturdayFourColumn);

        weekFourHBox.getChildren().add(sunFourTable);
        weekFourHBox.getChildren().add(monFourTable);
        weekFourHBox.getChildren().add(tuesFourTable);
        weekFourHBox.getChildren().add(wedFourTable);
        weekFourHBox.getChildren().add(thursFourTable);
        weekFourHBox.getChildren().add(friFourTable);
        weekFourHBox.getChildren().add(satFourTable);
*/

        for (int i = 0; i < 7; i++) {
            TableColumn<Task, String> column = new TableColumn<Task, String>(getDay(i));
            column.setCellValueFactory(new PropertyValueFactory<Task, String>("task"));
            column.setPrefWidth(82);
            for (HBox hBox : Arrays.asList(weekOneHBox, weekTwoHBox, weekThreeHBox, weekFourHBox)) {
                hBox.getChildren().add(column);
            }
        }

        updateCalendar();
    }

    private String getDay(int i) {
            switch (i) {
                case 0:
                    return "mon";
                case 1:
                    return "tues";
                case 2:
                    return "wed";
                case 3:
                    return "thurs";
                case 4:
                    return "fri";
                case 5:
                    return "sat";
                case 6:
                    return "sun";
                default:
                    return "";
            }
        }


    public void taskBtn(ActionEvent actionEvent) {
        makeDialog();
    }

    private String toHexCode(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }

    public void makeDialog() {

        System.out.println(tasks);
        Dialog<Task> dialog = new Dialog<>();
        dialog.initModality(Modality.NONE);
        Stage stage = (Stage) StartApplication.getScene().getWindow();
        dialog.initOwner(stage);

        //start making the stuff in the dialog
        dialog.setTitle("insert");
        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        Label taskLabel = new Label("task");
        TextField task = new TextField("");
        Label detailsLabel = new Label("details");
        TextField detail = new TextField("");

        //Add a Date Picker control
        Label dueDateLabel = new Label("due date");
        DatePicker dueDate = new DatePicker();

        // Add a Spinner control
        Label expectedTimeTaken = new Label("Expected time(hrs)");
        Spinner<Double> timeExpected = new Spinner<>(0.50, 10, 0);

        Label maxHoursPerTask = new Label("max hours per task on calendar");
        TextField maxHours = new TextField("");

        dialogPane.setContent(new VBox(taskLabel, task, detailsLabel, detail, dueDateLabel, dueDate, expectedTimeTaken, timeExpected, maxHoursPerTask,maxHours));
        //make an ok button
        final Button btOk = (Button) dialog.getDialogPane().lookupButton(ButtonType.OK);
        //Create what you want it to do when you click the button
        btOk.addEventFilter(
                ActionEvent.ACTION,
                event -> {
                    if (!detail.getText().equals("") && !task.getText().equals("") && dueDate.getValue() != null) { // if all your fields and things ARENT EMPTY
                        LocalDate date = dueDate.getValue();
                        //read them all text fields and make a new object.
                        int tasksToMake = (int) Math.ceil(timeExpected.getValue())/Integer.parseInt(maxHours.getText());
                        for(int i = 0; i< tasksToMake;i++){
                            tasks.add(new Task(task.getText(), detail.getText(), dueDate.getValue().minusDays(i), timeExpected.getValue()));
                        }

                        updateCalendar();

                    } else { //else if some text field is empty or incorrect. give them an error message
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Incorrect input");
                        alert.setHeaderText(null);
                        alert.setContentText("Make sure everything is filled in correctly.");
                        alert.showAndWait();
                        event.consume(); //consume the ok button event so it doesn't close the dialog.
                    }
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    try (FileWriter writer = new FileWriter("tasks.json")) {
                        gson.toJson(tasks, writer);
                        System.out.println("Saved.");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        Optional<Task> optionalResult = dialog.showAndWait(); //show the dialog.

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

        for (Task t : tasks) {
            //Find Week Number
            //https://www.baeldung.com/java-get-week-number
            Calendar calendar = Calendar.getInstance(Locale.US);
            calendar.set(t.getDueDate().getYear(), t.getDueDate().getMonthValue() - 1, t.getDueDate().getDayOfMonth());
            int weekOfMonth = calendar.get(Calendar.WEEK_OF_MONTH) - 1;

            if ((t.dueDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH).equals("Monday"))) {
                mondayTables[weekOfMonth].getItems().add(t);
                System.out.println(t.getDueDate());
                System.out.println("Week: "+weekOfMonth);
            } else if ((t.dueDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH).equals("Tuesday"))) {
                tuesdayTables[weekOfMonth].getItems().add(t);
                System.out.println(t.getDueDate());
                System.out.println("Week: "+weekOfMonth);
            } else if ((t.dueDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH).equals("Wednesday"))) {
                wednesdayTables[weekOfMonth].getItems().add(t);
                System.out.println(t.getDueDate());
                System.out.println("Week: "+weekOfMonth);
            } else if ((t.dueDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH).equals("Thursday"))) {
                thursdayTables[weekOfMonth].getItems().add(t);
                System.out.println(t.getDueDate());
                System.out.println("Week: "+weekOfMonth);
            } else if ((t.dueDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH).equals("Friday"))) {
                fridayTables[weekOfMonth].getItems().add(t);
                System.out.println(t.getDueDate());
                System.out.println("Week: "+weekOfMonth);
            } else if ((t.dueDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH).equals("Saturday"))) {
                saturdayTables[weekOfMonth].getItems().add(t);
                System.out.println(t.getDueDate());
                System.out.println("Week: "+weekOfMonth);
            } else if ((t.dueDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH).equals("Sunday"))) {
                sundayTables[weekOfMonth].getItems().add(t);
                System.out.println(t.getDueDate());
                System.out.println("Week: "+weekOfMonth);
            }
        }
    }


    private void loadTasks() {
        // load tasks from saved file
        //open and read Json for any previous saved data
        Gson gson = new Gson();
        try (Reader reader = new FileReader("tasks.json")) {
            //convert JSON file to Java object
            ArrayList<Task> imports = gson.fromJson(reader, new TypeToken<ArrayList<Task>>() {
            }.getType());
            tasks = FXCollections.observableArrayList(imports);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteBtn(ActionEvent event) {


         }

}



