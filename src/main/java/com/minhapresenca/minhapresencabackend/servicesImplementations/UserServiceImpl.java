package com.minhapresenca.minhapresencabackend.servicesImplementations;

import com.minhapresenca.minhapresencabackend.entity.User;
import com.minhapresenca.minhapresencabackend.repository.UserRepository;
import com.minhapresenca.minhapresencabackend.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCrypt;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        return userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User update(Long id, User userUp) {
        if (id == null || userUp == null) {
            throw new IllegalArgumentException("ID e User não podem ser nulos");
        }
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setPassword(userUp.getPassword());
            user.setEmail(userUp.getEmail());
            return userRepository.saveAndFlush(user);
        }
        return optionalUser.orElse(null);
    }
    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
