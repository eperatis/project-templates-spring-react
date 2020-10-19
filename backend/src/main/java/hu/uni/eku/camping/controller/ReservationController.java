package hu.uni.eku.camping.controller;

import hu.uni.eku.camping.controller.dto.CustomerDto;
import hu.uni.eku.camping.controller.dto.ReservationDto;
import hu.uni.eku.camping.controller.dto.ReservationRecordRequestDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping(value = "/reservation")
@RequiredArgsConstructor
@Api(tags = "Reservations")
@Slf4j
public class ReservationController {
    @PostMapping("/record")
    @ApiOperation(value = "Record")
    public void record(
            @RequestBody
                    ReservationRecordRequestDto request
    ) {
        log.info("Recording reservation for {} {} (address: {}, phone number: {}), at {} slot {}, from {} to {}",
                request.getCustomer().getFirstName(),
                request.getCustomer().getLastName(),
                request.getCustomer().getAddress(),
                request.getCustomer().getPhoneNumber(),
                request.getSlotId(),
                request.isElectricity() ? "with electricity" : "without electricity",
                request.getStart(),
                request.getEnd());
    }

    @GetMapping(value = {""}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Query Reservation")
    public Collection<ReservationDto> query() {
        Collection<ReservationDto> result = new ArrayList<>();
        ReservationDto dto = ReservationDto.builder()
                .customer(
                        CustomerDto.builder()
                                .firstName("John")
                                .lastName("Smith")
                                .address("3300, Eger Leányka utca 4.")
                                .phoneNumber("06309453234")
                                .build()
                )
                .price(6450)
                .build();
        result.add(dto);
        dto = ReservationDto.builder()
                .customer(
                        CustomerDto.builder()
                                .firstName("Mike")
                                .lastName("Hawk")
                                .address("3300, Eger Leányka utca 5.")
                                .phoneNumber("06309453235")
                                .build()
                )

                .price(7500)
                .build();
        result.add(dto);
        return result;
    }
}
