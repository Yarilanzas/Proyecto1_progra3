package org.logic;

import org.data.Data;
import org.data.XMLRepository;
import org.domain.Category;

import java.util.List;

public class ResourceService {

    public List<Category> findAllCategories() throws Exception{
        Data data = XMLRepository.instance().load();
        return data.getCategories();
    }

}
