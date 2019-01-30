package rs.ac.uns.ftn.informatika.udd.vezbe01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.informatika.udd.vezbe01.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
