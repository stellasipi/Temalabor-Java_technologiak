package iwiw.repository;

import iwiw.model.Tag;

public interface TagRepository {

    public Tag findById(Integer id);
    public Tag findByName (String name);

}
