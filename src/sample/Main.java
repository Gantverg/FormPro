package sample;

import com._1c.v8.ibis.admin.IClusterInfo;
import com._1c.v8.ibis.admin.client.AgentAdminConnectorFactory;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.models.Cluster;
import sample.models.Connection;
import sample.models.Infobase;
import sample.view.Controller;

import java.io.IOException;

public class Main extends Application {
    private Connection connection;
    private Stage primaryStage;
    private AnchorPane rootLayout;

    @Override
    public void start(Stage primaryStage) throws Exception{
        example();
        try {
            // Загружаем корневой макет из fxml файла.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/MainScreen.fxml"));
            rootLayout = (AnchorPane) loader.load();

            // Отображаем сцену, содержащую корневой макет.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            // Даём контроллеру доступ к главному приложению.
            Controller controller = loader.getController();
            controller.setMain(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void example(){
        connection = new Connection(new AgentAdminUtil(new AgentAdminConnectorFactory()));
        /*
        Cluster cluster1 = new Cluster("name1","host1");
        cluster1.addInfobase(new Infobase("infobase 1.1"));
        cluster1.addInfobase(new Infobase("infobase 1.2"));
        cluster1.addInfobase(new Infobase("infobase 1.3"));
        clusters.add(cluster1);
        Cluster cluster2 = new Cluster("name2","host2");
        cluster2.addInfobase(new Infobase("infobase 2.1"));
        cluster2.addInfobase(new Infobase("infobase 2.2"));
        cluster2.addInfobase(new Infobase("infobase 2.3"));
        clusters.add(cluster2);
        */
    }

    public static void main(String[] args) {
        launch(args);
    }

    public Connection getConnection() { return this.connection; }

}
