package mk.ukim.finki.domashna2_191124.repository;

import mk.ukim.finki.domashna2_191124.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}
