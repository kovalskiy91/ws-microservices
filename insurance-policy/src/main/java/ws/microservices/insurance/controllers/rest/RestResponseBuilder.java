package ws.microservices.insurance.controllers.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ws.microservices.insurance.models.Model;

import java.net.URI;
import java.util.List;

@Component
public class RestResponseBuilder {

    public <T extends Model> ResponseEntity<T> buildPostResponseFrom(T model) {
        final URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(model.getId())
                .toUri();
        return ResponseEntity
                .created(location)
                .body(model);
    }

    public <T extends Model> ResponseEntity<T> buildGetResponseFrom(T model) {
        return ResponseEntity.ok(model);
    }

    public <T extends Model> ResponseEntity<List<T>> buildGetResponseFrom(List<T> model) {
        return ResponseEntity.ok(model);
    }

}
