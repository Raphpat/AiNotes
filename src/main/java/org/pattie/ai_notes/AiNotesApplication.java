package org.pattie.ai_notes;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AiNotesApplication {

  public static void main(String[] args) {
    SpringApplication.run(AiNotesApplication.class, args);
  }

  public CommandLineRunner runner(ChatClient.Builder builder) {
    return args -> {
      ChatClient chatClient = builder.build();
      String response = chatClient.prompt("Tell me a joke").call().content();
      System.out.println(response);
    };
  }
}
