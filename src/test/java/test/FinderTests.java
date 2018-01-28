package test;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import algorithm.FindMode;
import algorithm.ThingBirthDateDifference;
import org.junit.Before;
import org.junit.Test;

import algorithm.Finder;
import algorithm.Thing;

public class FinderTests {

	Thing sue = new Thing();
	Thing greg = new Thing();
	Thing sarah = new Thing();
	Thing mike = new Thing();

	@Before
	public void setup() {
		sue.name = "Sue";
		sue.birthDate = new Date(50, 0, 1);
		greg.name = "Greg";
		greg.birthDate = new Date(52, 5, 1);
		sarah.name = "Sarah";
		sarah.birthDate = new Date(82, 0, 1);
		mike.name = "Mike";
		mike.birthDate = new Date(79, 0, 1);
	}

	@Test
	public void Returns_Empty_Results_When_Given_Empty_List() {
		List<Thing> list = new ArrayList<Thing>();
		Finder finder = new Finder(list);

		ThingBirthDateDifference result = finder.find(FindMode.MINIMAL_DIFFERENCE);
		assertEquals(null, result.earlyBirthDateThing);

		assertEquals(null, result.lateBirthDateThing);
	}

	@Test
	public void Returns_Empty_Results_When_Given_One_Person() {
		List<Thing> list = new ArrayList<Thing>();
		list.add(sue);

		Finder finder = new Finder(list);

		ThingBirthDateDifference result = finder.find(FindMode.MINIMAL_DIFFERENCE);

		assertEquals(null, result.earlyBirthDateThing);
		assertEquals(null, result.lateBirthDateThing);
	}

	@Test
	public void Returns_Closest_Two_For_Two_People() {
		List<Thing> list = new ArrayList<Thing>();
		list.add(sue);
		list.add(greg);
		Finder finder = new Finder(list);

		ThingBirthDateDifference result = finder.find(FindMode.MINIMAL_DIFFERENCE);

		assertEquals(sue, result.earlyBirthDateThing);
		assertEquals(greg, result.lateBirthDateThing);
	}

	@Test
	public void Returns_Furthest_Two_For_Two_People() {
		List<Thing> list = new ArrayList<Thing>();
		list.add(mike);
		list.add(greg);

		Finder finder = new Finder(list);

		ThingBirthDateDifference result = finder.find(FindMode.MAXIMAL_DIFFERENCE);

		assertEquals(greg, result.earlyBirthDateThing);
		assertEquals(mike, result.lateBirthDateThing);
	}

	@Test
	public void Returns_Furthest_Two_For_Four_People() {
		List<Thing> list = new ArrayList<Thing>();
		list.add(sue);
		list.add(sarah);
		list.add(mike);
		list.add(greg);
		Finder finder = new Finder(list);

		ThingBirthDateDifference result = finder.find(FindMode.MAXIMAL_DIFFERENCE);

		assertEquals(sue, result.earlyBirthDateThing);
		assertEquals(sarah, result.lateBirthDateThing);
	}

	@Test
	public void Returns_Closest_Two_For_Four_People() {
		List<Thing> list = new ArrayList<Thing>();
		list.add(sue);
		list.add(sarah);
		list.add(mike);
		list.add(greg);

		Finder finder = new Finder(list);

		ThingBirthDateDifference result = finder.find(FindMode.MINIMAL_DIFFERENCE);

		assertEquals(sue, result.earlyBirthDateThing);
		assertEquals(greg, result.lateBirthDateThing);
	}

}
