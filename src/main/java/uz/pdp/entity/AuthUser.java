package uz.pdp.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.pdp.enums.Role;
import uz.pdp.enums.Status;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class AuthUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "full_name")
    private String fullName;

    @Column(unique = true, nullable = false)
    private String username;
    private String password;


    @Enumerated(EnumType.STRING)
    private Role role;


    private Status status;


}
