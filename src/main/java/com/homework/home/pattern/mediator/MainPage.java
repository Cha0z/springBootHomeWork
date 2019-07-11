package com.homework.home.pattern.mediator;

import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


/**
 * тут используется библиотека которая на бек-енде позволяет *построить* фронт
 * пока читал патерны, захотел реализовать тут более удобное управление
 */

@Component
public class MainPage extends VerticalLayout implements Mediator {

    private Grid<String> grid = new Grid<>();
    private Form form = new Form();

    private List<String> items = new ArrayList<>();


    public MainPage() {
        grid.asSingleSelect();
        grid.addItemClickListener(e -> addValueToForm(e.getItem()));
        addComponents(grid, form);
    }

    @Override
    public void saveButtonClick(String value) {
        items.add(value);
        grid.setItems(items);
        grid.getDataProvider().refreshAll();
        form.clean();
    }

    private void addValueToForm(String value) {
        form.setValueToTextField(value);
    }
}
