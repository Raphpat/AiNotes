package org.pattie.ai_notes.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.pattie.ai_notes.NoteMapper;
import org.pattie.ai_notes.caller.AiCaller;
import org.pattie.ai_notes.db.models.NoteEntity;
import org.pattie.ai_notes.dto.Note;
import org.pattie.ai_notes.factory.SummaryPromptFactory;
import org.pattie.ai_notes.formatter.NoteFormatter;
import org.pattie.ai_notes.repository.NoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class NoteService {
  Logger log = LoggerFactory.getLogger(NoteService.class);

  private final AiCaller aiCaller;
  private final NoteFormatter noteFormatter;
  private final NoteMapper noteMapper;
  private final NoteRepository noteRepository;
  private final SummaryPromptFactory summaryPromptFactory;

  public List<Note> getAllNotes() {
    return noteRepository.findAll().stream().map(noteMapper::map).collect(Collectors.toList());
  }

  public Note getNote(Long id) {
    return noteRepository
        .findById(id)
        .map(noteMapper::map)
        .orElseThrow(
            () ->
                new ResponseStatusException(
                    HttpStatus.NOT_FOUND, String.format("Note not found with id: %s", id)));
  }

  public void createNote(Note note) {
    NoteEntity entity = new NoteEntity();
    entity = noteRepository.save(noteMapper.update(entity, note));
    log.info("Created note: {}", entity);
  }

  public Note updateNote(Note note, Long id) {
    NoteEntity entity =
        noteRepository
            .findById(id)
            .map(noteEntity -> noteMapper.update(noteEntity, note))
            .orElseThrow(
                () ->
                    new ResponseStatusException(
                        HttpStatus.NOT_FOUND, String.format("Note not found with id: %s", id)));
    log.info("Updating note: {}", entity);
    noteRepository.save(entity);
    return noteMapper.map(entity);
  }

  public void deleteNote(Long id) {
    noteRepository.deleteById(id);
  }

  public String getSummary() {
    List<NoteEntity> notes = noteRepository.findAll();
    String notesContent = noteFormatter.format(notes);
    Prompt prompt = summaryPromptFactory.createPrompt(notesContent);
    String response = aiCaller.execute(prompt);
    log.info("Summary response: {}", response);
    return response;
  }
}
