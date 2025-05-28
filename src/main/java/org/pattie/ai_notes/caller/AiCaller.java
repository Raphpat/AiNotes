package org.pattie.ai_notes.caller;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AiCaller {
  private final ChatClient.Builder builder;

  public String execute(Prompt prompt) {
    return builder.build().prompt(prompt).call().content();
  }
}
