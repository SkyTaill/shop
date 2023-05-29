package ru.home.shopmx.web.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.home.shopmx.service.ManufacturerService;
import ru.home.shopmx.web.dto.ManufacturerDto;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

//BDD
//для создания контекста спринга и в дальнейшем что-бы мокать
@ExtendWith(MockitoExtension.class)
class ManufacturerControllerMockitoTest {
    @Mock
    ManufacturerService manufacturerService;

    @InjectMocks
    ManufacturerController manufacturerController;

    List<ManufacturerDto>manufacturers=new ArrayList<>();

    @BeforeEach
    void setUp(){

        manufacturers.add( ManufacturerDto.builder().manufacturerId(1L).name("Apple").build());
        manufacturers.add( ManufacturerDto.builder().manufacturerId(2L).name("Google").build());

        //заменяет что будет возвращать
        given(manufacturerService.findAll()).willReturn(manufacturers);

    }

    @Test
    void getManufacturerListTest(){
        //when
        List<ManufacturerDto> manufacturerList = manufacturerController.getManufacturerList();

        //then отслеживаем что метод выполняется
        then(manufacturerService).should().findAll();

        assertAll(
                ()->assertEquals(2,manufacturerList.size(),"size must be 2"),
                ()->assertEquals("Apple",manufacturerList.get(0).getName())

        );
    }
}