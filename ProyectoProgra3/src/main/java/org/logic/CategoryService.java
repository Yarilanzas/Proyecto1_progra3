package org.logic;

import org.data.Data;
import org.data.XMLRepository;
import org.domain.Category;
import org.domain.Employee;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryService {
    public List<Category> findAll() throws Exception{
        Data data = XMLRepository.instance().load();
        return data.getCategories();
    }

    public Category findById(String id) throws Exception{
        Data data = XMLRepository.instance().load();
       return data.getCategories().stream().filter
               (c -> c.getId().equals(id))
               .findFirst().orElse(null);
    }

    public List<Category> findByDescription(String descripcion) throws Exception{
        Data data = XMLRepository.instance().load();
        return data.getCategories().stream().filter
                (c -> c.getDescription().toLowerCase()
                        .contains(descripcion.toLowerCase()))
                        .collect(Collectors.toList());
    }

    public void save(Category categoria) throws Exception{
        Data data = XMLRepository.instance().load();
        Category existe = data.getCategories().stream().filter
                        (c -> c.getId().equals(categoria.getId()))
                .findFirst().orElse(null);

        if (existe != null){
            data.getCategories().remove(existe);
        }else{
            categoria.setId(generarId(data.getCategories()));
        }

        data.getCategories().add(categoria);
        XMLRepository.instance().store(data);
    }

    public void delete(String id) throws Exception {
        Data data = XMLRepository.instance().load();
        Category existe = data.getCategories().stream().filter
                        (c -> c.getId().equals(id))
                .findFirst().orElse(null);

        if (existe != null) {
            data.getCategories().remove(existe);
            XMLRepository.instance().store(data);
        }
    }

    private String generarId(List<Category> categorias){
        int max = 0;
        for(Category c : categorias){
            String num = c.getId().replace("CAT-", "");
            try {
                int valor = Integer.parseInt(num);
                if (valor > max) {
                    max = valor;
                }
            } catch (NumberFormatException ignored) {}
        }

        int nuevonum = max + 1;
        String numText = String.valueOf(nuevonum);
        while(numText.length() < 6){
            numText = "0" + numText;
        }
        return "CAT-" + numText;
    }

}
