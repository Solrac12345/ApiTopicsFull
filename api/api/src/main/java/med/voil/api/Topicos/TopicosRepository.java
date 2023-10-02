package med.voil.api.Topicos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicosRepository extends JpaRepository<Topicos, Long> {

    Page<Topicos> findByStatusTrue(Pageable paginacion);
}
