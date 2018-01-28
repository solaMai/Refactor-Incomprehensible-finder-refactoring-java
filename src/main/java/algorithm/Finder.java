package algorithm;

import java.util.ArrayList;
import java.util.List;

public class Finder {
    private final List<Thing> things;

    public Finder(List<Thing> things) {
        this.things = things;
    }

    public ThingDifference find(FindMode findMode) {
        if (findMode == FindMode.MINIMAL_DIFFERENCE) {
            return getMinimalThingDifferenceFromList(getAllThingDifferences());
        } else if (findMode == FindMode.MAXIMAL_DIFFERENCE) {
            return getMaximalDifferenceFromList(getAllThingDifferences());
        } else {
            throw new RuntimeException("Find Mode Exception");
        }
    }

    private ThingDifference getMinimalThingDifferenceFromList(List<ThingDifference> differences) {
        ThingDifference result = differences.size() < 1 ? new ThingDifference() : differences.get(0);
        for (ThingDifference difference : differences) {
            if (difference.birthDateDifference < result.birthDateDifference) {
                result = difference;
            }
        }
        return result;
    }

    private ThingDifference getMaximalDifferenceFromList(List<ThingDifference> differences) {
        ThingDifference result = differences.size() < 1 ? new ThingDifference() : differences.get(0);
        for (ThingDifference difference : differences) {
            if (difference.birthDateDifference > result.birthDateDifference) {
                result = difference;
            }
        }
        return result;
    }

    private List<ThingDifference> getAllThingDifferences() {
        List<ThingDifference> differences = new ArrayList<ThingDifference>();
        for (int i = 0; i < things.size() - 1; i++) {
            for (int j = i + 1; j < things.size(); j++) {
                differences.add(getThingDifference(things.get(i), things.get(j)));
            }
        }
        return differences;
    }

    private ThingDifference getThingDifference(Thing thing1, Thing thing2) {
        ThingDifference difference = new ThingDifference();
        if (thing1.birthDate.getTime() < thing2.birthDate.getTime()) {
            difference.earlyBirthDateThing = thing1;
            difference.lateBirthDateThing = thing2;
        } else {
            difference.earlyBirthDateThing = thing2;
            difference.lateBirthDateThing = thing1;
        }
        difference.birthDateDifference = difference.lateBirthDateThing.birthDate.getTime() - difference.earlyBirthDateThing.birthDate.getTime();
        return difference;
    }
}
