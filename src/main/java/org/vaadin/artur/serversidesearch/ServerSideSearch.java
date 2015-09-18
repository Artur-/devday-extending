package org.vaadin.artur.serversidesearch;

import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class ServerSideSearch extends VerticalLayout {

	private ServerSideSearchField searchField = new ServerSideSearchField();
	private VerticalLayout searchResults = new VerticalLayout();

	public ServerSideSearch() {
		addComponents(searchField, searchResults);

		searchField.addSearchListener(e -> {
			searchResults.addComponent(new Label("Searched for '" + e.getText() + "'"));
		});
	}
}
