package com.productdelivery.core.entities;

public record Address (
    String city,
    String state,
    String road,
    String houseNumber
) {
}
