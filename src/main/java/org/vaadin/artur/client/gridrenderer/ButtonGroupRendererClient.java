package org.vaadin.artur.client.gridrenderer;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.WidgetUtil;
import com.vaadin.client.renderers.WidgetRenderer;
import com.vaadin.client.ui.VButton;
import com.vaadin.client.widget.escalator.Cell;
import com.vaadin.client.widget.escalator.RowContainer;
import com.vaadin.client.widget.grid.CellReference;
import com.vaadin.client.widget.grid.EventCellReference;
import com.vaadin.client.widget.grid.RendererCellReference;
import com.vaadin.client.widgets.Escalator;
import com.vaadin.client.widgets.Grid;
import com.vaadin.client.widgets.Grid.Section;

public class ButtonGroupRendererClient extends WidgetRenderer<String, FlowPanel> {

	private ButtonGroupRendererState state;
	private ButtonGroupRendererConnector connector;

	public void setConnector(ButtonGroupRendererConnector connector) {
		this.connector = connector;
	}

	public void setState(ButtonGroupRendererState state) {
		this.state = state;
	}

	@Override
	public FlowPanel createWidget() {
		FlowPanel panel = new FlowPanel();
		panel.addStyleName("button-group");

		return panel;
	}

	@Override
	public void render(RendererCellReference cell, String data, FlowPanel panel) {
		if (panel.getWidgetCount() == 0) {
			// Panel has not yet been inited
			for (int i = 0; i < state.buttons.size(); i++) {
				VButton button = new VButton();
				button.addStyleName("v-widget");
				button.addStyleName("v-button-small");

				final String buttonText = state.buttons.get(i);
				button.setHtml(buttonText);
				String style = state.styles.get(i);
				if (style != null)
					button.addStyleName(style);

				button.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						Element e = Element.as(event.getNativeEvent().getEventTarget());
						CellReference cll = findCell(e);
						connector.onButtonClick(buttonText, cll, event.getNativeEvent());
					}
				});
				panel.add(button);
			}

		}

	}

	private static Grid<?> findClosestParentGrid(Element e) {
		Widget w = WidgetUtil.findWidget(e, null);

		while (w != null && !(w instanceof Grid)) {
			w = w.getParent();
		}
		return (Grid<?>) w;
	}

	protected CellReference findCell(Element e) {
		// This should be an util method in Grid
		Grid grid = findClosestParentGrid(e);

		RowContainer container = getEscalator(grid).findRowContainer(e);
		if (container == null) {
			return null;
		}
		Cell cell = container.getCell(e);
		EventCellReference<Grid> cellReference = new EventCellReference<Grid>(grid);
		// FIXME: Section is currently always body. Might be useful for the
		// future to have an actual check.
		cellReference.set(cell, Section.BODY);
		return cellReference;

	}

	private native static Escalator getEscalator(Grid<?> grid)
	/*-{    
	  return grid.@com.vaadin.client.widgets.Grid::escalator;
	}-*/;

}
