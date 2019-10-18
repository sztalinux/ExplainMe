package com.example.explainme.data.network.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DefinitionDto {
    private String word;
    private List<Definition> definitions;
}
