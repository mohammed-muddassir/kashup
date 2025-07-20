package com.koston.users;

import com.koston.ApplicationUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserRestController
{

    private final UserService userService;

    public UserRestController(UserService userService)
    {
        this.userService = userService;
    }

    public UserService getUserService()
    {
        return userService;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserRequestDTO userRequestDTO) throws Exception
    {
        getUserService().createUser(userRequestDTO);
        return ResponseEntity.ok(ApplicationUtil.getResponseMap(HttpStatus.ACCEPTED.value(), "User Created Successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserRequestDTO userRequestDTO) throws Exception
    {
        getUserService().updateUserDetails(id, userRequestDTO);
        return ResponseEntity.ok(ApplicationUtil.getResponseMap(HttpStatus.OK.value(), "User Details Updated Successfully"));
    }

}
