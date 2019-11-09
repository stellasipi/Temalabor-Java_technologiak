package iwiw.repository;

import iwiw.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface NotesRepository extends JpaRepository<Note,Integer> {

    public List<Note> listByUserId(Integer userId);
    public List<Note> listBetweenDates(Date from, Date to);
    public Note update(Note note);

}
