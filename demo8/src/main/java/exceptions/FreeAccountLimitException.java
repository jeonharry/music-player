package exceptions;

public class FreeAccountLimitException extends Exception
{
    public FreeAccountLimitException(String message)
    {
        super("free account limit -"+message);
    }
    public FreeAccountLimitException()
    {
        this("");
    }
}
