package com.julianmartinez.sportscentrerest.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Resource implements Serializable {

    private static final long serialVersionUID = 2323676126712776080L;

    int id;
    String name;
}
