package org.pattie.ai_notes.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.pattie.ai_notes.NoteMapper;
import org.pattie.ai_notes.db.models.NoteEntity;
import org.pattie.ai_notes.dto.Note;
import org.pattie.ai_notes.repository.NoteRepository;

@ExtendWith(MockitoExtension.class)
class NoteServiceTest {

  @InjectMocks NoteService test;
  @Mock NoteRepository noteRepository;
  @Mock NoteMapper noteMapper;

  private NoteEntity note1;
  private NoteEntity note2;

  private Note note1Dto;
  private Note note2Dto;

  @BeforeEach
  void setUp() {
    OffsetDateTime now1 = OffsetDateTime.now();
    OffsetDateTime now2 = OffsetDateTime.now();
    note1 = new NoteEntity(1L, "Note 1", now1);
    note2 = new NoteEntity(2L, "Note 2", now2);

    note1Dto = new Note(1L, "Note 1", now1);
    note2Dto = new Note(2L, "Note 2", now2);
  }

  @Test
  void getAllNotes() {
    when(noteRepository.findAll()).thenReturn(List.of(note1, note2));
    when(noteMapper.map(note1)).thenReturn(note1Dto);
    when(noteMapper.map(note2)).thenReturn(note2Dto);

    List<Note> notes = test.getAllNotes();

    assertEquals(2, notes.size());
    assertTrue(notes.contains(note1Dto));
    assertTrue(notes.contains(note2Dto));
    verify(noteRepository).findAll();
    verify(noteMapper, times(2)).map(any(NoteEntity.class));
  }

  @Test
  void getNote() {
    when(noteRepository.findById(1L)).thenReturn(Optional.of(note1));
    when(noteMapper.map(note1)).thenReturn(note1Dto);

    Note note = test.getNote(1L);
    assertEquals(note1Dto, note);
    verify(noteRepository).findById(1L);
    verify(noteMapper).map(note1);
  }

  @Test
  void createNote() {
    Note newNote = new Note(null, "New Note", OffsetDateTime.now());
    NoteEntity newNoteEntity = new NoteEntity(3, "New Note", OffsetDateTime.now());

    when(noteMapper.update(any(NoteEntity.class), any(Note.class))).thenReturn(newNoteEntity);
    when(noteRepository.save(newNoteEntity)).thenReturn(newNoteEntity);

    test.createNote(newNote);

    verify(noteMapper).update(any(NoteEntity.class), any(Note.class));
    verify(noteRepository).save(newNoteEntity);
  }

  @Test
  void updateNote() {
    Note updatedNote = new Note(1L, "Updated Note", OffsetDateTime.now());
    NoteEntity updatedNoteEntity = new NoteEntity(1L, "Updated Note", OffsetDateTime.now());

    when(noteRepository.findById(1L)).thenReturn(Optional.of(note1));
    when(noteMapper.update(any(NoteEntity.class), any(Note.class))).thenReturn(updatedNoteEntity);
    when(noteRepository.save(updatedNoteEntity)).thenReturn(updatedNoteEntity);
    when(noteMapper.map(updatedNoteEntity)).thenReturn(updatedNote);

    Note result = test.updateNote(updatedNote, 1L);

    assertEquals(updatedNote, result);
    verify(noteRepository).findById(1L);
    verify(noteMapper).update(any(NoteEntity.class), any(Note.class));
    verify(noteRepository).save(updatedNoteEntity);
  }

  @Test
  void deleteNote() {
    test.deleteNote(1L);
    verify(noteRepository).deleteById(1L);
  }
}
