package ru.home.shopmx.web.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.home.shopmx.entity.Manufacturer;
import ru.home.shopmx.web.dto.ManufacturerDto;


@Mapper
public interface ManufacturerMapper {
    @Mapping(source = "manufacturerId", target = "id")
    Manufacturer toManufacturer(ManufacturerDto manufacturerDto);

    @Mapping(source = "id", target = "manufacturerId")
    ManufacturerDto toManufacturerDto(Manufacturer manufacturer);
}
