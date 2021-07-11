package com.mercadolibre.demo.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.demo.mappers.LinkMapper;
import com.mercadolibre.demo.dtos.responses.ResponseIdLinkIdDTO;
import com.mercadolibre.demo.mappers.CreateLinkMapper;
import com.mercadolibre.demo.services.GeneratorLinkIDService;
import com.mercadolibre.demo.utils.ListUtils;
import org.springframework.stereotype.Repository;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Data Persistence
 */
@Repository
public class JsonRepository {

    GeneratorLinkIDService generatorLinkIDService;
    // utility
    ListUtils listUtils;

    // variables
    LinkMapper linkMapper;
    ObjectMapper objectMapper;
    // constructor methods   ===========================================


    public JsonRepository(GeneratorLinkIDService generatorLinkIDService, ListUtils listUtils) {
        this.generatorLinkIDService = generatorLinkIDService;
        this.listUtils = listUtils;
        objectMapper = new ObjectMapper();
    }

    // methods   ===========================================

    private List<LinkMapper> mapDataBase(String path) {
        ObjectMapper objectMapper = new ObjectMapper(); // indagar en este mappeador
        TypeReference<List<LinkMapper>> typeReference = new TypeReference<>() {};
        List<LinkMapper> objects = null;
        try{
            objects = objectMapper.readValue(new File(path),
                    objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, LinkMapper.class));
//            objects = objectMapper.readValue(new File(path), List.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return objects;
    }

    public ResponseIdLinkIdDTO addResourceToDataBase(CreateLinkMapper createLinkMapper) throws IOException {
//        ObjectMapper objectMapper = new ObjectMapper();
        List<LinkMapper> objects = mapDataBase("src/main/resources/static/database.json");  // des-serializar
        // if an object with the url exists, then we only update it ?================
        if( listUtils.exists(objects,createLinkMapper.getUrl()) ) {
            // recuperamos el objeto y concatenamos el password a la lista de passwords
            linkMapper = listUtils.bringTheElementByUrl(objects,createLinkMapper.getUrl());
            linkMapper.setPasswords(listUtils.concatPasswords(linkMapper.getPasswords(),createLinkMapper.getPassword()));
            listUtils.activateLink(linkMapper); // si no esta activo, lo activamos
            // quitamos el objeto de la lista
            objects = listUtils.bringTheRestOfTheList(objects,createLinkMapper.getUrl());
        } else { // if it doesn't exist we create it ?=========
            linkMapper = new LinkMapper(createLinkMapper.getUrl(),generatorLinkIDService.generateID(), new ArrayList<>(Collections.singleton(createLinkMapper.getPassword())),true,0);
        }
        try{
            objects.add(linkMapper);
            objectMapper.writeValue(new File("src/main/resources/static/database.json"), objects);
        } catch (IOException e){
            e.printStackTrace();
        }

        return linkMapper.constructResponseId(linkMapper,createLinkMapper.getPassword());

    }

    public Optional<LinkMapper> getOneElementById(String linkId){
        List<LinkMapper> objects = mapDataBase("src/main/resources/static/database.json");  // des-serializar
        return listUtils.bringTheElementById(objects,linkId);
    }

    private List<LinkMapper> getData(){
        return mapDataBase("src/main/resources/static/database.json");
    }

    public void incrementCounter(LinkMapper linkMapper) {
        List<LinkMapper> objects = mapDataBase("src/main/resources/static/database.json");
        objects = listUtils.bringTheRestOfTheListById(objects,linkMapper.getLink_id());
        linkMapper.setRedirects(linkMapper.getRedirects() + 1);
        try{
            objects.add(linkMapper);
            objectMapper.writeValue(new File("src/main/resources/static/database.json"), objects);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void invalidate(LinkMapper linkMapper) {
        List<LinkMapper> objects = mapDataBase("src/main/resources/static/database.json");
        objects = listUtils.bringTheRestOfTheListById(objects,linkMapper.getLink_id());
        linkMapper.setActive(false);
        try{
            objects.add(linkMapper);
            objectMapper.writeValue(new File("src/main/resources/static/database.json"), objects);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}







