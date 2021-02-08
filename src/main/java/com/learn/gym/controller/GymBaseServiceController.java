package com.learn.gym.controller;

import com.learn.gym.db.entity.GymMetadata;
import com.learn.gym.service.GymBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1beta/gym")
public class GymBaseServiceController {

    @Autowired
    GymBaseService gymBaseService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public GymMetadata registerGym(@RequestBody GymMetadata gymMetadata) {
       return gymBaseService.createGym(gymMetadata);
    }

    @RequestMapping(value = "" , method =  RequestMethod.GET)
    public List<GymMetadata> getGyms() {
        return  gymBaseService.listGym(null);

    }

    @RequestMapping(value = "/{id}" , method =  RequestMethod.GET)
    public List<GymMetadata> getAGym(@PathVariable Integer id) {
        return  gymBaseService.listGym(id);

    }
}
