package iwiw.repository;

import iwiw.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Integer> {

    public Tag findByName (String name);
    public Tag update(Tag tag);

}
