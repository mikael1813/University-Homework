import controllers.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import repos.*;
import repos.database.*;
import service.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class MainApp extends Application {
    Service service;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        loadService();

        initView(primaryStage);
        primaryStage.setWidth(800);
        primaryStage.show();
    }

    private void initView(Stage primaryStage) throws IOException {
        FXMLLoader messageLoader = new FXMLLoader();
        messageLoader.setLocation(getClass().getResource("/views/Login.fxml"));
        AnchorPane messageTaskLayout = messageLoader.load();
        primaryStage.setScene(new Scene(messageTaskLayout));

        //StartController startController = messageLoader.getController();
        //startController.setService(superService);
        //StartController startController = new StartController(superService);

        LoginController loginController = messageLoader.getController();
        loginController.setService(service);
    }

    private void loadService() {
        Properties props = new Properties();
        try {
            props.load(new FileReader("bd.config"));
        } catch (IOException e) {
            System.out.println("Cannot find bd.config " + e);
        }
        System.out.println("Succes");
        LocRepository locRepository = new LocDBRepository();
        AdministratorRepository administratorRepository = new AdministratorDBRepository();
        ClientRepository clientRepository = new ClientDBRepository();
        RezervareRepository rezervareRepository = new RezervareDBRepository(props);
        SpectacolRepository spectacolRepository = new SpectacolDBRepository();

        service = new Service(administratorRepository,clientRepository,locRepository,rezervareRepository,spectacolRepository);

    }
}
