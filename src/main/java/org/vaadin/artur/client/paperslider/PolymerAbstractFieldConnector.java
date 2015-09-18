package org.vaadin.artur.client.paperslider;

import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractFieldConnector;
import com.vaadin.polymer.Polymer;
import com.vaadin.polymer.elemental.Function;

public abstract class PolymerAbstractFieldConnector extends AbstractFieldConnector {

	@Override
	protected void init() {
		super.init();
		// Defer init until Polymer ensures the element is upgraded
		// and we can safely change its properties
		Polymer.ready(getWidget().getElement(), new Function() {
			@Override
			public Object call(Object arg) {
				initElementReady();
				return null;
			}
		});
	}

	protected abstract void initElementReady();

	@Override
	public void onStateChanged(StateChangeEvent stateChangeEvent) {
		super.onStateChanged(stateChangeEvent);
		// Defer state handling until Polymer ensures the element is upgraded
		// and we can safely change its properties
		Polymer.ready(getWidget().getElement(), new Function() {
			@Override
			public Object call(Object arg) {
				onStateChangedPolymerReady();
				return null;
			}
		});
	}

	protected abstract void onStateChangedPolymerReady();

}
