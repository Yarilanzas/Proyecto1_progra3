package org.logic;

import org.data.Data;
import org.data.XMLRepository;
import org.domain.Category;
import org.logic.loginLogic.LoginService;

public class CayegoryService {
    private static CayegoryService theInstance;

    public static CayegoryService instance() {
        if (theInstance == null) theInstance = new CayegoryService();
        return theInstance;
    }

    private Data data;

    private CayegoryService() {
        try {
            data = XMLRepository.instance().load();
        } catch (Exception e) {
            System.err.println("Error al cargar la base de datos: " + e.getMessage());
            data = new Data();
        }

    }

   /* public void saveCategory(Category category){
        boolean existe = data.getCategories().stream()
                .anyMatch(c -> c.getId().equalsIgnoreCase(category.getId()));

        if (existe) {
            //throw new Exception("Ya existe una categoría con ese ID.");
           // throw new Exception("Ya existe una categoría con ese ID.")
        }


        data.getCategories().add(category);
        XMLRepository.instance().store(data);
        XMLRepository.instance().store(data);
    }*/


}
