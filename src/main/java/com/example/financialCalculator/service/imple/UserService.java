package com.example.financialCalculator.service.imple;

import com.example.financialCalculator.entities.User;
import com.example.financialCalculator.repository.IUserRepository;
import com.example.financialCalculator.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User createUser(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public List<User> findAllUser() {
        return (List<User>) this.userRepository.findAll();
    }

    @Override
    public User findUserById(Long id) {
        return this.userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("no found"));
    }

    @Override
    public User updateUser(User user) {

        Optional<User> optionalUser = userRepository.findById(user.getId());

        if (optionalUser.isPresent()){

            User existUser = optionalUser.get();

            existUser.setId(user.getId());
            existUser.setPhone(user.getPhone());
            existUser.setFullName(user.getFullName());

            this.userRepository.save(existUser);

            return existUser;
        }else{
            throw new RuntimeException("user not found");
        }
    }

    @Override
    public void deleteUserById(Long id) {
        findUserById(id);
        this.userRepository.deleteById(id);

    }


}
