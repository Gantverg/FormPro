package sample.models;

import com._1c.v8.ibis.admin.IClusterInfo;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.AgentAdminUtil;

import java.util.List;

/**
 * Created by gantv on 07.06.2016.
 */
public class Connection {
    private StringProperty server;
    private IntegerProperty port;
    private IntegerProperty timeout;
    private final AgentAdminUtil adminUtil;
    private ObservableList<Cluster> clusters =  FXCollections.observableArrayList();

    public Connection(AgentAdminUtil adminUtil){
        this.adminUtil = adminUtil;
        this.server = new SimpleStringProperty("127.0.0.1");
        this.port = new SimpleIntegerProperty(1545);
        this.timeout = new SimpleIntegerProperty(0);
    }

    public void connect()
    {
        adminUtil.connect(this.getServer(), this.getPort(), this.getTimeout());

        this.clusters.removeAll();
        List<IClusterInfo> iClusterInfoList = getClusterInfoList();
        for (IClusterInfo iClusterInfo: iClusterInfoList) {
            this.clusters.add(new Cluster(iClusterInfo, adminUtil));
        }
        //clusterListPresenter.setComponentsEnabled(true);
    }


    public String getServer() {
        return server.get();
    }

    public StringProperty serverProperty() {
        return server;
    }

    public int getPort() {
        return port.get();
    }

    public IntegerProperty portProperty() {
        return port;
    }

    public int getTimeout() {
        return timeout.get();
    }

    public IntegerProperty timeoutProperty() {
        return timeout;
    }

    public void setServer(String server) {
        this.server.set(server);
    }

    public void setPort(int port) {
        this.port.set(port);
    }

    public void setTimeout(int timeout) {
        this.timeout.set(timeout);
    }

    public List<IClusterInfo> getClusterInfoList()
    {
        return adminUtil.getClusterInfoList();
    }

    public AgentAdminUtil getAgentAdminUtil() { return adminUtil; }

    public ObservableList<Cluster> getClusters(){
        return this.clusters;
    }

}
