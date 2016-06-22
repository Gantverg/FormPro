package sample.view;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Created by gantv on 11.06.2016.
 */
public class ControllerPasswordChooser {

    @FXML
    private TextField textFieldLogin;

    @FXML
    private PasswordField passwordFieldPassword;

    @FXML
    private Button buttonConnect;

    @FXML
    private Button buttonCancel;

    private boolean isOK;

    @FXML
    private void initialize() {
        isOK = false;
    }

    @FXML
    private void buttonConnectOnAction(){
        try
        {
            //presenter.connectToServer();
            isOK = true;
        }
        catch (RuntimeException e)
        {
            System.out.println("Connector error. "+e.getMessage());
//            JOptionPane.showMessageDialog(ConnectionView.this,
//                    e.getMessage(), "Connector error",
//                    JOptionPane.ERROR_MESSAGE);
        }

    }

    @FXML
    private void buttonCancelOnAction(){
        isOK = false;
    }

    public boolean connectionIsOn() { return isOK; }

    public String getLogin(){
        return textFieldLogin.getText();
    }

    public char[] getPassword(){
        return passwordFieldPassword.getText().toCharArray();
    }
}
