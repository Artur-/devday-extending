package org.vaadin.artur.paperslider;

import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class PaperSliderView extends VerticalLayout {

	public PaperSliderView() {
		PaperSlider ps = new PaperSlider();
		addComponent(ps);
	};
}
