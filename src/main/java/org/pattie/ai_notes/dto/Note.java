package org.pattie.ai_notes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;

public record Note(Long id,
                   @NotBlank
                   String text,
                   @NotNull
                   OffsetDateTime createdAt) {

}
