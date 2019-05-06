package com.homework.home.repository;

import com.homework.home.models.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopRepository extends JpaRepository<Shop,Long> {

    List<Shop> findByNameContaining(String name);
    List<Shop> findByNameContainingAndWaterManufacturer(String name,String manufacturer);


}
