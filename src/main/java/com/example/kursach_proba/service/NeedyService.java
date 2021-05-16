package com.example.kursach_proba.service;

import com.example.kursach_proba.persistence.entity.Needy;
import com.example.kursach_proba.persistence.repository.NeedyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import static org.springframework.util.ResourceUtils.CLASSPATH_URL_PREFIX;
import static org.springframework.util.ResourceUtils.getFile;

@Service
public class NeedyService {
    /*path to storage from product*/


    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private NeedyRepository needyRepository;

    public Iterable<Needy> getAllNeedies() {
        return needyRepository.findAllByOrderByNameNeedy();
    }

    public Iterable<Needy> findByOptionId(Integer optionId) {
        return needyRepository.findAllByOptionOptionId(optionId);
    }

    public Needy saveNeedy(Needy needy) {
        return needyRepository.save(needy);
    }

    public Needy findNeedyById(Integer needyId) {
        return needyRepository.findById(needyId).orElse(null);
    }

    public void deleteNeedy(Integer needyId) {
        needyRepository.deleteById(needyId);
    }

    private final String STORAGE_FOLDER = "/storage/";

    private final Character SEPARATOR = '.';

    private String fullPathToStorage;

    //????????????
    public NeedyService() {
        this.fullPathToStorage = getPathToStorage();

    }

    public String getFullPathToStorage() {
        return this.fullPathToStorage;
    }

    public String buildUniquePhotoPath(String originalName) {
        int lastDot = originalName.lastIndexOf(SEPARATOR);
        StringBuilder result = new StringBuilder();
        result.append(STORAGE_FOLDER);
        result.append(originalName.substring(0, lastDot));
        result.append(new Date().getTime());
        result.append(originalName.substring(lastDot));
        return result.toString();
    }

    private String getPathToStorage() {
        try {
            return getFile(CLASSPATH_URL_PREFIX + "static").getPath();
        } catch (Exception e) {
            logger.error("ERROR during creation of path to storage folder", e);
            return null;
        }
    }

}
