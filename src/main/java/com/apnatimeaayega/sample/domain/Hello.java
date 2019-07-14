package com.apnatimeaayega.sample.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@RequiredArgsConstructor
public class Hello {

    private final String name;

    private final int id;
}
