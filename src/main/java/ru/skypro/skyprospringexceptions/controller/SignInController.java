package ru.skypro.skyprospringexceptions.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.skyprospringexceptions.exception.WrongLoginException;
import ru.skypro.skyprospringexceptions.exception.WrongPasswordException;

@RestController
@RequestMapping("/login")
public class SignInController {

    @GetMapping()
    public String logIn() {
        return "hello";
    }
    @GetMapping(path = "/signin")
    public static boolean signIn(@RequestParam(name = "login") String login,
                                 @RequestParam(name = "password") String password,
                                 @RequestParam(name = "confirmPassword") String confirmPassword) {
        String loginPattern = "(?=\\w)(?=^[^@#$%^&+=]+$)(?=\\S+$).{8,20}"; // all numbers and letters, including '_', except spaces & special symbols, from 8 to 20 characters
        String passwordPattern = "(?=.*\\w)(?=^[^@#$%^&+=]+$)(?=\\S+$).{6,19}"; // all numbers and letters, including '_', except spaces & special symbols, from 8 to 19 characters
        //localhost:8080/login/signin?login=java_skypro.go&password=D_1hWiKjjP_9&confirmPassword=confirmPassword

        try {
            if (!login.matches(loginPattern)) {
                throw new WrongLoginException("Incorrect login or login length!! Try again...");
            } else if (!password.matches(passwordPattern) || !(password.equals(confirmPassword))) {
                throw new WrongPasswordException("Incorrect password length or incorrect password!! Try to enter again");
            } else {
                System.out.println("Authorization completed successfully");
                return true;
            }
        } catch (WrongLoginException e) {
            System.out.println(e.getMessage());
        } catch (WrongPasswordException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
