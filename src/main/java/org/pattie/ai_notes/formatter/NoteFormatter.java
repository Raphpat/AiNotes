package org.pattie.ai_notes.formatter;

import java.util.List;
import java.util.stream.Collectors;
import org.pattie.ai_notes.db.models.NoteEntity;
import org.springframework.stereotype.Component;

@Component
public class NoteFormatter {
  public String format(List<NoteEntity> notes) {
    return notes.stream().map(NoteEntity::getText).collect(Collectors.joining(",\n"));
  }
}
