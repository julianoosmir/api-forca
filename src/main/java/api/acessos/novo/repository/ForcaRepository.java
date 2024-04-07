package api.acessos.novo.repository;

import api.acessos.novo.entity.Forca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForcaRepository extends JpaRepository<Forca, Long> {

}
