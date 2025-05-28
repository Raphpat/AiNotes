package org.pattie.ai_notes.factory;

import lombok.RequiredArgsConstructor;
import org.pattie.ai_notes.message.system.SystemMessageFactory;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SummaryPromptFactory {
  private final SystemMessageFactory systemMessageFactory;

  public Prompt createPrompt(String notesContent) {
    SystemMessage noteMessage =
        systemMessageFactory.createSystemMessage(
            "You are an AI assistant that summarizes notes. The user will send you a list of notes. "
                + "Group similar notes together. Reorder elements that fit together. "
                + "If there are no notes, respond with 'No notes available.' "
                + "If you don't understand a note, make sure to include it in the summary, but do not try to interpret it. ");
    SystemMessage noIntereranceMessage = systemMessageFactory.createNoIntererencePrompt();
    UserMessage user = new UserMessage(notesContent);
    return new Prompt(noteMessage, noIntereranceMessage, user);
  }
}
