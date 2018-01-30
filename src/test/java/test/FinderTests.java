package test;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import algorithm.FindMode;
import algorithm.AgeDifference;
import org.junit.Before;
import org.junit.Test;

import algorithm.Finder;
import algorithm.People;

public class FinderTests {

	People sue = new People();
	People greg = new People();
	People sarah = new People();
	People mike = new People();

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
		List<People> list = new ArrayList<People>();
		Finder finder = new Finder(list);

		AgeDifference result = finder.find(FindMode.CLOSEST);
		assertEquals(null, result.youngPeople);

		assertEquals(null, result.oldPeople);
	}

	@Test
	public void Returns_Empty_Results_When_Given_One_Person() {
		List<People> list = new ArrayList<People>();
		list.add(sue);

		Finder finder = new Finder(list);

		AgeDifference result = finder.find(FindMode.CLOSEST);

		assertEquals(null, result.youngPeople);
		assertEquals(null, result.oldPeople);
	}

	@Test
	public void Returns_Closest_Two_For_Two_People() {
		List<People> list = new ArrayList<People>();
		list.add(sue);
		list.add(greg);
		Finder finder = new Finder(list);

		AgeDifference result = finder.find(FindMode.CLOSEST);

		assertEquals(sue, result.youngPeople);
		assertEquals(greg, result.oldPeople);
	}

	@Test
	public void Returns_Furthest_Two_For_Two_People() {
		List<People> list = new ArrayList<People>();
		list.add(mike);
		list.add(greg);

		Finder finder = new Finder(list);

		AgeDifference result = finder.find(FindMode.FURTHEST);

		assertEquals(greg, result.youngPeople);
		assertEquals(mike, result.oldPeople);
	}

	@Test
	public void Returns_Furthest_Two_For_Four_People() {
		List<People> list = new ArrayList<People>();
		list.add(sue);
		list.add(sarah);
		list.add(mike);
		list.add(greg);
		Finder finder = new Finder(list);

		AgeDifference result = finder.find(FindMode.FURTHEST);

		assertEquals(sue, result.youngPeople);
		assertEquals(sarah, result.oldPeople);
	}

	@Test
	public void Returns_Closest_Two_For_Four_People() {
		List<People> list = new ArrayList<People>();
		list.add(sue);
		list.add(sarah);
		list.add(mike);
		list.add(greg);

		Finder finder = new Finder(list);

		AgeDifference result = finder.find(FindMode.CLOSEST);

		assertEquals(sue, result.youngPeople);
		assertEquals(greg, result.oldPeople);
	}

}
