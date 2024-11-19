package br.com.rlag.alfabetiza.user_answer_service.controller;

import br.com.rlag.alfabetiza.user_answer_service.model.UserAnswer;
import br.com.rlag.alfabetiza.user_answer_service.service.UserAnswerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/answers")
public class UserAnswerController {
    private final UserAnswerService userAnswerService;

    public UserAnswerController(UserAnswerService userAnswerService) {
        this.userAnswerService = userAnswerService;
    }

    // Salvar uma nova resposta
    @PostMapping
    public ResponseEntity<UserAnswer> saveUserAnswer(@RequestBody UserAnswer userAnswer) {
        UserAnswer savedAnswer = userAnswerService.saveUserAnswer(userAnswer);
        return ResponseEntity.ok(savedAnswer);
    }

    // Buscar todas as respostas de um usuário
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserAnswer>> getAnswersByUserId(@PathVariable String userId) {
        List<UserAnswer> answers = userAnswerService.getAnswersByUserId(userId);
        return ResponseEntity.ok(answers);
    }

    // Buscar uma resposta específica de um usuário para uma prática
    @GetMapping("/user/{userId}/practice/{practiceId}")
    public ResponseEntity<UserAnswer> getAnswerByUserIdAndPracticeId(
            @PathVariable String userId,
            @PathVariable String practiceId) {
        return userAnswerService.getAnswerByUserIdAndPracticeId(userId, practiceId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Buscar todas as respostas associadas a uma prática
    @GetMapping("/practice/{practiceId}")
    public ResponseEntity<List<UserAnswer>> getAnswersByPracticeId(@PathVariable String practiceId) {
        List<UserAnswer> answers = userAnswerService.getAnswersByPracticeId(practiceId);
        return ResponseEntity.ok(answers);
    }

    // Validar uma resposta fornecida
    @PostMapping("/validate")
    public ResponseEntity<Boolean> validateAnswer(
            @RequestParam String practiceId,
            @RequestParam String answerProvided) {
        boolean isCorrect = userAnswerService.validateAnswer(practiceId, answerProvided);
        return ResponseEntity.ok(isCorrect);
    }
}
