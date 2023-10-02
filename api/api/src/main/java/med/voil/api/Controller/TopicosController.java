package med.voil.api.Controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.Entity;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voil.api.Topicos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicosController {

    @Autowired
    private TopicosRepository topicosRepository;
    @PostMapping
    public ResponseEntity<DatosRespuestaTopicos> registarTopico(@RequestBody @Valid DatosregistroTopicos datosregistroTopicos, UriComponentsBuilder uriComponentsBuilder){

       Topicos topicos = topicosRepository.save(new Topicos(datosregistroTopicos));
       DatosRespuestaTopicos datosRespuestaTopicos = new DatosRespuestaTopicos(topicos.getId(), topicos.getTitulo(), topicos.getMensaje(),
               topicos.getFecha_de_creacion().toString(),topicos.getStatus_del_topico(),
               topicos.getAutor(), topicos.getCurso(), topicos.getStatus());
       URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topicos.getId()).toUri();
       return ResponseEntity.created(url).body(datosRespuestaTopicos);
    }

    @GetMapping
    public ResponseEntity<Page<Topicos>> listadoTopicos(@PageableDefault() Pageable paginacion){
        //return  topicosRepository.findAll(paginacion);
        return ResponseEntity.ok(topicosRepository.findByStatusTrue(paginacion));
    }

    @GetMapping("/{id}")
    /*public Optional<Topicos> getByIdTopicos(@PathVariable Long id){
        return topicosRepository.findById(id);
    }*/
    public ResponseEntity retornaDatosTopicos(@PathVariable Long id){
        Topicos topicos = topicosRepository.getReferenceById(id);
        var datosTopicos = new DatosRespuestaTopicos(topicos.getId(), topicos.getTitulo(), topicos.getMensaje(),
                topicos.getFecha_de_creacion().toString(),topicos.getStatus_del_topico(),
                topicos.getAutor(), topicos.getCurso(), topicos.getStatus());
        return ResponseEntity.ok(datosTopicos);
    }


    @PutMapping
    @Transactional
    public ResponseEntity actualizaTopico(@RequestBody @Valid ActualizaTopico actualizatopico){
        Topicos topicos = topicosRepository.getReferenceById(actualizatopico.id());
        topicos.actualizarDatos(actualizatopico);
        return ResponseEntity.ok(new DatosRespuestaTopicos(topicos.getId(), topicos.getTitulo(), topicos.getMensaje(),
                topicos.getFecha_de_creacion().toString(),topicos.getStatus_del_topico(),
                topicos.getAutor(), topicos.getCurso(), topicos.getStatus()));
    }
    //DELETE logic
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id){
        Topicos topico = topicosRepository.getReferenceById(id);
        topico.desactivarTopico();
        return ResponseEntity.noContent().build();
    }
    /*//DELETE DATABASE
    @DeleteMapping("/{id}")
    @Transactional
    public void eliminarTopico(@PathVariable Long id){
        Topicos topico = topicosRepository.getReferenceById(id);
        topicosRepository.delete(topico);
    }*/
}
