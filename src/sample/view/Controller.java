package sample.view;

import javafx.beans.property.ListProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import sample.Main;
import sample.Models.Cluster;
import sample.Models.Infobase;

import java.util.List;

public class Controller {

    @FXML
    TableView<Cluster> clusterTableView;

    @FXML
    TableView<Infobase> infobaseTableView;

    @FXML
    Button buttonUpdateClusterList;

    @FXML
    TableColumn<Cluster, String> clusterNameColumn;

    @FXML
    TableColumn<Cluster, String> clusterHostColumn;

    @FXML
    TableColumn<Infobase, String> infobaseNameColumn;

    // Ссылка на главное приложение.
    private Main main;
//
//
    @FXML
    private void initialize() {
        //System.out.println("Start initialize...");
        clusterTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showInfobaseList(newValue));

        clusterNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().getNameProperty());
        clusterHostColumn.setCellValueFactory(
                cellData -> cellData.getValue().getHostProperty());
        infobaseNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().getNameProperty());

    }

    private void showInfobaseList(Cluster cluster){
        infobaseTableView.getItems().removeAll();
        infobaseTableView.setItems(cluster.getInfobases());
    }

    private void connectToCluster(){
        clusterTableView.setItems(main.getClusters());
    }

    public void setMain(Main main) {
        this.main = main;

//        // Добавление в таблицу данных из наблюдаемого списка
//        clusterTableView.setItems(main.getClusters());
    }


    @FXML
    private void buttonUpdateClusterListOnAction(){
        //System.out.println("buttonUpdateClusterListOnAction is running...");
        connectToCluster();
    }
}
