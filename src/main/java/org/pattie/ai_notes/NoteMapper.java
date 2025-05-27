package org.pattie.ai_notes;

import org.pattie.ai_notes.db.models.NoteEntity;
import org.pattie.ai_notes.dto.Note;
import org.springframework.stereotype.Component;

@Component
public class NoteMapper {

    public Note map(NoteEntity entity) {
        return new Note(entity.getId(), entity.getText(), entity.getCreatedAt());
    }

    public NoteEntity update(NoteEntity entity, Note dto) {
        entity.setText(dto.text());
        entity.setCreatedAt(dto.createdAt());
        return entity;
    }
}
