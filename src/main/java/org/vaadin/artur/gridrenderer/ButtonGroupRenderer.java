package org.vaadin.artur.gridrenderer;

import java.util.HashMap;
import java.util.Map;

import org.vaadin.artur.client.gridrenderer.ButtonGroupRendererServerRpc;
import org.vaadin.artur.client.gridrenderer.ButtonGroupRendererState;
import org.vaadin.artur.gridrenderer.ButtonGroupRenderer.ClickListener.ClickEvent;

import com.vaadin.shared.MouseEventDetails;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.AbstractRenderer;
import com.vaadin.ui.Grid.Column;
import com.vaadin.ui.renderers.ClickableRenderer.RendererClickEvent;

public class ButtonGroupRenderer extends AbstractRenderer<String> {

	private Map<String, ClickListener> listeners = new HashMap<>();

	public static interface ClickListener {
		void click(ClickEvent event);

		public static class ClickEvent extends Component.Event {
			private String buttonText;
			private Object itemId;
			private Column column;
			private MouseEventDetails mouseDetails;

			public ClickEvent(Grid grid, String buttonText, Object itemId, Column column,
					MouseEventDetails mouseDetails) {
				super(grid);
				this.buttonText = buttonText;
				this.itemId = itemId;
				this.column = column;
				this.mouseDetails = mouseDetails;
			}

			public String getButtonText() {
				return buttonText;
			}

			public Object getItemId() {
				return itemId;
			}

			public Column getColumn() {
				return column;
			}

			public MouseEventDetails getMouseDetails() {
				return mouseDetails;
			}

			@Override
			public Grid getComponent() {
				return (Grid) super.getComponent();
			}
		}
	}

	protected ButtonGroupRenderer() {
		super(String.class);
		registerRpc(new ButtonGroupRendererServerRpc() {
			@Override
			public void click(String buttonText, String rowKey, String columnId, MouseEventDetails mouseDetails) {

				ClickListener listener = listeners.get(buttonText);
				if (listener != null) {
					listener.click(new ClickEvent(getParentGrid(), buttonText, getItemId(rowKey), getColumn(columnId),
							mouseDetails));
				}

			}
		});
	}

	@Override
	protected ButtonGroupRendererState getState() {
		return (ButtonGroupRendererState) super.getState();
	}

	public void addButton(String text, ClickListener listener) {
		addButton(text, null, listener);
	}

	public void addButton(String text, String style, ClickListener listener) {
		listeners.put(text, listener);
		getState().buttons.add(text);
		getState().styles.add(style);
	}


}
