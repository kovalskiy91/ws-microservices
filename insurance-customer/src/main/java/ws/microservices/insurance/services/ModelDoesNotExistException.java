package ws.microservices.insurance.services;

public class ModelDoesNotExistException extends RuntimeException {
    public ModelDoesNotExistException(String entity, Long id) {
        super(String.format("%s with id %d does not exist", entity, id));
    }
}
