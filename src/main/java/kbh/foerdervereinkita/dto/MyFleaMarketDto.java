package kbh.foerdervereinkita.dto;

import java.util.Collection;

public record MyFleaMarketDto(
    String fullName,
    String email,
    boolean hasPaid,
    Collection<Integer> storingPositionLocationNumbers) {}
