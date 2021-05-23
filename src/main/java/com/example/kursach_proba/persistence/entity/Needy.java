package com.example.kursach_proba.persistence.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "needy")
public class Needy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "needy_id")
    private Integer needyId;

    @Column(name = "needy_name", nullable = false)
    private String nameNeedy;

    @Column(name = "needy_status", nullable = false)
    private String status;

    @Column(name = "photo_path")
    private String photoPath;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "option_id")
    private Option option;

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }

    public Integer getNeedyId() {
        return needyId;
    }

    public void setNeedyId(Integer needyId) {
        this.needyId = needyId;
    }

    public String getNameNeedy() {
        return nameNeedy;
    }

    public void setNameNeedy(String nameNeedy) {
        this.nameNeedy = nameNeedy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public Integer getOptionId() {
        return this.option == null ? null : this.option.getOptionId();
    }

    public void setOptionId(Integer optionId) {
        if (this.option == null) {
            this.setOption(new Option());
        }
        this.getOption().setOptionId(optionId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Needy needy = (Needy) o;
        return needyId.equals(needy.needyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(needyId);
    }

}
