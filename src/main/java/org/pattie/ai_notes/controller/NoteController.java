package org.pattie.ai_notes.controller;

import org.pattie.ai_notes.dto.Note;
import org.pattie.ai_notes.service.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notes")
@CrossOrigin
public class NoteController {
    Logger log = LoggerFactory.getLogger(NoteController.class);

    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("")
    public List<Note> getAllNotes() {
        log.info("Fetching all notes");
        return noteService.getAllNotes();
    }

    @GetMapping("/{id}")
    public Optional<Note> getNote(@PathVariable Long id) {
        log.info("Fetching note with id: {}", id);
        return noteService.getNote();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void createNote(@RequestBody Note note) {
        log.info("Creating note: {}", note.toString());
        noteService.createNote(note);
    }

    @PutMapping("/{id}")
    public Note updateNote(@RequestBody Note note, @PathVariable Long id) {
        log.info("updating note with id: {}, {}", id, note.toString());
        return noteService.updateNote(note, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNote(@PathVariable Long id) {
        log.info("deleting note with id: {}", id);
        noteService.deleteNote(id);
    }
}
