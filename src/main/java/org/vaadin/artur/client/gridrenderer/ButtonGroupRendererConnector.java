package org.vaadin.artur.client.gridrenderer;

import org.vaadin.artur.gridrenderer.ButtonGroupRenderer;

import com.google.gwt.dom.client.NativeEvent;
import com.vaadin.client.MouseEventDetailsBuilder;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.connectors.AbstractRendererConnector;
import com.vaadin.client.widget.grid.CellReference;
import com.vaadin.shared.ui.Connect;

import elemental.json.JsonObject;

@Connect(ButtonGroupRenderer.class)
public class ButtonGroupRendererConnector extends AbstractRendererConnector<String> {

	@Override
	protected void init() {
		super.init();
		getRenderer().setConnector(this);
	}

	@Override
	public void onStateChanged(StateChangeEvent stateChangeEvent) {
		super.onStateChanged(stateChangeEvent);

		getRenderer().setState(getState());
	}

	@Override
	public ButtonGroupRendererState getState() {
		return (ButtonGroupRendererState) super.getState();
	}

	@Override
	public ButtonGroupRendererClient getRenderer() {
		return (ButtonGroupRendererClient) super.getRenderer();
	}

	public void onButtonClick(String buttonText, CellReference cll, NativeEvent event) {
		getRpcProxy(ButtonGroupRendererServerRpc.class).click(buttonText, getRowKey((JsonObject) cll.getRow()),
				getColumnId(cll.getColumn()), MouseEventDetailsBuilder.buildMouseEventDetails(event));
	}

}
