package com.homework.home.pattern.mediator;

import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Form extends VerticalLayout {

    @Autowired
    private Mediator mediator;

    private Button button = new Button("save");

    private TextField textField = new TextField();


    public Form() {
        addComponents(button, textField);
        button.addClickListener(e -> save());
    }


    private void save() {
        mediator.saveButtonClick(textField.getValue());
    }


    public void clean() {
        textField.clear();
    }

    public void setValueToTextField(String value) {
        textField.setValue(value);
    }
}

