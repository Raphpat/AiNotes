package org.pattie.ai_notes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AiNotesApplication {
  Logger log = LoggerFactory.getLogger(AiNotesApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(AiNotesApplication.class, args);
  }

  //  @Bean
  //  public CommandLineRunner runner(ChatClient.Builder builder) {
  //    return args -> {
  //      ChatClient chatClient = builder.build();
  //      String response = chatClient.prompt("Tell me a joke").call().content();
  //      log.warn(response);
  //    };
  //  }
}
