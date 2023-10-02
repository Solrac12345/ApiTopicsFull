package med.voil.api.Topicos;

public record DatoslistTopicos(String titulo, String mensaje, Status status_del_topic, String autor, String curso) {

    public DatoslistTopicos(Topicos topicos){
        this(topicos.getTitulo(), topicos.getMensaje(), topicos.getStatus_del_topico(), topicos.getAutor(), topicos.getCurso());
    }
}
