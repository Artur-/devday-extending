package org.vaadin.artur.serversidesearch;

import java.lang.reflect.Method;

import org.vaadin.artur.serversidesearch.ServerSideSearchField.SearchListener.SearchEvent;

import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.util.ReflectTools;

public class ServerSideSearchField extends CustomComponent {
	protected TextField textInput = new TextField();
	protected Button searchButton = new Button("Search");

	public ServerSideSearchField() {
		textInput.setInputPrompt("Enter text to search for");
		textInput.setWidth("15em");
		searchButton.setEnabled(false); // Disabled until text is entered
		HorizontalLayout layout = new HorizontalLayout();
		layout.addComponents(textInput, searchButton);

		setCompositionRoot(layout);

		textInput.addValueChangeListener(e -> {
			searchButton.setEnabled(!textInput.isEmpty());
		});
		// textInput.addTextChangeListener(e -> {
		// searchButton.setEnabled(!e.getText().isEmpty());
		// });

		searchButton.addClickListener(e -> {
			fireEvent(new SearchEvent(this, textInput.getValue()));
		});
	}

	public void addSearchListener(SearchListener searchListener) {
		super.addListener(SearchEvent.class, searchListener, SearchListener.searchedMethod);
	}

	public interface SearchListener {
		public static Method searchedMethod = ReflectTools.findMethod(SearchListener.class, "searched",
				SearchEvent.class);

		public void searched(SearchEvent event);

		public static class SearchEvent extends Component.Event {

			private String text;

			public SearchEvent(Component source, String text) {
				super(source);
				this.text = text;
			}

			public String getText() {
				return text;
			}

		}
	}

}
