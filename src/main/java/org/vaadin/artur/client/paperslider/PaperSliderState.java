package org.vaadin.artur.client.paperslider;

import com.vaadin.shared.AbstractFieldState;

public class PaperSliderState extends AbstractFieldState {

	public int max = 100;
	public int min = 0;
	public int value = 0;
	public boolean pin = false;
	public int step = 1;
}
