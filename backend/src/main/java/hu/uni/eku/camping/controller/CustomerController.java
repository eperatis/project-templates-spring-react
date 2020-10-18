package hu.uni.eku.camping.controller;

import hu.uni.eku.camping.controller.dto.CustomerDto;
import hu.uni.eku.camping.controller.dto.CustomerRequestDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping(value = "/customer")
@RequiredArgsConstructor
@Api(tags = "Customers")
@Slf4j
public class CustomerController {
    @PostMapping("/record")
    @ApiOperation(value = "Record")
    public void record(
            @RequestBody
                    CustomerRequestDto request
    ) {
        log.info("Recording of customer ({}, {})", request.getFirstName(), request.getLastName());
    }

    @GetMapping(value = {"/"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ApiOperation(value= "Query customers")
    public Collection<CustomerDto> query() {
        Collection<CustomerDto> result = new ArrayList<>();

        CustomerDto dto = CustomerDto.builder()
                .firstName("John")
                .lastName("Smith")
                .address("Eger, Leányka utca 4")
                .phoneNumber("06704563455")
                .build();
        result.add(dto);
        dto = CustomerDto.builder()
                .firstName("Jane")
                .lastName("Doe")
                .address("Eger, Barkóczy utca 13")
                .phoneNumber("06706562467")
                .build();
        result.add(dto);
        return result;
    }
}
