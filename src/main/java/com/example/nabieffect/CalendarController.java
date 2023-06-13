package com.example.nabieffect;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
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
    public AnchorPane mainPane;
    public ColorPicker colourPicker;

    ArrayList<HBox> myHBoxes = new ArrayList<>();

    @FXML
    protected void monthBtn() throws IOException {
        StartApplication.setRoot("hello-view");
    }

    public void initialize() {
        loadTasks();
        myHBoxes.add(weekOneHBox);
        myHBoxes.add(weekTwoHBox);
        myHBoxes.add(weekThreeHBox);
        myHBoxes.add(weekFourHBox);

/*

1. For each HBox
    2. create 7 tables
    3.  for each table create one column
    4. ADD the column to the table
    5. add the table to the hbox

    */
        for (HBox hBox: myHBoxes ) {
            for (int i = 0; i < 7; i++) {
                TableView<Task> currentDayTable = new TableView<Task>();
                TableColumn<Task, String> column = new TableColumn<Task, String>(getDay(i));
                column.setCellValueFactory(new PropertyValueFactory<Task, String>("task"));
                column.setPrefWidth(110);

                currentDayTable.getColumns().add(column);
                //weekOneHBox.getChildren().add(currentDayTable);
                hBox.getChildren().add(currentDayTable);
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
        //delete all tasks first
        for (HBox hBox: myHBoxes ) {
            for (int i = 0; i < 7; i++) {
                TableView<Task> tableView =  (TableView<Task>) hBox.getChildren().get(i);
                tableView.getColumns().removeAll();
            }

        }


        for (Task t : tasks) {
            // Find Week Number
            // https://www.baeldung.com/java-get-week-number
            Calendar calendar = Calendar.getInstance(Locale.US);
            calendar.set(t.getDueDate().getYear(), t.getDueDate().getMonthValue() - 1, t.getDueDate().getDayOfMonth());
            int weekOfMonth = calendar.get(Calendar.WEEK_OF_MONTH) - 1;

            int dayOfWeek = t.getDueDate().getDayOfWeek().getValue() - 1;

            HBox currentHBox = myHBoxes.get(weekOfMonth);
            if (currentHBox.getChildren().size() > dayOfWeek && currentHBox.getChildren().get(dayOfWeek) instanceof TableView) {
                TableView<Task> tableView = (TableView<Task>) currentHBox.getChildren().get(dayOfWeek);
                TableColumn<Task, String> column = (TableColumn<Task, String>) tableView.getColumns().get(0); // Assuming you have only one column

                column.setCellFactory(columnData -> {
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
                                setStyle("-fx-background-color: " + toHexCode(task.getColor()));

                            }
                        }
                    };

                    cell.setOnMouseClicked(event -> {
                        if (event.getClickCount() == 2 && !cell.isEmpty()) {
                            Task selectedTask = cell.getTableView().getItems().get(cell.getIndex());
                            showTaskDetailsPopup(selectedTask);
                        }
                    });

                    return cell;
                });

                tableView.getItems().add(t);
            }
        }
    }
    private void showTaskDetailsPopup(Task task) {
        // Create a new stage for the pop-up window
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Task Details");

        // Create the content for the pop-up window
        VBox popupContent = new VBox();
        popupContent.setPadding(new Insets(10));
        Label taskLabel = new Label("Task: " + task.getTask());
        Label detailsLabel = new Label("Details: " + task.getTaskDetails());
        Label dueDateLabel = new Label("Due Date: " + task.getDueDate().toString());
        Label expectedTimeLabel = new Label("Expected Time: " + task.getExpectedTime() + " hrs");
        Button delButton = new Button("Delete Task");
        delButton.setOnAction(event -> {
            tasks.remove(task);
                    updateCalendar();
        });
        popupContent.getChildren().addAll(taskLabel, detailsLabel, dueDateLabel, expectedTimeLabel,delButton);

        // Create the scene and set it to the stage
        Scene popupScene = new Scene(popupContent, 300, 200);
        popupStage.setScene(popupScene);

        // Show the pop-up window
        popupStage.showAndWait();
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

         //https://stackoverflow.com/questions/72583321/how-to-set-pane-colour-from-colour-picker
    public void colourPickerAction(ActionEvent event) {
        Color newColour = colourPicker.getValue();
        mainPane.setStyle("-fx-background-color: " + toHexCode(newColour) + ";");
    }
}



