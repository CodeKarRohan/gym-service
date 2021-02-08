package com.learn.gym.samples;

import com.learn.gym.db.entity.GymMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class SampleController {


    @Autowired
    DatabseOperations dbo;

    @GetMapping("/error1")
    public String sayHello() {
        return String.format("Hello %s!", "rohan123");
    }

    @GetMapping("/create")
    public GymMetadata createGym() {


        return  dbo.createGymMetadata();
    }

    @GetMapping("/update")
    public GymMetadata UpdateGym() {


        return  dbo.UpdateGymMetadata();
    }

    @GetMapping("/allGyms")
    public List<GymMetadata> ListAllGyms() {


        return  dbo.listAllEntity();
    }


    @GetMapping("/delete")
    public String DeleteGym() {


          dbo.deleteTheData();
          return "Deleted entity";
    }
}
