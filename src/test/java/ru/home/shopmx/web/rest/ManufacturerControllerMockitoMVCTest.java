package ru.home.shopmx.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.home.shopmx.entity.Manufacturer;
import ru.home.shopmx.service.ManufacturerService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//BDD
//для создания контекста спринга и в дальнейшем что-бы мокать
@ExtendWith(MockitoExtension.class)
class ManufacturerControllerMockitoMVCTest {
    @Mock
    ManufacturerService manufacturerService;

    @Autowired
    ObjectMapper objectMapper;

    @InjectMocks
    ManufacturerController manufacturerController;

    List<ManufacturerDto>manufacturers=new ArrayList<>();

    MockMvc mockMvc;

    @BeforeEach
    void setUp(){

        manufacturers.add( ManufacturerDto.builder().manufacturerId(1L).name("Apple").build());
        manufacturers.add( ManufacturerDto.builder().manufacturerId(2L).name("Google").build());

        //заменяет что будет возвращать
        given(manufacturerService.findAll()).willReturn(manufacturers);

        mockMvc= MockMvcBuilders.standaloneSetup(manufacturerController).build();

    }
    @Test
    void mockMvcGetManufacturerListTest() throws Exception {
        given(manufacturerService.findAll()).willReturn(manufacturers);
        //имитируем http метод
        mockMvc.perform(get("/api/v1/manufacturer"))
                //дальше пишем что ждем от нашего контроллера
                .andExpect(status().isOk())
                //парсим и ищем id
                .andExpect(content().string(containsString("id")))
                //обращение через доллар -> потом говорим что идет масив
                .andExpect(jsonPath("$.[0].id").value("1"))
                 .andExpect(jsonPath("$.[0].name").value("Apple"));

    }

    @Test
    void testSaveManufacturerTest() throws Exception {

        given(manufacturerService.save(any())).willReturn(new ManufacturerDto(3L, "Tesla"));

        mockMvc.perform(post("/api/v1/manufacturer")
                        .contentType(MediaType.APPLICATION_JSON)
                // можно еще так писать - .content{"{\"\name\": ..."}
                        .content(objectMapper
                                .writeValueAsString(Manufacturer.builder()
                                        .name("Tesla")
                                        .build())))
                .andExpect(status().isCreated());
    }

}