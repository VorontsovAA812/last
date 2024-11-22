package com.example.demo.rest.controllers;

import com.example.demo.domain.User;
import com.example.demo.rest.dto.UserDtos.NewUserRequest;
import com.example.demo.rest.dto.UserDtos.UserDto;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.sql.RowSet;

@Controller
@RequestMapping("/register")
public class RegistrationController  {

    @Autowired
    private UserService userService;
    @GetMapping
    public String showRegisterPage(Model model) {
        model.addAttribute("text", "Добро пожаловать на страницу регистрации!"); // Добавляем данные в модель

        return "register"; // Имя файла шаблона: register.html
    }
    @PostMapping
    public String registerUser(@RequestParam String username,
                               @RequestParam String password,
                               Model model) {
        // Проверяем, существует ли пользователь
        User existingUser = userService.findByUsername(username);
        if (existingUser != null) {
            model.addAttribute("error", "Пользователь с таким именем уже существует!");
            return "register"; // Возвращаем страницу регистрации с сообщением об ошибке
        }

        // Создаём нового пользователя
        NewUserRequest request = new NewUserRequest();
        request.setUsername(username);
        request.setPassword(password);
        userService.addNewUser(request);

        model.addAttribute("success", "Пользователь успешно зарегистрирован!");
        return "login"; // Перенаправляем на страницу входа
    }


}
