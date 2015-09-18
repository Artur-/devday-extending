package org.vaadin.artur.client.gridrenderer;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.shared.communication.SharedState;

public class ButtonGroupRendererState extends SharedState {

	public List<String> buttons = new ArrayList<>();
	public List<String> styles = new ArrayList<>();

}
