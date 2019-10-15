package com.example.explainme.data.network.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DefinitionDto {
    private String word;
    private List<Definition> definitions;
}
