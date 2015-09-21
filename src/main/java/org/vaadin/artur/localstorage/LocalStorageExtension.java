package org.vaadin.artur.localstorage;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import com.vaadin.annotations.JavaScript;
import com.vaadin.server.AbstractJavaScriptExtension;
import com.vaadin.ui.UI;

@JavaScript("localstorage.js")
public class LocalStorageExtension extends AbstractJavaScriptExtension {

	private Map<String, Consumer<String>> waitingForValue = new HashMap<>();

	public LocalStorageExtension() {
		registerRpc(new LocalStorageServerRpc() {

			@Override
			public void reportValue(String key, String value) {
				Consumer<String> r = waitingForValue.remove(key);
				if (r != null) {
					r.accept(value);
				} else {
					System.err.println("Unexpected report from the client for key " + key);
				}
			}
		});

	}

	public void extend(UI target) {
		super.extend(target);
	}

	public void set(String key, String value) {
		getRpcProxy(LocalStorageClientRpc.class).set(key, value);
	}

	public void get(String key, Consumer<String> onValueReceived) {
		getRpcProxy(LocalStorageClientRpc.class).get(key);
		waitingForValue.put(key, onValueReceived);
	}

}
