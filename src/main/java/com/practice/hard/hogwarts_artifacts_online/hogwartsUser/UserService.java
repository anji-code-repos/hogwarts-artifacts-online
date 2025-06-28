package com.practice.hard.hogwarts_artifacts_online.hogwartsUser;

import com.practice.hard.hogwarts_artifacts_online.system.exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
@Transactional
public class UserService implements Serializable {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public HogwartsUser getUserById(Integer userId){
        return this.userRepository.findById(userId)
                .orElseThrow(()->new ObjectNotFoundException("user", userId));
    }

    public List<HogwartsUser> getAllUsers(){
        return this.userRepository.findAll();
    }

    public HogwartsUser saveUser(HogwartsUser hogwartsUser){
        return this.userRepository.save(hogwartsUser);
    }

    public HogwartsUser updateUser(Integer userId, HogwartsUser hogwartsUser){
        return this.userRepository.findById(userId)
            .map(oldUser-> {
                oldUser.setName(hogwartsUser.getName());
                oldUser.setPassword(hogwartsUser.getPassword());
                oldUser.setRoles(hogwartsUser.getRoles());
                oldUser.setEnabled(hogwartsUser.isEnabled());
                return this.userRepository.save(oldUser);
            }).orElseThrow(()-> new ObjectNotFoundException("user", userId));
    }

    public void deleteUser(Integer userId){
        this.userRepository.findById(userId)
                .orElseThrow(()-> new ObjectNotFoundException("user", userId));
        this.userRepository.deleteById(userId);
    }
}
