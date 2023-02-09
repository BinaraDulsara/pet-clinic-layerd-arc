package lk.ijse.petClinic;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class AppInitializer extends Application {
    public static Stage mainStage;

    @Override
    public void start(Stage primaryStage) throws Exception {


        Parent root = FXMLLoader.load(getClass().getResource("/lk/ijse/petClinic/view/LoadingForm.fxml"));
        Scene scene = new Scene(root);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/lk/ijse/petClinic/view/icon/Screenshot_2023-01-31_005425-removebg-preview.png")));
        primaryStage.show();

        Parent loginRoot = FXMLLoader.load(getClass().getResource("/lk/ijse/petClinic/view/LoginForm.fxml"));
        Stage logStage = new Stage();
        logStage.setScene(new Scene(loginRoot, 800, 600));
        logStage.getIcons().add(new Image(getClass().getResourceAsStream("/lk/ijse/petClinic/view/icon/Screenshot_2023-01-31_005425-removebg-preview.png")));
        this.mainStage = logStage;


        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(event -> primaryStage.close());
        delay.play();
        PauseTransition delayLog = new PauseTransition(Duration.seconds(2));
        delayLog.setOnFinished(event -> logStage.show());
        delayLog.play();
        {

        }
    }
}
