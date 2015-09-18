package org.vaadin.artur.serversidesearchoptimized;

import java.lang.reflect.Method;

import org.vaadin.artur.client.serversidesearchoptimized.ServerSideSearchFieldOptimizedState;
import org.vaadin.artur.serversidesearchoptimized.ServerSideSearchFieldOptimized.SearchListenerOptimized.SearchEventOptimized;

import com.vaadin.shared.AbstractComponentState;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.util.ReflectTools;

public class ServerSideSearchFieldOptimized extends CustomComponent {
	protected TextField textInput = new TextField();
	protected Button searchButton = new Button("Search");

	public ServerSideSearchFieldOptimized() {
		textInput.setInputPrompt("Enter text to search for");
		textInput.setWidth("15em");
		HorizontalLayout layout = new HorizontalLayout();
		layout.addComponents(textInput, searchButton);

		setCompositionRoot(layout);

		searchButton.addClickListener(e -> {
			fireEvent(new SearchEventOptimized(this, textInput.getValue()));
		});

		// Send component references to the client
		getState().textField = textInput;
		getState().searchButton = searchButton;
	}

	@Override
	protected ServerSideSearchFieldOptimizedState getState() {
		return (ServerSideSearchFieldOptimizedState) super.getState();
	}

	public void addSearchListener(SearchListenerOptimized searchListener) {
		super.addListener(SearchEventOptimized.class, searchListener, SearchListenerOptimized.searchedMethod);
	}

	public interface SearchListenerOptimized {
		public static Method searchedMethod = ReflectTools.findMethod(SearchListenerOptimized.class, "searched",
				SearchEventOptimized.class);

		public void searched(SearchEventOptimized event);

		public static class SearchEventOptimized extends Component.Event {

			private String text;

			public SearchEventOptimized(Component source, String text) {
				super(source);
				this.text = text;
			}

			public String getText() {
				return text;
			}

		}
	}

}
