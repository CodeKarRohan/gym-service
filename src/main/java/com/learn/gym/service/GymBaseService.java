package com.learn.gym.service;

import com.learn.gym.db.entity.GymMetadata;

import java.util.List;

public interface GymBaseService {
    GymMetadata createGym(GymMetadata gym);
    List<GymMetadata> listGym(Integer gymId);
    void deleteGym(Integer gymId);
    GymMetadata updateGym(GymMetadata gym);


}
