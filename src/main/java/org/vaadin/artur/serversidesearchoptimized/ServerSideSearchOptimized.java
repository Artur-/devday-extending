package org.vaadin.artur.serversidesearchoptimized;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class ServerSideSearchOptimized extends VerticalLayout implements View {

	private ServerSideSearchFieldOptimized searchField = new ServerSideSearchFieldOptimized();
	private VerticalLayout searchResults = new VerticalLayout();

	public ServerSideSearchOptimized() {
		addComponents(searchField, searchResults);

		searchField.addSearchListener(e -> {
			searchResults.addComponent(new Label("Searched for '" + e.getText() + "'"));
		});
	}

	@Override
	public void enter(ViewChangeEvent event) {
	}
}
