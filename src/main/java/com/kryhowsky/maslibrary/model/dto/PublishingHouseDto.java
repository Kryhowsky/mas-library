package com.kryhowsky.maslibrary.model.dto;

import com.kryhowsky.maslibrary.model.dao.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PublishingHouseDto {

    private Long id;
    private String name;
    private Address address;

}
