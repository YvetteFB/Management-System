package management;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(final Stage primaryStage) throws Exception{
        try {
            Parent root = FXMLLoader.load(getClass().getResource("base.fxml"));
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            //scene.setFill(Color.TRANSPARENT);


            root.setOnMousePressed(mouseEvent -> {
                xOffset = mouseEvent.getSceneX();
                yOffset = mouseEvent.getSceneY();
            });

            root.setOnMouseDragged(event -> {
                primaryStage.setX(event.getSceneX() - xOffset);
                primaryStage.setY(event.getSceneY() - yOffset);
            });

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();


        }catch (Exception e){
            System.out.println("The error is " +e.getMessage());
        }


    }


    public static void main(String[] args) {
        launch(args);
    }
}
