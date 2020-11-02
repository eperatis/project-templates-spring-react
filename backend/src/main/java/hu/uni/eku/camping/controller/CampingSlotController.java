package hu.uni.eku.camping.controller;

import hu.uni.eku.camping.controller.dto.CampingSlotDto;
import hu.uni.eku.camping.controller.dto.CampingSlotRecordRequestDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping(value = "/camping-slot")
@RequiredArgsConstructor
@Api(tags = "Camping Slot")
@Slf4j
public class CampingSlotController {

    @PostMapping("/record")
    @ApiOperation(value = "Record")
    public void record(
            @RequestBody CampingSlotRecordRequestDto request
            ){
        log.info("Recording of slot's coordinate ({}, {})", request.getStartCoordinate(),request.getEndCoordinate());
    }

    @GetMapping(value = {""}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ApiOperation(value = "Query camping slolts")
    public Collection<CampingSlotDto> query() {
        CampingSlotDto dto = CampingSlotDto.builder()
                .id(3)
                .startCoordinate(new Point(0,1))
                .endCoordinate(new Point(2,4))
                .price(4500)
                .reserved(true)
                .description("Fa")
                .build();

        Collection<CampingSlotDto> result = new ArrayList<>();
        result.add(dto);
        dto = CampingSlotDto.builder()
                .id(4)
                .startCoordinate(new Point(5,5))
                .endCoordinate(new Point(5,7))
                .price(4501)
                .reserved(false)
                .description("Fa2")
                .build();
        result.add(dto);
        return result;
    }

}
