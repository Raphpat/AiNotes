package org.pattie.ai_notes.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.pattie.ai_notes.NoteMapper;
import org.pattie.ai_notes.db.models.NoteEntity;
import org.pattie.ai_notes.dto.Note;
import org.pattie.ai_notes.repository.NoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class NoteService {
    Logger log = LoggerFactory.getLogger(NoteService.class);

    private final NoteRepository noteRepository;
    private final NoteMapper noteMapper;

    public List<Note> getAllNotes() {
        return noteRepository.findAll().stream().map(noteMapper::map).collect(Collectors.toList());
    }

    public Note getNote(Long id) {
        return noteRepository.findById(id).map(noteMapper::map).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Note not found with id: %s", id)));
    }

    public void createNote(Note note) {
        NoteEntity entity = new NoteEntity();
        entity = noteRepository.save(noteMapper.update(entity, note));
        log.info("Created note: {}", entity);
    }

    public Note updateNote(Note note, Long id) {
        NoteEntity entity = noteRepository.findById(id).map(noteEntity -> noteMapper.update(noteEntity, note)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Note not found with id: %s", id)));
        log.info("Updating note: {}", entity);
        noteRepository.save(entity);
        return noteMapper.map(entity);
    }

    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }
}
