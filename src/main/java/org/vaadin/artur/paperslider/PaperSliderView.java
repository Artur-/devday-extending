package org.vaadin.artur.paperslider;

import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class PaperSliderView extends VerticalLayout {

	public PaperSliderView() {
		PaperSlider ps = new PaperSlider();
		ps.setMin(20);
		ps.setMax(80);
//		ps.setPin(true);
		ps.setStep(5);
		ps.addValueChangeListener(e -> {
			addComponent(new Label("New value: " + ps.getValue()));
		});
		addComponent(ps);
	};
}
