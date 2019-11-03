package iwiw.repository;

import iwiw.model.Marked;

import java.util.List;

public interface MarkedRepository {

    public Marked findById(Integer id);
    public List<Marked> listByTagId(Integer tagId);
    public List<Marked> listByMessageId(Integer messageId);
    public Marked update(Marked marked);
    public void add(Marked marked);
    public void delete(Marked marked);
    public void deleteById(Integer id);

}
