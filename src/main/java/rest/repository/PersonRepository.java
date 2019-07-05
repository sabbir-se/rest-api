package rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import rest.model.Person;

@Transactional
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query(value = "SELECT * FROM Person p WHERE p.first_name =?1 AND p.last_name =?2", nativeQuery = true)
    Person findPersonByFirstNameAndLastName(String first_name, String last_name);
}
