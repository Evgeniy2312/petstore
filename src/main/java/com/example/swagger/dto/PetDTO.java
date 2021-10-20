package com.example.swagger.dto;

import com.example.swagger.entity.PetStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PetDTO {

    private long id;
    private PetStatus petStatus;
}
