package org.vaadin.artur.client.gridrenderer;

import com.vaadin.shared.MouseEventDetails;
import com.vaadin.shared.communication.ServerRpc;

public interface ButtonGroupRendererServerRpc extends ServerRpc {
	public void click(String buttonText, String rowKey, String colKey, MouseEventDetails mouseEventDetails);
}
