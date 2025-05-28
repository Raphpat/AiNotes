package org.pattie.ai_notes.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.pattie.ai_notes.dto.Note;
import org.pattie.ai_notes.service.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notes")
@CrossOrigin
@RequiredArgsConstructor
public class NoteController {
  Logger log = LoggerFactory.getLogger(NoteController.class);

  private final NoteService noteService;

  @GetMapping("")
  public List<Note> getAll() {
    return noteService.getAllNotes();
  }

  @GetMapping("/summary")
  public String getSummary() {
    return "Not implemented yet";
  }

  @GetMapping("/{id}")
  public Note get(@PathVariable Long id) {
    return noteService.getNote(id);
  }

  @PostMapping("")
  @ResponseStatus(HttpStatus.CREATED)
  public void create(@Valid @RequestBody Note note) {
    noteService.createNote(note);
  }

  @PutMapping("/{id}")
  public Note update(@RequestBody Note note, @PathVariable Long id) {
    return noteService.updateNote(note, id);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {
    noteService.deleteNote(id);
  }
}
