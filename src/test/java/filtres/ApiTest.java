package filtres;

import filters.Main;
import filters.classes.Person;
import filters.enums.Education;
import filters.enums.Gender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class ApiTest {

    static final Long count = 3L;
    static final List<String> names = Arrays.asList("Bob", "Oleg");
    static final List<String> fam = Arrays.asList("Yellow", "Red", "Blue");

    Collection<Person> input = new ArrayList<>();

    public void InitialData() {
        input.add(new Person("Garry", "Black", 13, Gender.MAN, Education.ELEMENTARY));
        input.add(new Person("Denis", "White", 45, Gender.MAN, Education.HIGHER));
    }

    @Test
    public void generateCollectionTest() {
        Assertions.assertNotNull(Main.generateCollection(count, names, fam));
    }

    @Test
    public void generateCollectionCountTest() {

        InitialData();

        Long result = 1L;

        Assertions.assertNotNull(Main.getCount(input));
        Assertions.assertEquals(result, Main.getCount(input));
    }

    @Test
    public void getManTest() {
        InitialData();

        Assertions.assertNotNull(Main.getMan(input));
        Assertions.assertEquals(Collections.EMPTY_LIST, Main.getMan(input));
    }

    @Test
    public void getWorkers() {

        InitialData();


        Assertions.assertNotNull(Main.getWorkers(input));
        Assertions.assertEquals(1, Main.getWorkers(input).size());
    }
}
