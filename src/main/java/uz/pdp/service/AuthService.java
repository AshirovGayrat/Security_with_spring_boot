package uz.pdp.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.dto.RegisterDTO;
import uz.pdp.entity.AuthUser;
import uz.pdp.enums.Role;
import uz.pdp.exceptions.UserAlreadyExistsException;
import uz.pdp.repository.AuthUserRepository;

import java.util.Optional;

@Component
public class AuthService {

    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(AuthUserRepository authUserRepository, PasswordEncoder passwordEncoder) {
        this.authUserRepository = authUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void createUser(RegisterDTO dto ) {
        Optional<AuthUser> optional = authUserRepository.findAuthUserByUsername(dto.username());
        if ( optional.isPresent() ) throw new UserAlreadyExistsException( "Username: " + dto.username());

        AuthUser authUser = new AuthUser();
        authUser.setFullName(dto.fullName());
        authUser.setUsername(dto.username());
        authUser.setPassword( passwordEncoder.encode(dto.password()) );
        authUser.setRole( Role.ROLE_USER );

        authUserRepository.save(authUser);
    }

}
