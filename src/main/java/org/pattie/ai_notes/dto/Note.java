package org.pattie.ai_notes.dto;

import java.time.OffsetDateTime;

public record Note(Long id,
                   String text,
                   OffsetDateTime createdAt) {

}
