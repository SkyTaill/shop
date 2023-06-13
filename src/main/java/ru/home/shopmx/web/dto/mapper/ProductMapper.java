package ru.home.shopmx.web.dto.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;

import ru.home.shopmx.dao.ManufacturerDao;
import ru.home.shopmx.entity.Manufacturer;
import ru.home.shopmx.entity.Product;

import java.util.NoSuchElementException;

@Mapper(uses = ManufacturerMapper.class)
public interface ProductMapper {
    Product toProduct(ProductDto productDto, @Context ManufacturerDao manufacturerDao);

    ProductDto toProductDto(Product product);

    default Manufacturer getManufacturer(String manufacturer, @Context ManufacturerDao manufacturerDao) {
        return manufacturerDao.findByName(manufacturer).orElseThrow(NoSuchElementException::new);
    }

    default String getManufacturer(Manufacturer manufacturer) {
        return manufacturer.getName();
    }

}
