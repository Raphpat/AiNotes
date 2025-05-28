package org.pattie.ai_notes.repository;

import org.pattie.ai_notes.db.models.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<NoteEntity, Long> {}
