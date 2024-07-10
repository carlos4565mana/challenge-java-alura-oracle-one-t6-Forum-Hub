package br.com.challenge_alura_one_t6.AluraForum.service;

import br.com.challenge_alura_one_t6.AluraForum.entities.User;
import br.com.challenge_alura_one_t6.AluraForum.exception.ObjectNotFoundException;
import br.com.challenge_alura_one_t6.AluraForum.repositorie.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void delete(Long userId) {
        this.userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("user", userId));
        this.userRepository.deleteById(userId);
    }

    public User findById(Long userId) {
        return this.userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("user", userId));
    }
}
