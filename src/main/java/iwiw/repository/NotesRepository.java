package iwiw.repository;

import iwiw.model.Note;

import java.util.Date;
import java.util.List;

public interface NotesRepository {

    public Note findById(Integer id);
    public List<Note> listByUserId(Integer userId);
    public List<Note> listBetweenDates(Date from, Date to);

}
