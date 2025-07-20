package com.koston.users;

import com.koston.AppThreadLocals;
import com.koston.KostonException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService
{
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder encoder, PasswordEncoder encoder1)
    {
        this.userRepository = userRepository;
        this.encoder = encoder1;
    }

    @Override
    public void createUser(UserRequestDTO userRequestDTO) throws Exception
    {
       validateUserEmail(userRequestDTO);
       String encodedPassword = getEncoder().encode(userRequestDTO.password());
       User user = new User(userRequestDTO.firstName(), userRequestDTO.lastName(), userRequestDTO.email(), encodedPassword);
       getUserRepository().save(user);
    }

    @Override
    public void updateUserDetails(Long id, UserRequestDTO userRequestDTO) throws Exception
    {
        Long currentUser = AppThreadLocals.getCurrentUserId();
        if(!currentUser.equals(id))
        {
            throw new KostonException("Cannot update another Users data");
        }

        Optional<User> userOptl = getUserRepository().findById(id);
        if(userOptl.isEmpty())
        {
            throw new KostonException("Resource Not Found");
        }

        String email = userRequestDTO.email();

        boolean isEmailAssociatedToOtherUsr = getUserRepository().existsByEmailAndIdNot(email, id);
        if(isEmailAssociatedToOtherUsr)
        {
            throw new KostonException("Given email is associated to another User. Try using another mail.");
        }

        User existingUserDetails = userOptl.get();
        if(userRequestDTO.firstName() != null)
            existingUserDetails.setFirstName(userRequestDTO.firstName());
        if(userRequestDTO.lastName() != null)
            existingUserDetails.setLastName(userRequestDTO.lastName());
        if(userRequestDTO.email() != null)
            existingUserDetails.setEmail(userRequestDTO.email());
        if(userRequestDTO.password() != null)
            existingUserDetails.setPassword(getEncoder().encode(userRequestDTO.password()));

        getUserRepository().save(existingUserDetails);
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

    public PasswordEncoder getEncoder()
    {
        return encoder;
    }
}
