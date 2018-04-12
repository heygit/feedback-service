package project.entity;

import javax.persistence.*;
import java.security.PrivateKey;
import java.security.PublicKey;

@Entity
public class AccountEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "hashed_password")
    private String hashedPassword;

    @Lob
    @Column(name = "private_key")
    private PrivateKey privateKey;

    @Lob
    @Column(name = "public_key")
    private PublicKey publicKey;

    public AccountEntity(String username, String hashedPassword, PrivateKey privateKey, PublicKey publicKey) {
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }

    public AccountEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }
}
