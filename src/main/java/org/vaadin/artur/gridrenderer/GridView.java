package org.vaadin.artur.gridrenderer;

import com.vaadin.data.Container.Indexed;
import com.vaadin.data.Item;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class GridView extends VerticalLayout {

	public GridView() {

		Grid grid = new Grid();
		grid.setWidth("600px");
		grid.addColumn("Name", String.class).setExpandRatio(1);
		grid.addColumn("Age", Integer.class);
		grid.addColumn("Actions", String.class);

		grid.addRow("Magnus Durante", 12, "");
		grid.addRow("Dubravka Houtkooper", 45, "");
		grid.addRow("Lourens Kedzierski", 31, "");
		grid.addRow("Eugene Russo", 49, "");
		grid.addRow("Haggith Rier", 67, "");

		ButtonGroupRenderer buttonsRenderer = new ButtonGroupRenderer();
		buttonsRenderer.addButton("Edit", this::onClick);
		buttonsRenderer.addButton(FontAwesome.TRASH_O.getHtml() + " Delete " + FontAwesome.TRASH_O.getHtml(),
				"v-button-danger", this::onClick);
		buttonsRenderer.addButton("Mark", "v-button-friendly", this::onClick);

		grid.getColumn("Actions").setRenderer(buttonsRenderer);

		addComponent(grid);
	}

	public void onClick(ButtonGroupRenderer.ClickListener.ClickEvent e) {
		Grid grid = e.getComponent();
		Indexed container = grid.getContainerDataSource();
		Item clickedItem = container.getItem(e.getItemId());
		String clickedName = (String) clickedItem.getItemProperty("Name").getValue();

		Notification.show(e.getButtonText() + " " + clickedName);
		
	}
}
