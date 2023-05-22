package com.app.gadgetblitz.service;

import com.app.gadgetblitz.dto.PhoneFullDto;
import com.app.gadgetblitz.dto.PhoneSimpleDto;
import com.app.gadgetblitz.dto.mapper.PhoneMapper;
import com.app.gadgetblitz.model.phone.Phone;
import com.app.gadgetblitz.repository.PhoneRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class PhoneServiceImpl implements PhoneService {
    private final PhoneRepository repository;
    private final PhoneMapper.Full phoneFullMapper;
    private final PhoneMapper.Simple phoneSimpleMapper;
    private final MongoTemplate mongoTemplate;

    @Override
    public List<PhoneSimpleDto> findAll(Integer page, Integer size) {
        return repository.findAll(PageRequest.of(page, size)).getContent().stream()
                .map(phoneSimpleMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PhoneSimpleDto> findBySpecification(String name, String brand,
                                                    Double sizeMin, Double sizeMax,
                                                    Integer storageMin, Integer storageMax,
                                                    Double priceMin, Double priceMax,
                                                    Integer cameraBackMin, Integer cameraBackMax) {

        Criteria c = new Criteria();
        List<Criteria> criteriaList = new ArrayList<>();
        if (name != null)
            criteriaList.add(Criteria.where("name").regex("(?i).*" + name + ".*"));
        if (brand != null)
            criteriaList.add(Criteria.where("data.general.brand").is(brand));
        if (sizeMin != null || sizeMax != null)
            criteriaList.add(addRangeCriteria(sizeMin, sizeMax, "data.display.size__inch"));
        if (storageMin != null || storageMax != null)
            criteriaList.add(addRangeCriteria(storageMin, storageMax, "data.storage.capacity__gb"));
        if (cameraBackMin != null || cameraBackMax != null)
            criteriaList.add(addRangeCriteria(cameraBackMin, cameraBackMax, "data.camera.camera_back__mp"));
        if (priceMin != null || priceMax != null)
            criteriaList.add(addRangeCriteria(priceMin, priceMax, "price"));

        if (!criteriaList.isEmpty())
            c = new Criteria().andOperator(criteriaList.toArray(new Criteria[0]));

        Query query = new Query(c);
//                .with(PageRequest.of(0 ,2));
        // TODO pagination

        return mongoTemplate.find(query, Phone.class).stream()
                .map(phoneSimpleMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PhoneFullDto> findById(String id) {
        return repository.findById(id).map(phoneFullMapper::toDto);
    }

    private <T extends Number> Criteria addRangeCriteria(T min, T max, String field) {
        Criteria criteria = new Criteria();
        if (min != null && max != null) {
            criteria.andOperator(
                    Criteria.where(field).gte(min),
                    Criteria.where(field).lte(max)
            );
        } else if (min != null) {
            criteria.and(field).gte(min);
        } else if (max != null) {
            criteria.and(field).lte(max);
        }
        return criteria;
    }
}
