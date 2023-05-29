package ru.home.shopmx.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.home.shopmx.dao.ManufacturerDao;
import ru.home.shopmx.entity.Manufacturer;
import ru.home.shopmx.web.dto.ManufacturerDto;
import ru.home.shopmx.web.dto.mapper.ManufacturerMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class ManufacturerServiceMockitoTest {
    @Mock
    ManufacturerDao manufacturerDao;

    @Mock
    ManufacturerMapper manufacturerMapper;

    @InjectMocks
    ManufacturerService manufacturerService;

    @Test
    void findAllManufacturersTest(){
        //given
        List<Manufacturer> manufacturers = new ArrayList<>();
        manufacturers.add(Manufacturer.builder()
                .id(1L)
                .name("Apple")
                .createdBy("user1")
                .createdDate(LocalDateTime.now())
                .version(1)
                .build());
        manufacturers.add(Manufacturer.builder()
                .id(1L)
                .name("Apple")
                .createdBy("user1")
                .createdDate(LocalDateTime.now())
                .version(1)
                .build());

            given(manufacturerDao.findAll()).willReturn(manufacturers);

            //when
        List<ManufacturerDto> manufacturerDtos=manufacturerService.findAll();

          then(manufacturerDao).should().findAll();

        assertEquals(manufacturers.size(),manufacturerDtos.size());
    }

    @Test
    void saveManufacturerTest(){
        Manufacturer manufacturerFromDao=Manufacturer.builder()
                .id(1L)
                .name("Apple")
                .createdBy("user1")
                .createdDate(LocalDateTime.now())
                .lastModifiedBy("user1")
                .lastModifiedDate(LocalDateTime.now())
                .version(1)
                .build();

        ManufacturerDto apple =new ManufacturerDto(null,"Apple");

        given(manufacturerDao.save(any(Manufacturer.class))).willReturn(manufacturerFromDao);
        given(manufacturerMapper.toManufacturer(any())).will()



                //усли не сделать выше - упадет из-за мапера так как контекст спринга не поднят
        ManufacturerDto returnedManufacturerDto=manufacturerService.save(apple);

        //те должен быть вызван метод save - с любым Manufacturer.class
        then(manufacturerDao).should().save(any(Manufacturer.class));
        System.out.println(returnedManufacturerDto);
        assertEquals(1L,returnedManufacturerDto.getManufacturerId());
    }

}