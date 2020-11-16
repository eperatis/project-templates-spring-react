package hu.uni.eku.camping.controller;

import hu.uni.eku.camping.controller.dto.CustomerDto;
import hu.uni.eku.camping.controller.dto.ReservationDto;
import hu.uni.eku.camping.controller.dto.ReservationRecordRequestDto;
import hu.uni.eku.camping.model.Customer;
import hu.uni.eku.camping.model.PaymentStatus;
import hu.uni.eku.camping.model.Reservation;
import hu.uni.eku.camping.service.ReservationService;
import hu.uni.eku.camping.service.exceptions.CampingSlotAlreadyReservedException;
import hu.uni.eku.camping.service.exceptions.EmptyStringException;
import hu.uni.eku.camping.service.exceptions.ReservationAlreadyExistsException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/reservation")
@RequiredArgsConstructor
@Api(tags = "Reservation")
@Slf4j
public class ReservationController {
    private final ReservationService service;

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
        try {
            service.record(new Reservation(
                    request.getStart(),
                    request.getEnd(),
                    request.isElectricity(),
                    PaymentStatus.NOT_PAID
            ), new Customer(
                    -1,
                    request.getCustomer().getFirstName(),
                    request.getCustomer().getLastName(),
                    request.getCustomer().getAddress(),
                    request.getCustomer().getPhoneNumber()
            ), request.getSlotId());
        } catch (ReservationAlreadyExistsException e) {
            log.info("Reservation ({},{}) is already exists! Message: {}", request.getStart(), request.getEnd(), e.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    e.getMessage()
            );
        } catch (CampingSlotAlreadyReservedException e) {
            log.info("Camping slot ({}) is already reserved! Message: {}", request.getSlotId(), e.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    e.getMessage()
            );
        } catch (EmptyStringException e) {
            log.info("Empty parameter! Message: {}", e.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    e.getMessage()
            );
        }
    }

    @GetMapping(value = {"/{customerId}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Query Reservation")
    public ReservationDto query(@PathVariable int customerId) {
        Customer customer = service.getCustomer(customerId);
        return ReservationDto.builder()
                .customer(CustomerDto.builder()
                        .id(customer.getId())
                        .firstName(customer.getFirstName())
                        .lastName(customer.getLastName())
                        .address(customer.getAddress())
                        .phoneNumber(customer.getPhoneNumber())
                        .build())
                .price(service.getPrice(customerId))
                .expanses(service.findByCustomerId(customerId).getExpenses())
                .build();
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Delete")
    public void delete(@PathVariable int id) {
        log.info("Deleting reservation {}", id);
    }

    @PostMapping("/pay/{customerId}")
    @ApiOperation(value = "Post")
    public void pay(@PathVariable int customerId) {
        service.payReservation(customerId);
    }
}
