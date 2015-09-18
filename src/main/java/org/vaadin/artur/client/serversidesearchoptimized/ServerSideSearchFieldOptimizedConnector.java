package org.vaadin.artur.client.serversidesearchoptimized;

import org.vaadin.artur.serversidesearchoptimized.ServerSideSearchFieldOptimized;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.VTextField;
import com.vaadin.client.ui.button.ButtonConnector;
import com.vaadin.client.ui.customcomponent.CustomComponentConnector;
import com.vaadin.client.ui.textfield.TextFieldConnector;
import com.vaadin.shared.ui.Connect;

@Connect(ServerSideSearchFieldOptimized.class)
public class ServerSideSearchFieldOptimizedConnector extends CustomComponentConnector {

	private TextFieldConnector textFieldConnector;
	private ButtonConnector searchButtonConnector;

	@Override
	public void onStateChanged(StateChangeEvent stateChangeEvent) {
		super.onStateChanged(stateChangeEvent);

		searchButtonConnector = (ButtonConnector) getState().searchButton;
		textFieldConnector = (TextFieldConnector) getState().textField;

		textFieldConnector.getWidget().addKeyUpHandler(new KeyUpHandler() {
			@Override
			public void onKeyUp(KeyUpEvent event) {
				updateButtonEnabledState();
			}
		});

		Scheduler.get().scheduleFinally(new ScheduledCommand() {
			@Override
			public void execute() {
				// Finally to be sure that we update after button updates its
				// own enabled state
				updateButtonEnabledState();
			}
		});
	}

	private void updateButtonEnabledState() {
		VTextField textField = textFieldConnector.getWidget();
		boolean emptyTextField = (isPrompting(textField) || textField.getValue().isEmpty());
		searchButtonConnector.setWidgetEnabled(!emptyTextField);
	}

	private boolean isPrompting(VTextField textField) {
		return textField.getElement().hasClassName("v-textfield-prompt");
	}

	@Override
	public ServerSideSearchFieldOptimizedState getState() {
		return (ServerSideSearchFieldOptimizedState) super.getState();
	}

}
