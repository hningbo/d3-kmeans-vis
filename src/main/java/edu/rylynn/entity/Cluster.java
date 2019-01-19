package edu.rylynn.entity;

import edu.rylynn.service.KMeansService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rylynn
 * @version 2019-01-19
 * @classname Cluster
 * @discription
 */

public class Cluster {
    private Point center;
    private List<Point> members = new ArrayList<>();

    public Cluster(Point center) {
        this.center = center;
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }


    public List<Point> getMembers() {
        return members;
    }

    public void setMembers(List<Point> members) {
        this.members = members;
    }

    public void addMember(Point point){
        this.members.add(point);
    }
}

