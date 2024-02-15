package dev.hasangurbuz.gradesapi.service;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PagedResult<T> {
    private Long total;
    private List<T> items;
}
