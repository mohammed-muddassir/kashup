package com.koston.users;

import com.koston.KostonException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService
{
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @Override
    public void createUser(UserRequestDTO userRequestDTO) throws Exception
    {
       validateUserEmail(userRequestDTO);

       User user = new User(userRequestDTO.firstName(), userRequestDTO.lastName(), userRequestDTO.email(), userRequestDTO.password());
       getUserRepository().save(user);
    }

    private void validateUserEmail(UserRequestDTO userRequestDTO) throws Exception
    {
        String email = userRequestDTO.email();

        boolean isEmailAlreadyExists = getUserRepository().existsByEmail(email);
        if(isEmailAlreadyExists)
        {
            throw new KostonException("This email is already registered. Kindly use another email");
        }
    }

    public UserRepository getUserRepository()
    {
        return userRepository;
    }
}
