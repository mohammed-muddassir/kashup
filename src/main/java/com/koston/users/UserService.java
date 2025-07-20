package com.koston.users;

public interface UserService
{
    void createUser(UserRequestDTO userRequestDTO) throws Exception;

    void updateUserDetails(Long id, UserRequestDTO userRequestDTO) throws Exception;
}
