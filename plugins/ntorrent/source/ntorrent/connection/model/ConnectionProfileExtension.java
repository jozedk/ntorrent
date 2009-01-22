package ntorrent.connection.model;

import java.awt.Component;
import java.io.Serializable;

import ntorrent.connection.socket.view.SocketConnectionView;

public interface ConnectionProfileExtension<T extends ConnectionProfile> extends Serializable {	
	/**
	 * @return
	 */
	public T getConnectionProfile();
	
	/**
	 * This method should return a Component that is to be displayed
	 * in as the input form on the profile connection profile setup.
	 * 
	 * Since this interface is subject to serialization, a good implementation
	 * of this method could be as follows:
	 * 
	 * 	private transient Component display;
	 *  ... 
	 * 	if(display == null){
	 *		display = new ConnectionView(connectionProfile);
	 *	}
	 *	return display;
	 *
	 * @return Component
	 */
	public Component getDisplay();

	/**
	 * Returns a human friendly name of this object.
	 * This method should return by default a name that describes
	 * this connection profile.
	 * @return
	 */
	public String getName();
	
	/**
	 * Sets a human friendly name of this object.
	 * This method will be called upon saving a connection profile
	 * to set a user specified name.
	 */
	public void setName(String name);
	
}