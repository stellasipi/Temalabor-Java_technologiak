package iwiw.repository;

import iwiw.model.Marked;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface MarkedRepository extends JpaRepository<Marked, Integer> {

    public List<Marked> listByTagId(Integer tagId);
    public List<Marked> listByMessageId(Integer messageId);
    public Marked update(Marked marked);

}
