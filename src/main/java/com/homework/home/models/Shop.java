package com.homework.home.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shop {

    @Id
    private Long id;

    private String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "shop")
    private List<Water> water;
//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "shop")
//    private List<Pants> pantsList;



}
