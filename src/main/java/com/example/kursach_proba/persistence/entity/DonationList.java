package com.example.kursach_proba.persistence.entity;


import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "donation_list")
public class DonationList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "donation_list_id")
    private Integer donationListId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "donationList", fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    private List<DonationItem> donationItems;

    @Column(name = "created_date", nullable = false, columnDefinition = "date default sysdate")
    private Date createdDate = new Date();


    //Option -> Needy ????????????
    @Transient
    private Map<Needy, Long> groupedOptions;

    public Integer getDonationListId() {
        return donationListId;
    }

    public void setDonationListId(Integer donationListId) {
        this.donationListId = donationListId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<DonationItem> getDonationItems() {
        return donationItems;
    }

    public void setDonationItems(List<DonationItem> donationItems) {
        this.donationItems = donationItems;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Map<Needy, Long> getGroupedOptions() {
        return groupedOptions;
    }

    public void setGroupedOptions(Map<Needy, Long> groupedOptions) {
        this.groupedOptions = groupedOptions;
    }
}
