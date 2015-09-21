package org.vaadin.artur.localstorage;

import com.vaadin.shared.communication.ClientRpc;

public interface LocalStorageClientRpc extends ClientRpc {

	public void set(String key, String value);

	public void get(String key);
}
