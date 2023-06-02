package com.app.gadgetblitz.service;

import com.app.gadgetblitz.dto.PhoneFullDto;
import com.app.gadgetblitz.dto.PhoneSimpleDto;
import com.app.gadgetblitz.dto.mapper.PhoneMapper;
import com.app.gadgetblitz.model.phone.Phone;
import com.app.gadgetblitz.repository.PhoneRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

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
    public Page<PhoneSimpleDto> findAll(Integer page, Integer size) {

        return repository.findAll(PageRequest.of(page, size))
                .map(phoneSimpleMapper::toDto);
    }

    @Override
    public Page<PhoneSimpleDto> findBySpecification(String name, String brand,
                                                    Double sizeMin, Double sizeMax,
                                                    Integer storageMin, Integer storageMax,
                                                    Double priceMin, Double priceMax,
                                                    Integer batteryMin, Integer batteryMax,
                                                    Integer page, Integer size) {

        Criteria c = new Criteria();
        List<Criteria> criteriaList = new ArrayList<>();

        if (name != null)
            criteriaList.add(Criteria.where("name").regex("(?i).*" + name + ".*"));
        if (brand != null) {
            List<Criteria> brandCriteriaList = new ArrayList<>();
            String[] brands = brand.split(",");
            for (String b : brands) {
                brandCriteriaList.add(Criteria.where("data.general.brand").is(b));
            }
            criteriaList.add(new Criteria().orOperator(brandCriteriaList.toArray(new Criteria[0])));
        }
        if (sizeMin != null || sizeMax != null)
            criteriaList.add(addRangeCriteria(sizeMin, sizeMax, "data.display.size__inch"));
        if (storageMin != null || storageMax != null)
            criteriaList.add(addRangeCriteria(storageMin, storageMax, "data.storage.capacity__gb"));
        if (batteryMin != null || batteryMax != null)
            criteriaList.add(addRangeCriteria(batteryMin, batteryMax, "data.battery.capacity__mAh"));
        if (priceMin != null || priceMax != null)
            criteriaList.add(addRangeCriteria(priceMin, priceMax, "price"));

        if (!criteriaList.isEmpty())
            c = new Criteria().andOperator(criteriaList.toArray(new Criteria[0]));

        Pageable pageable = PageRequest.of(page,size);

        Query query = new Query(c).with(pageable);

        List<PhoneSimpleDto> phones = mongoTemplate.find(query, Phone.class).stream()
                .map(phoneSimpleMapper::toDto)
                .collect(Collectors.toList());

        return PageableExecutionUtils.getPage(
                phones,
                pageable,
                () -> mongoTemplate.count(Query.of(query).limit(-1).skip(-1), Phone.class));
    }

    @Override
    public Optional<PhoneFullDto> findById(String id) {
        return repository.findById(id).map(phoneFullMapper::toDto);
    }

    @Override
    public PhoneFullDto addOpinion(String id, String opinion) {
        Optional<Phone> optionalPhone = repository.findById(id);
        if (optionalPhone.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        Phone phone = optionalPhone.get();
        if (phone.getOpinions() == null)
            phone.setOpinions(new ArrayList<>());
        phone.getOpinions().add(opinion);
        repository.save(phone);
//        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
        return phoneFullMapper.toDto(phone);
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
