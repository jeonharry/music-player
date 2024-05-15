package exceptions;

public class FailedLoginException extends Exception
{
    public FailedLoginException(String message)
    {
        super("login failed -"+message);
    }
    public FailedLoginException()
    {
        this("");
    }
}
