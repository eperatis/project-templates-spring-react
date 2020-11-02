package hu.uni.eku.camping.controller;

import hu.uni.eku.camping.controller.dto.CampingSlotDto;
import hu.uni.eku.camping.controller.dto.CampingSlotRecordRequestDto;
import hu.uni.eku.camping.model.CampingSlot;
import hu.uni.eku.camping.service.CampingSlotService;
import hu.uni.eku.camping.service.exceptions.CampingSlotAlreadyExistsException;
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
    ){
        log.info("Recording of Camping Slot ({})",request.getDescription());
        try {
            service.record(new CampingSlot(
                    -1,
                    request.getStartCoordinate(),
                    request.getEndCoordinate(),
                    request.getSlotStatus(),
                    request.getDescription()
            ));
        } catch (CampingSlotAlreadyExistsException e) {
            log.info("Camping slot ({}) is already exists! Message: {}", request.getDescription(), e.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    e.getMessage()
            );
        }
    }

    @GetMapping(value = {"/"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ApiOperation(value= "Query Camping Slots")
    public Collection<CampingSlotDto> query(){
        return service.readAll().stream().map(model ->
                CampingSlotDto.builder()
                        .startCoordinate(model.getStartCoordinate())
                        .endCoordinate(model.getEndCoordinate())
                        .slotStatus(model.getSlotStatus())
                        .description(model.getDescription())
                        .build())
                .collect(Collectors.toList());
    }
}
