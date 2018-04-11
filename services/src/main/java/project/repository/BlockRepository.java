package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.entity.BlockEntity;

@Repository
public interface BlockRepository extends JpaRepository<BlockEntity, Long> {
}
