package sky.pro.users.service;

import org.springframework.stereotype.Service;
import sky.pro.users.entity.User;
import sky.pro.users.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public void createUser(User user) {
        repository.save(user);
    }

    public User getUserById(Integer id) {
        return repository.getById(id);
    }

    public void deleteUserById(Integer id) {
        repository.deleteById(id);
    }

    public List<User> getUsersByName(String name) {
        return repository.getUsersByName(name);
    }
}
