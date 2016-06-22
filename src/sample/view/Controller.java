package sample.view;

import com._1c.v8.ibis.admin.IClusterInfo;
import com._1c.v8.ibis.admin.IInfoBaseInfo;
import com._1c.v8.ibis.admin.IInfoBaseInfoShort;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.AgentAdminOperation;
import sample.Main;
import sample.models.Cluster;
import sample.models.Connection;
import sample.models.Infobase;

import sample.AgentAdminUtil;
import sample.operations.InfoBaseAuthenticatedOperation;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Controller {

    // Link to main app
    private Main main;

    @FXML
    private TableView<Cluster> clusterTableView;

    @FXML
    private TableView<Infobase> infobaseTableView;

    @FXML
    private Button buttonConnect;

    @FXML
    private Button buttonUpdateClusterList;

    @FXML
    private TableColumn<Cluster, String> clusterNameColumn;

    @FXML
    private TableColumn<Cluster, String> clusterHostColumn;

    @FXML
    private TableColumn<Infobase, String> infobaseNameColumn;

    @FXML
    private TextField textFieldServer;

    @FXML
    private TextField textFieldPort;

    @FXML
    private TextField textFieldTimeout;

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
        main.getConnection().connect();
    }

    public void setMain(Main main) {
        this.main = main;
        textFieldServer.setText(main.getConnection().getServer());
        textFieldPort.setText(Integer.toString(main.getConnection().getPort()));
        textFieldTimeout.setText(Integer.toString(main.getConnection().getTimeout()));
    }

    @FXML
    private void buttonConnectOnAction(){
        System.out.println("Connect to "+main.getConnection().getServer()+":"+main.getConnection().getPort());
        connectToCluster();
    }

    @FXML
    private void buttonUpdateClusterListOnAction(){
        //System.out.println("buttonUpdateClusterListOnAction is running...");
        clusterTableView.setItems(main.getConnection().getClusters());
    }

    public List<Infobase> getInfoBaseShortInfos()
    {
        /*
        final UUID clusterId = clusterTableView.getSelectionModel().selectedItemProperty().get().getiClusterInfo().getClusterId();
        if (clusterId != null)
        {
            return
                    new ClusterAuthenticatedOperation<List<IInfoBaseInfoShort>>(adminUtil, clusterId,
                            new AgentAdminOperation<List<IInfoBaseInfoShort>>()
                            {
                                @Override
                                public List<IInfoBaseInfoShort> call()
                                {
                                    return adminUtil.getInfoBaseShortInfos(clusterId);
                                }
                            }).call();
        }
*/
        return Collections.emptyList();

    }

    @FXML
    private void buttonUpdateInfobaseListOnAction(){

        //infobaseTableView.setItems(getInfoBaseShortInfos());
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
