package org.vaadin.artur.paperslider;

import org.vaadin.artur.client.paperslider.PaperSliderServerRpc;
import org.vaadin.artur.client.paperslider.PaperSliderState;

import com.vaadin.ui.AbstractField;

public class PaperSlider extends AbstractField<Integer> {

	public PaperSlider() {
		registerRpc(new PaperSliderServerRpc() {
			@Override
			public void valueChanged(int value) {
				setValue(value);
			}
		});
	}

	@Override
	public Class<? extends Integer> getType() {
		return Integer.class;
	}

	@Override
	protected PaperSliderState getState() {
		return (PaperSliderState) super.getState();
	}

	public void setMax(int max) {
		getState().max = max;
	}

	public void setMin(int min) {
		getState().min = min;
	}

	@Override
	protected void setInternalValue(Integer newValue) {
		super.setInternalValue(newValue);
		getState().value = newValue;
	}

	public void setPin(boolean pin) {
		getState().pin = pin;
	}

	public void setStep(int step) {
		getState().step = step;
	}
}
