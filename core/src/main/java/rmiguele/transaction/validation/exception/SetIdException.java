package rmiguele.transaction.validation.exception;

public class SetIdException extends RuntimeException {

    public SetIdException(Class<?> clazz, Exception e) {
        super(String.format("Could not set the id for class \"%s\".", clazz.getSimpleName()), e);
    }
}
