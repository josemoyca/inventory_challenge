package demo.retail.inventory.handlers.exception;

public class ResourceNotFoundException extends Exception {
    private static final long serialVersionUID = -7440354430739560621L;

    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
    public ResourceNotFoundException(Long id) {
        super("No se encontró el post con el ID: " + id);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceNotFoundException(Throwable cause) {
        super(cause);
    }

    public static String buildExceptionMessage(Class<?> clazz, Integer id) {
        final String messageTemplate = "Resource of type %s with id = %d not found.";

        return String.format(messageTemplate, clazz.getSimpleName(), id);
    }

    public static String buildExceptionMessage(Class<?> clazz, String name, Integer id) {
        final String messageTemplate = "Resource of type %s with %s = %d not found.";

        return String.format(messageTemplate, clazz.getSimpleName(), name, id);
    }

    public static String buildExceptionMessage(Class<?> clazz, String name, String value) {
        final String messageTemplate = "Resource of type %s with %s = %s not found.";

        return String.format(messageTemplate, clazz.getSimpleName(), name, value);
    }

}
