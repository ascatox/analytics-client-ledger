package eng.it.analytics.client.exception;


public class SmartClientException extends Exception {

    public SmartClientException() {
        super();
    }

    public SmartClientException(String message) {
        super(message);
    }

    public SmartClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public SmartClientException(Throwable cause) {
        super(cause);
    }

    protected SmartClientException(String message, Throwable cause, boolean enableSuppression, boolean
            writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }



}

