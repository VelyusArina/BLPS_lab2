package ru.artemiyandarina.blps_lab2.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.jaas.AuthorityGranter;
import ru.artemiyandarina.blps_lab2.exceptions.NotFoundException;
import ru.artemiyandarina.blps_lab2.models.User;
import ru.artemiyandarina.blps_lab2.repositories.UserRepository;

import java.security.Principal;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
public class JAASAuthorityGranter implements AuthorityGranter {

    private final UserRepository userRepository;

    @Override
    public Set<String> grant(Principal principal) {
        Optional<User> user = userRepository.findByEmail(principal.getName());
        if (user.isPresent()) {
            return Collections.singleton(user.get().getRole().name());
        }
        throw new NotFoundException();
    }
}
