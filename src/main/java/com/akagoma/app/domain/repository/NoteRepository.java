package com.akagoma.app.domain.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.akagoma.app.domain.Dynamo;
import com.akagoma.app.domain.model.Note;

@Repository
public class NoteRepository {
    public void update(Note note) {
        Dynamo.update(note, Note.class);
    }

    public List<Note> findAll() {
        return Dynamo.findAll(Note.class);
    }
}
