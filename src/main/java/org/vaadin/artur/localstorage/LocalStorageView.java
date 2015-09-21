package org.vaadin.artur.localstorage;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class LocalStorageView extends VerticalLayout implements View {

	private LocalStorageExtension extension;

	public LocalStorageView() {
		setSpacing(true);

		extension = new LocalStorageExtension();
		TextField keySet = new TextField("Key");
		TextField value = new TextField("Value");
		Button set = new Button("Set", e -> {
			extension.set(keySet.getValue(), value.getValue());
		});
		
		HorizontalLayout hl1 = new HorizontalLayout();
		hl1.setDefaultComponentAlignment(Alignment.BOTTOM_RIGHT);
		hl1.addComponents(keySet,value,set);
		
		TextField keyGet = new TextField("Key");
		Button get = new Button("Get", e -> {
			String k = keyGet.getValue();
			extension.get(k, v -> {
				Notification.show("Value for " + k + " is " + v);
			});
		});

		HorizontalLayout hl2 = new HorizontalLayout();
		hl2.setDefaultComponentAlignment(Alignment.BOTTOM_RIGHT);
		hl2.addComponents(keyGet,get);

		addComponents(hl1,hl2);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		extension.extend(getUI());
	}

}
