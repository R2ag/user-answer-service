package br.com.rlag.alfabetiza.user_answer_service.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class UserAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String practiceId; // Relacionado à prática no ContentService

    @Column(nullable = false)
    private String answerProvided; // Resposta enviada pelo usuário

    @Column(nullable = false)
    private Boolean isCorrect; // Se a resposta está correta

    @Column(nullable = false)
    private LocalDateTime submissionDate; // Data e hora da submissão

}
