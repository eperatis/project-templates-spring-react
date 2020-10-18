package hu.uni.eku.camping.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDto {
    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
}