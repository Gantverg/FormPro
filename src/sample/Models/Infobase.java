package sample.models;

import com._1c.v8.ibis.admin.IInfoBaseInfoShort;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by gantv on 05.06.2016.
 */
public class Infobase {
    private IInfoBaseInfoShort iInfoBaseInfoShort;

    public Infobase(IInfoBaseInfoShort iInfoBaseInfoShort) {
        this.iInfoBaseInfoShort = iInfoBaseInfoShort;
    }

    public String getName() { return iInfoBaseInfoShort.getName(); }

    public StringProperty getNameProperty() { return new SimpleStringProperty(iInfoBaseInfoShort.getName()); }

}
