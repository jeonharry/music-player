package exceptions;

public class UserNotFoundException extends FailedLoginException
{
    public UserNotFoundException(String message)
    {
        super("username doesn't exist "+message);
    }
    public UserNotFoundException()
    {
        this("");
    }
}
