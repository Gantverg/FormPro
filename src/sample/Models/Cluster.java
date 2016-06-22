package sample.models;

import com._1c.v8.ibis.admin.IClusterInfo;
import com._1c.v8.ibis.admin.IInfoBaseInfoShort;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.AgentAdminUtil;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * Created by gantv on 05.06.2016.
 */
public class Cluster {
    private StringProperty name;
    private StringProperty host;
    private IClusterInfo iClusterInfo;
    private ListProperty<Infobase> infobases;

    public Cluster(IClusterInfo iClusterInfo, AgentAdminUtil agentAdminUtil) {
        this.iClusterInfo = iClusterInfo;
        this.name = new SimpleStringProperty(iClusterInfo.getName());
        this.host = new SimpleStringProperty(iClusterInfo.getHostName());
        ObservableList<Infobase> observableList = FXCollections.observableArrayList(new LinkedList<>());
        this.infobases = new SimpleListProperty<Infobase>(observableList);
    }

    public void updateInfobaseList(AgentAdminUtil agentAdminUtil, String login, String password){
        final UUID clusterId = iClusterInfo.getClusterId();
        if (clusterId != null)
        {
            for (IInfoBaseInfoShort iInfoBaseInfoShort:agentAdminUtil.getInfoBaseShortInfos(clusterId)) {
                this.infobases.add(new Infobase(iInfoBaseInfoShort));
            }
        }

    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setHost(String host) {
        this.host.set(host);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty getNameProperty() { return name; }

    public StringProperty getHostProperty() { return host; }

    public String getHost() {
        return host.get();
    }

    public IClusterInfo getiClusterInfo() { return iClusterInfo; }

    public void setiClusterInfo(IClusterInfo iClusterInfo) { this.iClusterInfo = iClusterInfo; }

    public ListProperty<Infobase> getInfobases() {
        return infobases;
    }

    public void addInfobase(Infobase infobase){
        if (!infobases.contains(infobase))
            infobases.add(infobase);
    }

    public void removeInfobase(Infobase infobase){
        if (infobases.contains(infobase))
            infobases.remove(infobase);
    }
}
