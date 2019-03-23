package sample;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.input.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

public class Main extends Application {
    private BorderPane bp = new BorderPane();
    private GridPane gp = new GridPane(); // for adding new student record

    private TableView<StudentRecord> students = new TableView<>();
    private String currentFilename = "myfile.csv";

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

        // creating a VBox for menu bar
        VBox topContainer = new VBox();
        MenuBar menuBar = new MenuBar();

        topContainer.getChildren().add(menuBar);

        // creating menu file
        Menu menuFile = new Menu("File");
        MenuItem newFile = new MenuItem("New");
        newFile.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event){
                students.getItems().clear();
            }
        });

        // creating open option
        MenuItem openFile = new MenuItem("Open");
        openFile.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event){
                //javafx file chooser
                FileChooser fc = new FileChooser();
                fc.setInitialDirectory(new File("."));
                fc.setTitle("Choose a File");
                fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV files", "*.csv"));

                //show file chooser
                File f = fc.showOpenDialog(primaryStage);
                if(f != null){
                    currentFilename = f.getName();
                    load(f);
                }
            }
        });
        // creating save option
        MenuItem saveFile = new MenuItem("Save");
        saveFile.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event){
                try{
                    save();
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
        // option of saving with ctrl+s
        saveFile.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));

        // creating save as option
        MenuItem saveAsFile = new MenuItem("Save As");
        saveAsFile.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event){
                //javafx file chooser
                FileChooser fc = new FileChooser();
                fc.setInitialDirectory(new File("."));
                fc.setTitle("Save As");
                fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter(".csv", "*.csv"));

                //show file chooser
                File f = fc.showSaveDialog(primaryStage);
                if(f != null){
                    currentFilename = f.getName();
                    try{
                        save();
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });

        // creating exit item
        MenuItem exitFile = new MenuItem("Exit");
        exitFile.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });

        // adding all file items into menu bar
        menuFile.getItems().addAll(newFile, openFile, saveFile, saveAsFile, exitFile);
        menuBar.getMenus().addAll(menuFile);

        // setting menu bar on top, table in the center, and user entry at bottom
        bp.setTop(topContainer);
        bp.setCenter(students);
        bp.setBottom(gp);
        primaryStage.setTitle("Lab 08 Solutions");
        Scene scene = new Scene(bp, 600, 440);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    // method for handling open file
    public void load(File f){
        try{
            BufferedReader br = new BufferedReader(new FileReader(f));
            //students = new TableView<>();
            students.getItems().clear();
            int count = 0;
            String row;
            while((row = br.readLine()) != null){
                if(count != 0){
                    String[] data = row.split(",");
                    StudentRecord temp =
                            new StudentRecord(data[0],
                                    Float.valueOf(data[1]),
                                    Float.valueOf(data[2]),
                                    Float.valueOf(data[3]));
                    students.getItems().add(temp);
                }
                count++;
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        students.setItems(DataSource.getAllMarks());
    }

    // method for handling save option
    public void save(){
        PrintWriter out = null;
        try {
            out = new PrintWriter(new FileOutputStream(currentFilename));
            String titles = "SID" + "," +
                    "Assignments" + "," +
                    "Midterm" + "," +
                    "Final Exam";
            out.println(titles);
            for(StudentRecord record : students.getItems()){
                String row = record.getStudentID() + "," +
                        record.getAssignment() + "," +
                        record.getMidterm() + "," +
                        record.getFinalExam();
                out.println(row);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
        System.out.println("Saved!");
    }


    public static void main(String[] args) {
        launch(args);
    }
}
