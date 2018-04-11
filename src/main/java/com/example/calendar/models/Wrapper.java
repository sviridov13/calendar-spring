package com.example.calendar.models;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Wrapper<T> {
    private final T object;
}
