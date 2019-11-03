package iwiw.repository;

import iwiw.model.Marked;

import java.util.List;

public interface MarkedRepository {

    public Marked findById(Integer id);
    public List<Marked> listByTagId(Integer tagId);

}
