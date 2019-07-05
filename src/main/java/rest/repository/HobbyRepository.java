package rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import rest.model.Hobby;

import java.util.List;

@Transactional
public interface HobbyRepository extends JpaRepository<Hobby, Long> {

    List<Hobby> findHobbiesByPersonId(Long personID);
}
