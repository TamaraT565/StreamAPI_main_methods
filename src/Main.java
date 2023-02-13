import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        long minor_people = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();
        System.out.println("Количество несовершеннолетних жителей равно - " + minor_people);

        System.out.println("----------------------------------------------");
       List<String> recruits = persons.stream()
               .filter(person -> person.getSex() == Sex.MAN)
               .filter(person -> person.getAge() >= 18)
               .filter(person ->person.getAge() <= 27)
               .map(person -> person.getFamily().toString())
               .collect(Collectors.toList());
        System.out.println("Список фамилий мужчин призывников: " + recruits);

        System.out.println("----------------------------------------------");

        List<String> workers = persons.stream()
                .filter(person -> person.getAge()>= 18)
                .filter(Person ->Person.getSex() == Sex.WOMAN? Person.getAge() < 60: Person.getAge() < 65)
                .filter(person -> person.getEducation() == Education.HIGHER)
                .sorted(Comparator.comparing(Person::getFamily))
                .map(person -> person.getFamily().toString())
                .collect(Collectors.toList());
        System.out.println("Список работоспособного населения с высшим образованием" + workers);
    }
}