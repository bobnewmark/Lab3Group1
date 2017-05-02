package repository;

import entity.Reference;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by said on 02.05.2017.
 */
public interface ReferenceRepository extends JpaRepository<Reference, Integer> {
}
