package family.tree.repository;

import family.tree.entity.Person;
import org.springframework.data.jpa.domain.Specification;

public class PersonSpecifications {

    public static Specification<Person> nameContains(String name) {
        return (root, query, cb) ->
                (name == null || name.isEmpty())
                        ? null
                        : cb.like(cb.lower(root.get("fullName")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<Person> birthDateEquals(String age) {
        return (root, query, cb) ->
                age == null
                        ? null
                        : cb.equal(root.get("birthDate"), age);
    }

    public static Specification<Person> genderEquals(String gender) {
        return (root, query, cb) ->
                (gender == null || gender.isEmpty())
                        ? null
                        : cb.equal(root.get("gender"), gender);
    }
}
