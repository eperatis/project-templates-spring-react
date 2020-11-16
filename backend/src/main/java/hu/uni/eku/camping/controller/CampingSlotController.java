package hu.uni.eku.camping.controller;

import hu.uni.eku.camping.controller.dto.CampingSlotDto;
import hu.uni.eku.camping.controller.dto.CampingSlotRecordRequestDto;
import hu.uni.eku.camping.controller.dto.IntervalRequestDto;
import hu.uni.eku.camping.model.CampingSlot;
import hu.uni.eku.camping.model.SlotStatus;
import hu.uni.eku.camping.service.CampingSlotService;
import hu.uni.eku.camping.service.exceptions.CampingSlotAlreadyExistsException;
import hu.uni.eku.camping.service.exceptions.StartDateBeforeEndDateException;
import hu.uni.eku.camping.service.exceptions.StartDateBeforeTodayException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/slot")
@RequiredArgsConstructor
@Api(tags = "Camping Slots")
@Slf4j
public class CampingSlotController {
    private final CampingSlotService service;

    @PostMapping("/record")
    @ApiOperation(value = "Record")
    public void record(
            @RequestBody
                    CampingSlotRecordRequestDto request
    ) {
        log.info("Recording of Camping Slot ({})", request.getDescription());
        try {
            service.record(new CampingSlot(
                    -1,
                    request.getName(),
                    request.getStartCoordinate(),
                    request.getEndCoordinate(),
                    request.getDescription()
            ));
        } catch (CampingSlotAlreadyExistsException e) {
            log.info("Camping slot ({}) is already exists! Message: {}", request.getName(), e.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    e.getMessage()
            );
        }
    }

    @GetMapping(value = {"/status"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ApiOperation(value = "Query Camping Slots")
    public Collection<CampingSlotDto> query() {
        return service.readAll().stream().map(model ->
                CampingSlotDto.builder()
                        .id(model.getId())
                        .name(model.getName())
                        .startCoordinate(model.getStartCoordinate())
                        .endCoordinate(model.getEndCoordinate())
                        .slotStatus(service.isReserved(model.getId(), LocalDate.now())
                                ? SlotStatus.NOT_EMPTY
                                : SlotStatus.EMPTY)
                        .description(model.getDescription())
                        .build())
                .collect(Collectors.toList());
    }

    @PostMapping(value = {""}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ApiOperation(value = "Query Reserved Camping Slots during interval")
    public Collection<CampingSlotDto> queryReserved(
            @RequestBody
                    IntervalRequestDto request
    ) {
        try {
            return service.findAllBetweenInterval(request.getStart(), request.getEnd()).stream().map(model ->
                    CampingSlotDto.builder()
                            .id(model.getId())
                            .name(model.getName())
                            .startCoordinate(model.getStartCoordinate())
                            .endCoordinate(model.getEndCoordinate())
                            .slotStatus(service.isReserved(model.getId(), LocalDate.now())
                                    ? SlotStatus.NOT_EMPTY
                                    : SlotStatus.EMPTY)
                            .description(model.getDescription())
                            .build())
                    .collect(Collectors.toList());
        } catch (StartDateBeforeTodayException e) {
            log.info("A kezdődátum {} nem lehet a mai nap előtt!", request.getStart().toString());
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    e.getMessage()
            );
        } catch (StartDateBeforeEndDateException e) {
            log.info("A kezdődátum ({}) nem lehet a végdátum ({}) előtt!",
                    request.getStart().toString(), request.getEnd().toString()
            );
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    e.getMessage()
            );
        }
    }
}
