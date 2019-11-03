package iwiw.repository;

import iwiw.model.Tag;

public interface TagRepository {

    public Tag findById(Integer id);
    public Tag findByName (String name);
    public Tag update(Tag tag);
    public void delete(Tag tag);
    public void deleteById(Integer id);

}
