package filters;

import filters.classes.Person;
import filters.enums.Education;
import filters.enums.Gender;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = generateCollection(5231L, names, families);

        //Найти количество несовершеннолетних (т.е. людей младше 18 лет).
        System.out.println("Количество несовершеннолетних: " + getCount(persons));

        //Получить список фамилий призывников (т.е. мужчин от 18 и до 27 лет).
        System.out.println("Фамилии призывников: " + getMan(persons));

        /*Получить отсортированный по фамилии список потенциально работоспособных людей с высшим образованием в выборке
        (т.е. людей с высшим образованием от 18 до 60 лет для женщин и до 65 лет для мужчин).*/
        System.out.println(getWorkers(persons));
    }

    public static Collection<Person> generateCollection(Long count, List<String> names, List<String> families) {
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Gender.values()[new Random().nextInt(Gender.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        return persons;
    }

    public static Long getCount(Collection<Person> persons) {
        return persons.stream()
                .filter(x -> x.getAge() < 18)
                .count();
    }

    public static List<String> getMan(Collection<Person> persons) {
        return persons.stream()
                .filter(x -> x.getGender().equals(Gender.MAN))
                .filter(x -> x.getAge() >= 18 && x.getAge() <= 27)
                .map(x -> x.getFamily())
                .collect(Collectors.toList());
    }

    public static Collection<Person> getWorkers(Collection<Person> persons) {
         return persons.stream()
                .filter(x -> x.getEducation().equals(Education.HIGHER))
                .filter(x -> (x.getGender().equals(Gender.WOMAN) && x.getAge() >= 18 && x.getAge() <= 60)
                        || (x.getGender().equals(Gender.MAN) && x.getAge() >= 18 && x.getAge() <= 65))
                .sorted(Comparator.comparing(x -> x.getFamily()))
                .collect(Collectors.toList());
    }
}