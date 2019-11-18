package com.hdt.example_assess.utils.pageable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PageableOutput<T> {
    private int page;
    private int totalPage;
    private List<T> listResult = new ArrayList<>();
}
