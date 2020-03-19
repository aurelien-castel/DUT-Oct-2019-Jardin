package view;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

/**
 * classe pour créer un Radio Combo
 * @param <T>
 */
public class RadioCombo<T> implements ItemListener {
    private JRadioButton radioBtn;
    private JComboBox<T> combo;
    private String text;

    /**
     * constructeur
     * @param text
     * @param model
     */
    public RadioCombo(String text, DefaultComboBoxModel<T> model) {
        radioBtn = new JRadioButton(text);
        this.text = text;
        combo = new JComboBox<>(model);
        combo.setEnabled(false);
        radioBtn.addItemListener(this);
        /*
        radioBtn.addItemListener(evt -> {
            combo.setEnabled(evt.getStateChange() == ItemEvent.SELECTED);
        });*/
    }
    
    @Override
    public void itemStateChanged(ItemEvent evt){

        Object source = evt.getSource();
        if (source == radioBtn) {
        combo.setEnabled(evt.getStateChange() == ItemEvent.SELECTED);
        }
    }

    /**
     * @return the radioBtn
     */
    public JRadioButton getRadioBtn() {
        return radioBtn;
    }

    /**
     * @return the text
     */
    public String getRadioString() {
        return text;
    }

    /**
     * @return the combo
     */
    public JComboBox<T> getCombo() {
        return combo;
    }
}