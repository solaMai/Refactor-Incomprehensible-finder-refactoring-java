package algorithm;

import java.util.ArrayList;
import java.util.List;

public class Finder {
    private final List<People> peopleList;

    public Finder(List<People> peopleList) {
        this.peopleList = peopleList;
    }

    public AgeDifference find(FindMode findMode) {
        if (findMode == FindMode.CLOSEST) {
            return getMinimalAgeDifferenceFromList(getAllAgeDifference());
        } else if (findMode == FindMode.FURTHEST) {
            return getMaximalAgeDifferenceFromList(getAllAgeDifference());
        } else {
            throw new RuntimeException("Find Mode Exception");
        }
    }

    private AgeDifference getMinimalAgeDifferenceFromList(List<AgeDifference> differences) {
        AgeDifference result = differences.size() < 1 ? new AgeDifference() : differences.get(0);
        for (AgeDifference difference : differences) {
            if (difference.difference < result.difference) {
                result = difference;
            }
        }
        return result;
    }

    private AgeDifference getMaximalAgeDifferenceFromList(List<AgeDifference> differences) {
        AgeDifference result = differences.size() < 1 ? new AgeDifference() : differences.get(0);
        for (AgeDifference difference : differences) {
            if (difference.difference > result.difference) {
                result = difference;
            }
        }
        return result;
    }

    private List<AgeDifference> getAllAgeDifference() {
        List<AgeDifference> differences = new ArrayList<AgeDifference>();
        for (int i = 0; i < peopleList.size() - 1; i++) {
            for (int j = i + 1; j < peopleList.size(); j++) {
                differences.add(getAgeDifference(peopleList.get(i), peopleList.get(j)));
            }
        }
        return differences;
    }

    private AgeDifference getAgeDifference(People people1, People people2) {
        AgeDifference difference = new AgeDifference();
        if (people1.birthDate.getTime() < people2.birthDate.getTime()) {
            difference.youngPeople = people1;
            difference.oldPeople = people2;
        } else {
            difference.youngPeople = people2;
            difference.oldPeople = people1;
        }
        difference.difference = difference.oldPeople.birthDate.getTime() - difference.youngPeople.birthDate.getTime();
        return difference;
    }
}
