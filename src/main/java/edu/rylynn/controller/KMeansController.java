package edu.rylynn.controller;

import edu.rylynn.entity.Cluster;
import edu.rylynn.service.KMeansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author rylynn
 * @version 2019-01-15
 * @classname KMeansService
 * @discription
 */

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class KMeansController {

    @Autowired
    private KMeansService kMeansService;

    @RequestMapping("/kmeansDataGenerator")
    public @ResponseBody
    List<Cluster> kmeansDataGenerator() {
        return kMeansService.generatePoint();
    }

    @RequestMapping(value = "doKmeans", method = RequestMethod.GET)
    public @ResponseBody
    List<List<Cluster>> kmeans() {
        return kMeansService.doKmeans();
    }


}
