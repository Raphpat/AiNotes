package org.pattie.ai_notes.db.models;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "note")
public class NoteEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private OffsetDateTime createdAt;

}
