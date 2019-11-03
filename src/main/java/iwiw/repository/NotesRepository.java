package iwiw.repository;

import iwiw.model.Note;

import java.util.Date;
import java.util.List;

public interface NotesRepository {

    public Note findById(Integer id);
    public List<Note> listByUserId(Integer userId);
    public List<Note> listBetweenDates(Date from, Date to);
    public Note update(Note note);
    public void delete(Note note);
    public void deleteById(Integer id);


}
