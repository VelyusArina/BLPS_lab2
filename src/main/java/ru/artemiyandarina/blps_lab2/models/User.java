package ru.artemiyandarina.blps_lab2.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "person")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class User {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @XmlAttribute
    private Long id;

    @Column(length = 30, nullable = false)
    @XmlAttribute
    private String name;

    @Column(length = 30, nullable = false)
    @XmlAttribute
    private String surname;

    @Column(length = 30, nullable = false, unique = true)
    @XmlAttribute
    private String email;

    @Column(nullable = false)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    private Role role = Role.ROLE_USER;

    @OneToMany(mappedBy = "owner")
    private List<Petition> documents = new ArrayList<>();

    public void setPassword(String password) {
        this.passwordHash = new BCryptPasswordEncoder().encode(password);
    }
}
