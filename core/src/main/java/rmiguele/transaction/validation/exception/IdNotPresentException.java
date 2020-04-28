package rmiguele.transaction.validation.exception;

public class IdNotPresentException extends RuntimeException {
    public IdNotPresentException(Class<?> clazz) {
        super(String.format("@Id annotation not set for class \"%s\"", clazz.getSimpleName()));
    }
}