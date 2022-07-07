package ca.georgiancollege.comp1011m2022ice7;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class FileChooserController {

    @FXML
    private Button SaveToFileButton;

    @FXML
    void SaveToFileButtonClicked(ActionEvent event)
    {
        try(Formatter output = new Formatter("vector2d.txt") )
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
        try(Scanner input = new Scanner(Paths.get("vector2d.txt")))
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

}
