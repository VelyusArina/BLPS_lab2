package ru.artemiyandarina.blps_lab2.controllers;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import ru.artemiyandarina.blps_lab2.schemas.user.UserRead;
import ru.artemiyandarina.blps_lab2.schemas.user.UserRegister;
import ru.artemiyandarina.blps_lab2.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;

@Tag(name = "Пользователи")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Validated
public class UserController {
    final UserService userService;
    private final String USER_XML = "users.xml";

    @Operation(summary = "Регистрирует пользователя")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UserRead registerUser(@RequestBody @Valid UserRegister schema) {
        UserRead userRead = userService.register(schema);
        saveUserToXml(userRead);
        return userRead;
    }

    @Operation(summary = "Список пользователей")
    @GetMapping
    public List<UserRead> getUserList() {
        return userService.getAll();
    }

    @Operation(summary = "Возвращает пользователя")
    @GetMapping("/{id}")
    public UserRead getUser(@PathVariable Long id) {
        return userService.getById(id);
    }

    @Operation(summary = "Удаляет пользователя")
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }

    private void saveUserToXml(UserRead userRead) {
        try {
            File file = new File(USER_XML);
            JAXBContext jaxbContext = JAXBContext.newInstance(UserRead.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(userRead, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

