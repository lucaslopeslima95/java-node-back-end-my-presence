package com.minhapresenca.minhapresencabackend.servicesImplementations;


import com.minhapresenca.minhapresencabackend.entity.User;
import com.minhapresenca.minhapresencabackend.repository.UserRepository;
import com.minhapresenca.minhapresencabackend.service.UserService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
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
        return userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User update(Long id,String email,String password) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
            user.setEmail(email);
            return userRepository.saveAndFlush(user);
        }
        return optionalUser.orElse(null);
    }
    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    public User buildUser(String email,String password){
        User user = User
                .builder()
                .password(BCrypt.hashpw(password, BCrypt.gensalt()))
                .email(email)
                .build();
        User userSaved = this.save(user);
        return userSaved;
    }

    public User findById(Long id){
        return userRepository.findById(id).get();
    }
}
