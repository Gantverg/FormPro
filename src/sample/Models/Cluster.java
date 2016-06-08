package sample.models;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by gantv on 05.06.2016.
 */
public class Cluster {
    private StringProperty name;
    private StringProperty host;
    private ListProperty<Infobase> infobases;

    public Cluster(String name, String host) {
        this.name = new SimpleStringProperty(name);
        this.host = new SimpleStringProperty(host);
        List<Infobase> list = new LinkedList<>();
        ObservableList<Infobase> observableList = FXCollections.observableArrayList(list);
        this.infobases = new SimpleListProperty<Infobase>(observableList);
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
