package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class Main extends Application {
    private BorderPane bp = new BorderPane();
    private GridPane gp = new GridPane(); // for adding new student record

    private TableView<StudentRecord> students = new TableView<>();

    private TextField SID = new TextField();
    private Label SIDLabel = new Label();
    private TextField assignment = new TextField();
    private Label assignmentLabel = new Label();
    private TextField midterm = new TextField();
    private Label midtermLabel = new Label();
    private TextField finalExam = new TextField();
    private Label finalExamLabel = new Label();

    private Button addButton = new Button();

    @Override
    public void start(Stage primaryStage) throws Exception{
        // creating table

        // Student ID
        TableColumn<StudentRecord, String> sidCol = new TableColumn<>("SID");
        sidCol.setPrefWidth(100);
        sidCol.setCellValueFactory(new PropertyValueFactory<>("studentID"));

        //Assignments
        TableColumn<StudentRecord, Float> assignCol = new TableColumn<>("Assignments");
        assignCol.setPrefWidth(100);
        assignCol.setCellValueFactory(new PropertyValueFactory<>("assignment"));

        //Midterm
        TableColumn<StudentRecord, Float> midCol = new TableColumn<>("Midterm");
        midCol.setPrefWidth(100);
        midCol.setCellValueFactory(new PropertyValueFactory<>("midterm"));

        //Final Exam
        TableColumn<StudentRecord, Float> examCol = new TableColumn<>("Final Exam");
        examCol.setPrefWidth(100);
        examCol.setCellValueFactory(new PropertyValueFactory<>("finalExam"));

        //Final Mark
        TableColumn<StudentRecord, Float> markCol = new TableColumn<>("Final Mark");
        markCol.setPrefWidth(100);
        markCol.setCellValueFactory(new PropertyValueFactory<>("finalMark"));

        //Letter Grade
        TableColumn<StudentRecord, String> letterCol = new TableColumn<>("Letter Grade");
        letterCol.setPrefWidth(100);
        letterCol.setCellValueFactory(new PropertyValueFactory<>("letter"));

        students.setItems(DataSource.getAllMarks());
        students.getColumns().addAll(sidCol, assignCol, midCol, examCol, markCol, letterCol);

        // form at the bottom
        SIDLabel.setText("SID:");
        SID.setPromptText("SID");
        assignmentLabel.setText("Assignments:");
        assignment.setPromptText("Assignments/100");
        midtermLabel.setText("Midterm:");
        midterm.setPromptText("Midterm/100");
        finalExamLabel.setText("Final Exam:");
        finalExam.setPromptText("Final Exam/100");

        addButton.setText("Add");

        // creating layout of form for adding student info to table
        gp.setPadding(new Insets(10, 10, 10, 10));
        gp.setHgap(15);
        gp.setVgap(10);

        // adding items to grid
        gp.add(SIDLabel, 0, 0);
        gp.add(SID, 1, 0);

        gp.add(assignmentLabel, 2, 0);
        gp.add(assignment, 3, 0);

        gp.add(midtermLabel, 0, 1);
        gp.add(midterm, 1, 1);

        gp.add(finalExamLabel, 2, 1);
        gp.add(finalExam, 3, 1);

        gp.add(addButton, 1, 4);

        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                students.getItems().add(new StudentRecord(
                        SID.getText(),
                        Float.parseFloat(assignment.getText()),
                        Float.parseFloat(midterm.getText()),
                        Float.parseFloat(finalExam.getText())
                ));
                SID.clear();
                assignment.clear();
                midterm.clear();
                finalExam.clear();
            }
        });

        bp.setCenter(students);
        bp.setBottom(gp);
        primaryStage.setTitle("Lab 05 Solutions");
        Scene scene = new Scene(bp, 600, 440);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
