package sample.operations;

import java.awt.Component;
import java.io.IOException;
import java.util.UUID;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.AgentAdminOperation;
import sample.AgentAdminUtil;
//import sample.console.ui.view.PasswordChooser;

import com._1c.v8.ibis.admin.AgentAdminAuthenticationException;
import sample.Main;
import sample.view.Controller;
import sample.view.ControllerPasswordChooser;

/**
 * Administrative operation that requires infobase authentication
 * 
 */
public final class InfoBaseAuthenticatedOperation<V>
    implements AgentAdminOperation<V>
{
	private final Component view;
	private final AgentAdminUtil adminUtil;
	private final UUID clusterId;
	private final AgentAdminOperation<V> operation;

	public InfoBaseAuthenticatedOperation(Component view, 
        AgentAdminUtil adminUtil, UUID clusterId,
        AgentAdminOperation<V> operation)
	{
		this.view = view;
		this.adminUtil = adminUtil;
		this.clusterId = clusterId;
		this.operation = operation;
	}

	@Override
	public V call()
	{
		Stage passwordStage = new Stage();
		ControllerPasswordChooser controller;
		while (true)
		{
			try
			{
				return operation.call();
			}
			catch (AgentAdminAuthenticationException e)
			{

				try{
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(Main.class.getResource("view/passwordChooser.fxml"));
					AnchorPane rootLayout = (AnchorPane) loader.load();

					// Отображаем сцену, содержащую корневой макет.
					Scene scene = new Scene(rootLayout);
					passwordStage.setScene(scene);
					// Даём контроллеру доступ к главному приложению.
					controller = loader.getController();
					passwordStage.showAndWait();
					if (controller.connectionIsOn())
						adminUtil.addInfoBaseCredentials(clusterId, controller.getLogin(),
								String.valueOf(controller.getPassword()));
				}
				catch (IOException ioE)
				{
					ioE.printStackTrace();
				}

				/*
				PasswordChooser dialog = new PasswordChooser();
				if (!dialog.showDialog(view, "Infobase administrator"))
				{
					throw e;
				}

				adminUtil.addInfoBaseCredentials(clusterId, dialog.getLogin(),
						String.valueOf(dialog.getPassword()));
				*/
			} 
		}
	}
}
