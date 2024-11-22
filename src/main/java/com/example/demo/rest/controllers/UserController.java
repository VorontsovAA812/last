package com.example.demo.rest.controllers;

import com.example.demo.domain.User;
import com.example.demo.rest.dto.UserDtos.NewUserRequest;
import com.example.demo.rest.dto.UserDtos.UserDto;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/usr")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseBody
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return  ResponseEntity.ok(userService.findById(id));
    }
    @ResponseBody
    @PostMapping
    public ResponseEntity<Long> add(@RequestBody NewUserRequest request) //
    // Long - возвращаем индекс добавленного пользователя
    {
        System.out.println("Получен запрос на создание пользователя: " + request);

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addNewUser(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id)
    {
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/current")
    public ResponseEntity<UserDto> getCurrentUser(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        User user = (User) authentication.getPrincipal();
        UserDto userDto = new UserDto(user.getId(), user.getUsername());
        return ResponseEntity.ok(userDto);
    }
}
