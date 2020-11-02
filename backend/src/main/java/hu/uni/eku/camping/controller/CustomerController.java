package hu.uni.eku.camping.controller;

import hu.uni.eku.camping.controller.dto.CustomerDto;
import hu.uni.eku.camping.controller.dto.CustomerRecordRequestDto;
import hu.uni.eku.camping.model.Customer;
import hu.uni.eku.camping.service.CustomerService;
import hu.uni.eku.camping.service.exceptions.CustomerAlreadyExistsException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/customer")
@RequiredArgsConstructor
@Api(tags = "Customers")
@Slf4j
public class CustomerController {

    private final CustomerService service;

    @PostMapping("/record")
    @ApiOperation(value = "Record")
    public void record(
            @RequestBody
                    CustomerRecordRequestDto request
    ) {
        log.info("Recording of customer ({},{})", request.getFirstName(), request.getLastName());
        try {
            service.record(new Customer(
                    0,
                    request.getFirstName(),
                    request.getLastName(),
                    request.getAddress(),
                    request.getPhoneNumber()
            ));
        } catch (CustomerAlreadyExistsException e) {
            log.info("Customer ({},{}) is already exists! Message: {}", request.getFirstName(), request.getLastName(), e.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    e.getMessage()
            );
        }
    }

    @GetMapping(value = {"/"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ApiOperation(value = "Query customers")
    public Collection<CustomerDto> query() {
        return service.readAll().stream().map(model ->
                CustomerDto.builder()
                        .id(model.getId())
                        .firstName(model.getFirstName())
                        .lastName(model.getLastName())
                        .address(model.getAddress())
                        .phoneNumber(model.getPhoneNumber())
                        .build())
                .collect(Collectors.toList());
    }
}
