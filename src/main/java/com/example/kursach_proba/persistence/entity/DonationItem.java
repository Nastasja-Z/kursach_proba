package com.example.kursach_proba.persistence.entity;


import javax.persistence.*;

@Entity
@Table(name = "donation_item")
public class DonationItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "donation_item_id")
    private Integer donationItemId;


    //field option_id from Needy
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "option_id")
    private Needy option;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "donation_list_id")
    private DonationList donationList;

    public Integer getDonationItemId() {
        return donationItemId;
    }

    public void setDonationItemId(Integer donationItemId) {
        this.donationItemId = donationItemId;
    }

    public Needy getOption() {
        return option;
    }

    public void setOption(Needy option) {
        this.option = option;
    }

    public DonationList getDonationList() {
        return donationList;
    }

    public void setDonationList(DonationList donationList) {
        this.donationList = donationList;
    }
}
