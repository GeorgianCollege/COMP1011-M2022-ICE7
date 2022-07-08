package ca.georgiancollege.comp1011m2022ice7;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class FileChooserController
{
    private String activeFilePath = "vector2d.txt";


    @FXML
    private BorderPane borderPane;

    @FXML
    private Button ReadFromFileButton;

    @FXML
    private Button SaveToFileButton;

    @FXML
    private Button SelectFileButton;

    @FXML
    private TextArea textArea;

    @FXML
    void SaveToFileButtonClicked(ActionEvent event)
    {
        try(Formatter output = new Formatter(activeFilePath) )
        {
            ArrayList<Vector2D> vector2DData = DBManager.Instance().readVectorTable();

            vector2DData.sort(null);

            for (var vector2d : vector2DData)
            {
                // serialize our Vector2D data
                output.format("%.1f %.1f%n", vector2d.getX(), vector2d.getY());
            }
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    @FXML
    void ReadFromFileButtonClicked(ActionEvent event)
    {
        try(Scanner input = new Scanner(Paths.get(activeFilePath)))
        {
           while(input.hasNext())
           {
               System.out.printf("(%.1f, %.1f)%n", input.nextFloat(), input.nextFloat());
           }
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    @FXML
    void SelectFileButtonClicked(ActionEvent event)
    {
        // configure dialog (modal window) allowing selection of a file
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt"),
                new FileChooser.ExtensionFilter("All Files (*.*)", "*.*")
        );


        // display files in folder from which the app was launched
        fileChooser.setInitialDirectory(new File("."));

        // display the FileChooser Dialog
        File file = fileChooser.showOpenDialog(borderPane.getScene().getWindow());


        // process selected Path or display a message
        if(file != null)
        {
            activeFilePath = file.getName();
        }
        else
        {
            textArea.setText("Select file");
        }
    }

}
