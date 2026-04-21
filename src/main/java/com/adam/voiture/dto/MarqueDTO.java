package com.adam.voiture.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarqueDTO {
    private Long idmarque;
    private String nommarque;
    private String descriptionmarque;
}