package com.app.gadgetblitz.dto.mapper;

import com.app.gadgetblitz.dto.PhoneSimpleDto;
import com.app.gadgetblitz.model.phone.Phone;
import com.app.gadgetblitz.model.phone.components.Image;
import com.app.gadgetblitz.model.phone.components.Price;
import org.mapstruct.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PhoneMapper {

    @Mapping(source = "data.display.size__inch", target = "size")
    @Mapping(source = "data.software.os", target = "system")
    @Mapping(source = "data.cpu.type", target = "procesor")
    @Mapping(source = "data.storage.capacity__gb", target = "storage")
    @Mapping(target = "urls", expression = "java(filterUrls(phone.getImages()))")
    @Mapping(target = "price", expression = "java(computeAverage(phone.getPrices()))")
    PhoneSimpleDto toDto(Phone phone);

    default BigDecimal computeAverage(List<Price> list) {
        if (list == null || list.isEmpty())
            return null;

        BigDecimal sum = list.stream()
                .map(Price::price)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return sum.divide(BigDecimal.valueOf(list.size()), 2, RoundingMode.HALF_UP);
    }

    default List<String> filterUrls(List<Image> images) {
        if (images == null || images.isEmpty())
            return null;

        return images.stream()
                .filter(Objects::nonNull)
                .map(Image::url)
                .collect(Collectors.toList());
    }


}