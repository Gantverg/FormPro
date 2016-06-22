package sample.operations;

import java.awt.Component;
import java.io.IOException;
import java.util.UUID;

import com._1c.v8.ibis.admin.client.AgentAdminConnectorFactory;
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
 * Administrative operation that requires cluster authentication
 * 
 */
public final class ClusterAuthenticatedOperation<V>
    implements AgentAdminOperation<V>
{
	//private final Component view;
	private final AgentAdminUtil adminUtil;
	private final UUID clusterId;
	private final AgentAdminOperation<V> callable;

    public ClusterAuthenticatedOperation(//Component view,
        AgentAdminUtil adminUtil, UUID clusterId,
        AgentAdminOperation<V> operation)
	{
		//this.view = view;
		this.adminUtil = adminUtil;
		this.clusterId = clusterId;
		this.callable = operation;
	}

	@Override
	public V call()
	{
		while (true)
		{
			try
			{
				return callable.call();
			}
			catch (AgentAdminAuthenticationException aaaE)
			{
				/*
				PasswordChooser dialog = new PasswordChooser();
				if (!dialog.showDialog(view, "Cluster administrator")) 
				{
					throw e;
				}
				*/
				/*
					adminUtil.authenticateCluster(clusterId, controller.getLogin(),
						String.valueOf(controller.getPassword()));

	`			*/
			}
		}
	}
}
