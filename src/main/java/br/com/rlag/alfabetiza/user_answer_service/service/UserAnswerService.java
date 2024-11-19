package br.com.rlag.alfabetiza.user_answer_service.service;

import br.com.rlag.alfabetiza.user_answer_service.model.Practice;
import br.com.rlag.alfabetiza.user_answer_service.model.UserAnswer;
import br.com.rlag.alfabetiza.user_answer_service.repository.UserAnswerRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class UserAnswerService {

    private final UserAnswerRepository userAnswerRepository;
    private final RestTemplate restTemplate;

    public UserAnswerService(UserAnswerRepository userAnswerRepository, RestTemplate restTemplate) {
        this.userAnswerRepository = userAnswerRepository;
        this.restTemplate = restTemplate;
    }

    // Salvar uma nova resposta
    public UserAnswer saveUserAnswer(UserAnswer userAnswer) {
        return userAnswerRepository.save(userAnswer);
    }

    // Recuperar respostas de um usuário
    public List<UserAnswer> getAnswersByUserId(String userId) {
        return userAnswerRepository.findByUserId(userId);
    }

    // Recuperar resposta de um usuário para uma prática específica
    public Optional<UserAnswer> getAnswerByUserIdAndPracticeId(String userId, String practiceId) {
        return userAnswerRepository.findByUserIdAndPracticeId(userId, practiceId);
    }

    // Recuperar respostas associadas a uma prática
    public List<UserAnswer> getAnswersByPracticeId(String practiceId) {
        return userAnswerRepository.findByPracticeId(practiceId);
    }


    // Função para validar a resposta
    public boolean validateAnswer(String practiceId, String answerProvided) {
        String url = "http://contentservice/api/v1/practices/" + practiceId;

        // Buscar detalhes da prática no ContentService
        Practice practice;
        try {
            practice = restTemplate.getForObject(url, Practice.class);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar prática no ContentService: " + e.getMessage(), e);
        }

        if (practice == null) {
            throw new RuntimeException("Prática não encontrada no ContentService.");
        }

        // Verificar se a resposta é correta
        return practice.getCorrectAnswer().equalsIgnoreCase(answerProvided);
    }

}
