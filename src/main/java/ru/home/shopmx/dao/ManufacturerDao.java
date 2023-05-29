package ru.home.shopmx.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.home.shopmx.entity.Manufacturer;


import java.util.Optional;

public interface ManufacturerDao extends JpaRepository<Manufacturer, Long> {
    Optional<Manufacturer> findByName(String name);
}
