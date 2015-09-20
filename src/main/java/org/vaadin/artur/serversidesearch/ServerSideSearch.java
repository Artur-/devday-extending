package org.vaadin.artur.serversidesearch;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class ServerSideSearch extends VerticalLayout implements View {

	private ServerSideSearchField searchField = new ServerSideSearchField();
	private VerticalLayout searchResults = new VerticalLayout();

	public ServerSideSearch() {
		addComponents(searchField, searchResults);

		searchField.addSearchListener(e -> {
			searchResults.addComponent(new Label("Searched for '" + e.getText() + "'"));
		});
	}

	@Override
	public void enter(ViewChangeEvent event) {
	}
}
