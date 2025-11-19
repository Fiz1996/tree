package family.tree.repository;

import family.tree.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> , JpaSpecificationExecutor<Person> {
    List<Person> findByMotherId(Long motherId);
    List<Person> findByFatherId(Long fatherId);
    List<Person> findByFullNameContainingIgnoreCase(String name);

}