package com.example.kursach_proba.service;

import com.example.kursach_proba.persistence.entity.Option;
import com.example.kursach_proba.persistence.repository.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OptionService {

    @Autowired
    private OptionRepository optionRepository;

    public Iterable<Option> getAllOptions() {
        return optionRepository.findAll();
    }


    public Option findOptionById(Integer optionId) {
        return optionRepository.findById(optionId).orElse(null);

    }

    public Option saveOption(Option option) {
        return optionRepository.save(option);
    }

    public void deleteOption(Integer optionId) {
        optionRepository.deleteById(optionId);
    }


}
