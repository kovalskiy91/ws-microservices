package ws.microservices.insurance.services;

public class ModelNotFoundException extends RuntimeException {
    public ModelNotFoundException(String entity, Long id) {
        super(String.format("%s with id %d not found", entity, id));
    }
}
