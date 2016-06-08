package sample.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by gantv on 07.06.2016.
 */
public class Connection {
    private StringProperty server;
    private IntegerProperty port;
    private IntegerProperty timeout;

    public Connection(){
        this.server = new SimpleStringProperty("127.0.0.1");
        this.port = new SimpleIntegerProperty(1540);
        this.timeout = new SimpleIntegerProperty(0);
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
}
