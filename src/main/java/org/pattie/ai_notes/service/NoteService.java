package org.pattie.ai_notes.service;

import lombok.RequiredArgsConstructor;
import org.pattie.ai_notes.NoteMapper;
import org.pattie.ai_notes.db.models.NoteEntity;
import org.pattie.ai_notes.db.requests.NoteDAO;
import org.pattie.ai_notes.dto.Note;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoteService {
    Logger log = LoggerFactory.getLogger(NoteService.class);

    private final NoteDAO noteDAO;
    private final NoteMapper noteMapper;

    public List<Note> getAllNotes() {
        return List.of(); // Placeholder for actual implementation
    }

    public Optional<Note> getNote() {
        return Optional.empty(); // Placeholder for actual implementation
    }

    public void createNote(Note note) {
        NoteEntity entity = new NoteEntity();
        entity = noteMapper.update(entity, note);
        log.info("Creating note: {}", entity.toString());
    }

    public Note updateNote(Note note, Long id) {
        NoteEntity entity = new NoteEntity(); // FIXME
        entity.setId(id);
        entity = noteMapper.update(entity, note);
        log.info("Updating note: {}, {}", id, entity.toString());
        return noteMapper.map(entity);
    }

    public void deleteNote(Long id) {
        //do delete
    }
}
