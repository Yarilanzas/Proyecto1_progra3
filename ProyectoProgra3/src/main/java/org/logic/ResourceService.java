package org.logic;

import org.data.Data;
import org.data.XMLRepository;
import org.domain.Category;
import org.domain.Resource;

import java.util.List;

public class ResourceService {

   public List<Resource> findAllResources() throws Exception{
        Data data = XMLRepository.instance().load();
        return data.getResources();
    }

    public List<Resource> findByDesc(String des) throws Exception {
        Data data = XMLRepository.instance().load();
        return data.getResources().stream()
                .filter(r -> r.getDescription() != null && r.getDescription().toUpperCase().contains(des.toUpperCase()))
                .toList();
    }

    public List<Resource> findByCategory(String catDesc) throws Exception {
        Data data = XMLRepository.instance().load();
        return data.getResources().stream()
                .filter(r -> r.getCategory() != null && r.getCategory().getDescription().equalsIgnoreCase(catDesc))
                .toList();
    }
    public void delete(String id) throws Exception {
        Data data = XMLRepository.instance().load();
        Resource existe = data.getResources().stream().filter
                        (c -> c.getId().equals(id))
                .findFirst().orElse(null);

        if (existe != null) {
            data.getResources().remove(existe);
            XMLRepository.instance().store(data);
        }else{
            throw new Exception("No existe ningun recurso con ese id");
        }
    }
    public  void save(Resource r)throws Exception{
        Data data = XMLRepository.instance().load();
        Resource existe = data.getResources().stream().filter
                        (c -> c.getId().equals(r.getId()))
                .findFirst().orElse(null);

        if (existe != null){
           throw new Exception("Ya existe un recurso con ese ID");
        }else{
            data.getResources().add(r);
            XMLRepository.instance().store(data);
        }

    }

}
