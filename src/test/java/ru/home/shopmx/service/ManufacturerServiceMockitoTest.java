package ru.home.shopmx.service;

import com.example.api.manufacturer.dto.ManufacturerDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import ru.home.shopmx.dao.ManufacturerDao;
import ru.home.shopmx.entity.Manufacturer;
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
        //подменяем метод без которого не работает метод так как не развернут контекст спринга на хардкоженный
        given(manufacturerMapper.toManufacturer(any())).will(new ToManufacturer());
        given(manufacturerMapper.toManufacturerDto(any())).will(new ToManufacturerDto());



                //усли не сделать выше - упадет из-за мапера так как контекст спринга не поднят
        ManufacturerDto returnedManufacturerDto=manufacturerService.save(apple);

        //те должен быть вызван метод save - с любым Manufacturer.class
        then(manufacturerDao).should().save(any(Manufacturer.class));
        System.out.println(returnedManufacturerDto);
        assertEquals(1L,returnedManufacturerDto.getManufacturerId());
    }

}

class ToManufacturer implements Answer<Manufacturer> {

    @Override
    public Manufacturer answer(InvocationOnMock invocation) throws Throwable {
        ManufacturerDto manufacturerDto = (ManufacturerDto) invocation.getArgument(0);
        if ( manufacturerDto == null ) {
            return null;
        }

        Manufacturer.ManufacturerBuilder manufacturer = Manufacturer.builder();

        manufacturer.id( manufacturerDto.getManufacturerId() );
        manufacturer.name( manufacturerDto.getName() );

        return manufacturer.build();
    }
}

class ToManufacturerDto implements Answer<ManufacturerDto> {

    @Override
    public ManufacturerDto answer(InvocationOnMock invocation) throws Throwable {
        Manufacturer manufacturer = (Manufacturer) invocation.getArgument(0);
        if ( manufacturer == null ) {
            return null;
        }

        ManufacturerDto.ManufacturerDtoBuilder manufacturerDto = ManufacturerDto.builder();

        manufacturerDto.manufacturerId( manufacturer.getId() );
        manufacturerDto.name( manufacturer.getName() );

        return manufacturerDto.build();
    }
}