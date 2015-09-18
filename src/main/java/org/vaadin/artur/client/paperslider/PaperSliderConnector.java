package org.vaadin.artur.client.paperslider;

import com.vaadin.polymer.paper.widget.PaperSlider;
import com.vaadin.shared.ui.Connect;

@Connect(org.vaadin.artur.paperslider.PaperSlider.class)
public class PaperSliderConnector extends PolymerAbstractFieldConnector {

	@Override
	public PaperSlider getWidget() {
		return (PaperSlider) super.getWidget();
	}

	@Override
	public PaperSliderState getState() {
		return (PaperSliderState) super.getState();
	}

	@Override
	protected void initElementReady() {

	}

	@Override
	protected void onStateChangedPolymerReady() {
		getWidget().setMin(getState().min);
		getWidget().setMax(getState().max);
		getWidget().setValue("" + getState().value);

	}
}
