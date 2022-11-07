package filtres;

import filters.Main;
import filters.classes.Person;
import filters.enums.Education;
import filters.enums.Gender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.object.IsCompatibleType.typeCompatibleWith;

public class HamcrestTest {

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
        //Assertions.assertNotNull(Main.generateCollection(count, names, fam));

        Collection<Person> coll = Main.generateCollection(count, names, fam);

        assertThat(null, not(Main.generateCollection(count, names, fam)));
        assertThat(coll, is(not(empty())));
    }

    @Test
    public void generateCollectionCountTest() {

        InitialData();

        Long result = 1L;

        //Assertions.assertNotNull(Main.getCount(input));
        //Assertions.assertEquals(result, Main.getCount(input));
        assertThat(result, is(equalTo(Main.getCount(input))));
    }

    @Test
    public void getManTest() {
        InitialData();

        //Assertions.assertNotNull(Main.getMan(input));
        //Assertions.assertEquals(Collections.EMPTY_LIST, Main.getMan(input));
        assertThat(Main.getMan(input), is(empty()));
    }

    @Test
    public void getWorkers() {

        InitialData();

        Collection<Person> coll = Main.getWorkers(input);

        //Assertions.assertNotNull(Main.getWorkers(input));
        //Assertions.assertEquals(1, Main.getWorkers(input).size());
        assertThat(coll, hasSize(1));
    }

    @Test
    public void checkDenis() {

        InitialData();

        Collection<Person> coll = Main.getWorkers(input);
        Person denis = new Person("Denis", "White", 45, Gender.MAN, Education.HIGHER);

        //Тест падает с ошибкой т.к. не смотря на то, что объект denis и объект
        //в коллекции имеют одинаковые значения, они не являются одним и тем же объектом, поскольку
        //они имеют разные ссылки
        assertThat(coll, hasItem(denis));
    }

    @Test
    public void isObject() {

        InitialData();

        Collection<Person> coll = Main.getWorkers(input);

        assertThat(coll.stream().findFirst().getClass(), typeCompatibleWith(Object.class));
    }
}
