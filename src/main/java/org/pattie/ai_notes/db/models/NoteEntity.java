package org.pattie.ai_notes.db.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.OffsetDateTime;
import lombok.Data;

@Data
@Entity
@Table(name = "note")
public class NoteEntity implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String text;
  private OffsetDateTime createdAt;

  public NoteEntity(long id, String text, OffsetDateTime createdAt) {
    this.id = id;
    this.text = text;
    this.createdAt = createdAt;
  }

  public NoteEntity() {}
}
