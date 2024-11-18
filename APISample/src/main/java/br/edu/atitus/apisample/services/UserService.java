package br.edu.atitus.apisample.services;

import br.edu.atitus.apisample.entities.UserEntity;
import br.edu.atitus.apisample.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository repository;

    private static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    private boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public UserEntity save(UserEntity newUser) throws Exception {
        if (newUser == null) {
            throw new IllegalArgumentException("Objeto nulo!");
        }
        if (newUser.getName() == null || newUser.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome inválido!");
        }
        if (newUser.getEmail() == null || newUser.getEmail().trim().isEmpty() || !isValidEmail(newUser.getEmail())) {
            throw new IllegalArgumentException("Email inválido!");
        }
        return repository.save(newUser);
    }
}
