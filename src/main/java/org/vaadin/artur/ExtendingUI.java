package org.vaadin.artur;

import javax.servlet.annotation.WebServlet;

import org.vaadin.artur.gridrenderer.GridView;

import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("mytheme")
@Widgetset("org.vaadin.artur.MyAppWidgetset")
@JavaScript("https://cdnjs.cloudflare.com/ajax/libs/webcomponentsjs/0.7.12/webcomponents-lite.min.js")
public class ExtendingUI extends UI {

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = ExtendingUI.class, productionMode = false)
	public static class ExtendingUIServlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);

		// layout.addComponent(new ServerSideSearch());
		// layout.addComponent(new ServerSideSearchOptimized());
		// layout.addComponent(new PaperSliderView());
		layout.addComponent(new GridView());
		setContent(layout);

	}
}
