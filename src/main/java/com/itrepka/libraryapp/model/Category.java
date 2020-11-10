package com.itrepka.libraryapp.model;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "categoryId")
public class Category {
    private Integer categoryId;
    private String name;
}
