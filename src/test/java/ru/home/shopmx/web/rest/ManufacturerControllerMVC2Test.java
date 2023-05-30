package ru.home.shopmx.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.home.shopmx.entity.Manufacturer;
import ru.home.shopmx.service.ManufacturerService;
import ru.home.shopmx.web.dto.ManufacturerDto;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


//если хотим все приложение поднять - @SpringBootTest
@WebMvcTest
class ManufacturerControllerMVC2Test {

    @MockBean
    ManufacturerService manufacturerService;


    //переводит в json все наши обьекты
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void findAllTest() throws Exception {
        List<ManufacturerDto> manufacturers = new ArrayList<>();

        manufacturers.add(ManufacturerDto.builder().manufacturerId(1L).name("Apple").build());
        manufacturers.add(ManufacturerDto.builder().manufacturerId(2L).name("Microsoft").build());

        given(manufacturerService.findAll()).willReturn(manufacturers);

        mockMvc.perform(get("/api/v1/manufacturer"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("id")))
                .andExpect(jsonPath("$.[0].id").value("1"))
                .andExpect(jsonPath("$.[0].name").value("Apple"))
                .andExpect(jsonPath("$.[1].id").value("2"));
    }
}