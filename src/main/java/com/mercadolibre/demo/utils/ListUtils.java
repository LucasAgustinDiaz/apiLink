package com.mercadolibre.demo.utils;

import com.mercadolibre.demo.mappers.LinkMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
public class ListUtils {


    public boolean exists(List<LinkMapper> list, String str) {
        Optional<LinkMapper> linkDTO = list.stream().filter(e -> e.getLink().equalsIgnoreCase(str)).findFirst();
        return linkDTO.isPresent();
    }

    public LinkMapper bringTheElementByUrl(List<LinkMapper> list, String str) {
        Optional<LinkMapper> linkDTO = list.stream().filter(e -> e.getLink().equalsIgnoreCase(str)).findFirst();
        return linkDTO.orElseGet(LinkMapper::new);
    }

    public List<LinkMapper> bringTheRestOfTheList(List<LinkMapper> list, String str) {
        List<LinkMapper> retrievedList = new ArrayList<>();
        for(LinkMapper e: list) {
          if (!e.getLink().equals(str))
              retrievedList.add(e);
        }
        return retrievedList;
    }

    public List<LinkMapper> bringTheRestOfTheListById(List<LinkMapper> list, String linkId) {
        List<LinkMapper> retrievedList = new ArrayList<>();
        for(LinkMapper e: list) {
            if (!e.getLink_id().equals(linkId))
                retrievedList.add(e);
        }
        return retrievedList;
    }

    public List<String> concatPasswords(List<String> list, String password){
        if (!list.contains(password))
            list.add(password);
        return list;
    }

    public void activateLink(LinkMapper linkMapper) {
        if (!linkMapper.getActive())
            linkMapper.setActive(true);
    }

    public Optional<LinkMapper> bringTheElementById(List<LinkMapper> list, String id) {
        return list.stream().filter(e -> e.getLink_id().equals(id)).findFirst();
    }
}
