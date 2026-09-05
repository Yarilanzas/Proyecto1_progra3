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

    public Resource findByDesc(String des)throws Exception{
        Data data = XMLRepository.instance().load();
        for (Resource e : data.getResources()){
            if (e.getDescription().equals(des)){
                return e;
            }
        }
        return null;
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
