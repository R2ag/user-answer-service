package br.com.rlag.alfabetiza.user_answer_service.model;

import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Practice {
    private String id;
    private String description;
    private String imageUrl;
    private List<String> possibleAnswers; // Respostas possíveis
    private String correctAnswer; // Resposta correta
}
