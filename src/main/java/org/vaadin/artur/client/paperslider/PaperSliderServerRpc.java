package org.vaadin.artur.client.paperslider;

import com.vaadin.shared.communication.ServerRpc;

public interface PaperSliderServerRpc extends ServerRpc {

	public void valueChanged(int newValue);

}
