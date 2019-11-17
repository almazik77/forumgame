package services;

import models.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import repositories.UsersRepository;

import java.util.Optional;

public class AccountService {

    private UsersRepository usersRepository;
    PasswordEncoder encoder;


    public AccountService(UsersRepository usersRepository, PasswordEncoder encoder) {
        this.usersRepository = usersRepository;
        this.encoder = encoder;
    }

    public boolean registerUser(String login, String password, String mail) {
        String hashPassword = encoder.encode(password);
        usersRepository.save(User.builder().email(mail).password(hashPassword).email(mail).login(login).build());
        return true;
    }


    public User find(String login) {
        return usersRepository.find(login).get();
    }

    public String find(String login, String password) {
        User user = usersRepository.find(login).get();
        if (user == null) {
            return "Wrong Login";
        }
        if (encoder.matches(password, user.getPassword()))
            return "Success";
        return "Wrong Password";
    }

    public Long findUserId(String login) {
        User user = find(login);
        return user.getId();
    }

    public Optional<User> find(Long id) {
        return usersRepository.find(id);
    }

    public boolean checkIfCanCreateGame(Long id) {
        Optional<User> user = find(id);

        return user.isPresent() && (user.get().getRole().equals("admin") || user.get().getRole().equals("moderator"));
    }
}
