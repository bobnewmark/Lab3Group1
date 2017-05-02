package repository;

import entity.Object;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by said on 02.05.2017.
 */
public interface ObjectRepository extends JpaRepository<Object, Integer> {
}
