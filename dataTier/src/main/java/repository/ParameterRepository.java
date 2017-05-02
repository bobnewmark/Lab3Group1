package repository;

import entity.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by said on 02.05.2017.
 */
public interface ParameterRepository extends JpaRepository<Parameter, Integer> {
}
