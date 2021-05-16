package com.example.kursach_proba.persistence.entity;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String username;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<DonationList> donationLists;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<DonationList> getDonationLists() {
        return donationLists;
    }

    public void setDonationLists(Set<DonationList> donationLists) {
        this.donationLists = donationLists;
    }
}
