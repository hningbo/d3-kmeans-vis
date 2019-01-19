package edu.rylynn.service;

import edu.rylynn.controller.KMeansController;
import edu.rylynn.entity.Cluster;
import edu.rylynn.entity.Point;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author rylynn
 * @version 2019-01-19
 * @classname KMeansService
 * @discription
 */

@Service
public class KMeansService {
    private List<Point> points = new ArrayList<>();
    private List<Cluster> clusters = new ArrayList<>();

    public List<Cluster> generatePoint() {
        List<Point> initCenters = new ArrayList<>();

        points.clear();
        Random random = new Random();

        int[] seedX = new int[6];
        int[] seedY = new int[6];
        for (int i = 0; i < 6; i++) {
            seedX[i] = random.nextInt(10);
            seedY[i] = random.nextInt(10);
        }

        for (int i = 3; i < 6; i++) {
            initCenters.add(new Point(random.nextGaussian() * 20 + 50 + 30 * seedX[i], random.nextGaussian() * 20 + 50 + 30 * seedY[i]));
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 100; j++) {
                Point point = new Point( random.nextGaussian() * 20 + 50 + 30 * seedX[i], random.nextGaussian() * 20 + 50 + 30 * seedY[i]);
                point.setId(i * 100 + j);
                points.add(point);
            }
        }

        clusters = getCluster(initCenters);
        return clusters;
    }

    private void updateCenter(List<Cluster> clusters) {
        for (Cluster cluster : clusters) {
            double tempX = 0.0d;
            double tempY = 0.0d;
            List<Point> members = cluster.getMembers();
            if (members.size() != 0) {
                for (Point member : members) {
                    tempX += member.getX();
                    tempY += member.getY();
                }
                tempX /= members.size();
                tempY /= members.size();
                cluster.setCenter(new Point(tempX, tempY));
            }
        }
    }

    private List<Cluster> getCluster(List<Point> centers) {
        List<Cluster> newClusters = new ArrayList<>();

        for (Point center : centers) {
            newClusters.add(new Cluster(center));
        }

        for (Point point : points) {
            double closestDist = Double.MAX_VALUE;
            int closestIndex = 0;
            for (int i = 0; i < centers.size(); i++) {
                if (point.getDistance(centers.get(i)) < closestDist) {
                    closestDist = point.getDistance(centers.get(i));
                    closestIndex = i;
                }
            }
            newClusters.get(closestIndex).addMember(point);
            point.setClazz(closestIndex);
        }

        clusters = newClusters;
        return clusters;

    }

    public List<List<Cluster>> doKmeans() {
        List<List<Cluster>> results = new ArrayList<>();

        List<Point> tempCenters = new ArrayList<>();
        int iterationEpoch = 0;

        while (iterationEpoch < 5) {
            tempCenters.clear();
            updateCenter(clusters);
            for (Cluster cluster : clusters) {
                tempCenters.add(cluster.getCenter());
            }

            getCluster(tempCenters);
            results.add(clusters);
            iterationEpoch++;
        }

        return results;
    }


}
