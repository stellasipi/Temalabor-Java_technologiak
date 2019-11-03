package iwiw.repository;

import iwiw.model.SentMessage;

import java.util.List;

public interface SentMessageRepository {

    public SentMessage findById(Integer id);
    public List<Integer> listByFromId(Integer fromId);
    public List<Integer> listByToId(Integer toId);
    public void add(Integer messageId,Integer fromId,Integer toId);

}
