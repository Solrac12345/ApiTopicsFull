package med.voil.api.Topicos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosregistroTopicos(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotNull
        Status status_del_topico,
        @NotBlank
        String autor,
        @NotBlank
        String curso) {
}
