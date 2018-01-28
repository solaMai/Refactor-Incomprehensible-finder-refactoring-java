package algorithm;

import java.util.ArrayList;
import java.util.List;

public class Finder {
    private final List<Thing> things;

    public Finder(List<Thing> things) {
        this.things = things;
    }

    public ThingBirthDateDifference find(FindMode findMode) {
        if (findMode == FindMode.MINIMAL_DIFFERENCE) {
            return getMinimalBirthDateDifferenceFromList(getAllThingBirthDateDifferences());
        } else if (findMode == FindMode.MAXIMAL_DIFFERENCE) {
            return getMaximalBirthDateDifferenceFromList(getAllThingBirthDateDifferences());
        } else {
            throw new RuntimeException("Find Mode Exception");
        }
    }

    private ThingBirthDateDifference getMinimalBirthDateDifferenceFromList(List<ThingBirthDateDifference> differences) {
        ThingBirthDateDifference answer = differences.size() < 1 ? new ThingBirthDateDifference() : differences.get(0);
        for (ThingBirthDateDifference result : differences) {
            if (result.difference < answer.difference) {
                answer = result;
            }
        }
        return answer;
    }

    private ThingBirthDateDifference getMaximalBirthDateDifferenceFromList(List<ThingBirthDateDifference> differences) {
        ThingBirthDateDifference answer = differences.size() < 1 ? new ThingBirthDateDifference() : differences.get(0);
        for (ThingBirthDateDifference result : differences) {
            if (result.difference > answer.difference) {
                answer = result;
            }
            return answer;
        }
        return answer;
    }

    private List<ThingBirthDateDifference> getAllThingBirthDateDifferences() {
        List<ThingBirthDateDifference> differences = new ArrayList<ThingBirthDateDifference>();
        for (int i = 0; i < things.size() - 1; i++) {
            for (int j = i + 1; j < things.size(); j++) {
                differences.add(getThingBirthDateDifference(things.get(i), things.get(j)));
            }
        }
        return differences;
    }

    private ThingBirthDateDifference getThingBirthDateDifference(Thing thing1, Thing thing2) {
        ThingBirthDateDifference difference = new ThingBirthDateDifference();
        if (thing1.birthDate.getTime() < thing2.birthDate.getTime()) {
            difference.earlyBirthDateThing = thing1;
            difference.lateBirthDateThing = thing2;
        } else {
            difference.earlyBirthDateThing = thing2;
            difference.lateBirthDateThing = thing1;
        }
        difference.difference = difference.lateBirthDateThing.birthDate.getTime() - difference.earlyBirthDateThing.birthDate.getTime();
        return difference;
    }
}
