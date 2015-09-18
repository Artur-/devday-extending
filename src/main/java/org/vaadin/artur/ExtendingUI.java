package org.vaadin.artur;

import javax.servlet.annotation.WebServlet;

import org.vaadin.artur.serversidesearchoptimized.ServerSideSearchOptimized;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("mytheme")
@Widgetset("org.vaadin.artur.MyAppWidgetset")
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
		layout.addComponent(new ServerSideSearchOptimized());
		setContent(layout);

	}
}
