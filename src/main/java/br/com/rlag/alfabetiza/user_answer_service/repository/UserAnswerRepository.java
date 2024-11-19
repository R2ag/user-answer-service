package br.com.rlag.alfabetiza.user_answer_service.repository;

import br.com.rlag.alfabetiza.user_answer_service.model.UserAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserAnswerRepository  extends JpaRepository<UserAnswer, Long> {
    // Buscar respostas por ID do usuário
    List<UserAnswer> findByUserId(String userId);

    // Buscar resposta específica por usuário e prática
    Optional<UserAnswer> findByUserIdAndPracticeId(String userId, String practiceId);

    // Buscar respostas por prática
    List<UserAnswer> findByPracticeId(String practiceId);
}
