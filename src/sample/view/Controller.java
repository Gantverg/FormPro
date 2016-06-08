package sample.view;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import sample.Main;
import sample.models.Cluster;
import sample.models.Connection;
import sample.models.Infobase;

import sample.AgentAdminUtil;

import java.util.List;

public class Controller {

    private final AgentAdminUtil adminUtil;

    @FXML
    TableView<Cluster> clusterTableView;

    @FXML
    TableView<Infobase> infobaseTableView;

    @FXML
    Button buttonConnect;

    @FXML
    Button buttonUpdateClusterList;

    @FXML
    TableColumn<Cluster, String> clusterNameColumn;

    @FXML
    TableColumn<Cluster, String> clusterHostColumn;

    @FXML
    TableColumn<Infobase, String> infobaseNameColumn;

    @FXML
    TextField textFieldServer;

    @FXML
    TextField textFieldPort;

    @FXML
    TextField textFieldTimeout;

    // Ссылка на главное приложение.
    private Main main;

    public Controller(AgentAdminUtil adminUtil)
    {
        this.adminUtil = adminUtil;
    }

    @FXML
    private void initialize() {
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
        textFieldServer.setText(main.getConnection().getServer());
        textFieldPort.setText(Integer.toString(main.getConnection().getPort()));
        textFieldTimeout.setText(Integer.toString(main.getConnection().getTimeout()));
    }


    @FXML
    private void buttonUpdateClusterListOnAction(){
        //System.out.println("buttonUpdateClusterListOnAction is running...");
        connectToCluster();
    }

    @FXML
    private void buttonConnectOnAction(){
        System.out.println("Connetct to "+main.getConnection().getServer()+":"+main.getConnection().getPort());
        connectToCluster();
    }

    @FXML
    private void textFieldServerOnAction(){
        main.getConnection().setServer(textFieldPort.getText());
    }

    @FXML
    private void textFieldPortOnAction() {
        main.getConnection().setPort(Integer.parseInt(textFieldPort.getText()));
    }

    @FXML
    private void textFieldTimeoutOnAction() {
        main.getConnection().setTimeout(Integer.parseInt(textFieldTimeout.getText()));
    }

}
