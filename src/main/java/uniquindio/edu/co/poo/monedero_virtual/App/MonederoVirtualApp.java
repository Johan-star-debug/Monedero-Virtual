package uniquindio.edu.co.poo.monedero_virtual.App;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MonederoVirtualApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "/uniquindio/edu/co/poo/monedero_virtual/view/InicioView.fxml"
                )
        );

        Scene scene = new Scene(loader.load());

        stage.setTitle("Monedero Virtual");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}