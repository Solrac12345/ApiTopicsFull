package med.voil.api.Topicos;

import jakarta.validation.constraints.NotNull;

public record DatosRespuestaTopicos(Long id,String titulo,String mensaje,String fecha_de_creacion,Status status_del_topico,String autor,String curso, Boolean status) {
}
