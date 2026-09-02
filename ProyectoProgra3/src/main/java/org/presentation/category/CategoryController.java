package org.presentation.category;

import org.domain.Category;
import org.logic.CategoryService;

import javax.swing.*;

public class CategoryController {
    private CategoryView view;
    private CategoryModel model;
    private final CategoryService categoryService = new CategoryService();

    public CategoryController(CategoryView view, CategoryModel model) {
        this.view = view;
        this.model = model;

        view.setController(this);
        view.setModel(model);

        this.cargarCategorias();
    }

    public void cargarCategorias() {
        try {
            model.setList(categoryService.findAll());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void saveCategory(Category category) {
        try {
            categoryService.save(category);
            this.cargarCategorias();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void edit(int row) {
        Category cat = model.getList().get(row);
        model.setCurrent(cat);
    }
}