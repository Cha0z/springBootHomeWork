package com.homework.home.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pants {

    @Id
    private Long id;

    private String name;

    private String manufacturer;

    private String size;

    private ClothesSex sex;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;
}
