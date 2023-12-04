package com.bezkoder.springjwt.models;
import jakarta.persistence.*;

import java.time.Instant;


@Entity
@Table(name = "refreshtoken")
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "token", nullable = false, unique = true)
    private String token;
    @Column(name = "expiryDate", nullable = false)
    private Instant expiryDate;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private User user;

//    @Column(name = "id")
//    private int id;
//    @ManyToOne()
//    @JoinColumn(name = "userId", referencedColumnName = "")
//    @JsonBackReference
//    private User user;


    public int getId() {
        return id;
    }

    public void setId(int tokenId) {
        this.id = tokenId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Instant getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Instant expiryDate) {
        this.expiryDate = expiryDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
