package com.example.kursach_proba.persistence.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "options")
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_id")
    private Integer optionId;

    @Column(name = "name_option", nullable = false)
    private String nameOfOption;

    @Column(name = "price_option", nullable = false)
    private Integer price;

    public Integer getOptionId() {
        return optionId;
    }

    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
    }

    public String getNameOfOption() {
        return nameOfOption;
    }

    public void setNameOfOption(String nameOfOption) {
        this.nameOfOption = nameOfOption;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Option option = (Option) o;
        return optionId.equals(option.optionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(optionId);
    }
}
