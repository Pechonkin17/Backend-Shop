package NULP.shopOnline.exception;

public class BucketNotFoundException extends RuntimeException{
    public BucketNotFoundException(String message) {
        super(message);
    }
}