package view;

import java.awt.event.ItemEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

public class RadioCombo<T> {
    private JRadioButton radioBtn;
    private JComboBox<T> combo;
    private String text;

    public RadioCombo(String text, DefaultComboBoxModel<T> model) {
        radioBtn = new JRadioButton(text);
        this.text = text;
        combo = new JComboBox<>(model);
        combo.setEnabled(false);
        radioBtn.addItemListener(evt -> {
            combo.setEnabled(evt.getStateChange() == ItemEvent.SELECTED);
        });
    }

    public JRadioButton getRadioBtn() {
        return radioBtn;
    }

    public String getRadioString() {
        return text;
    }

    public JComboBox<T> getCombo() {
        return combo;
    }
}