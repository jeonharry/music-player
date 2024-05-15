package exceptions;

public class InvalidFormatException extends Exception
{
    public InvalidFormatException(String message)
    {
        super("Invalid format -"+message);
    }
    public InvalidFormatException()
    {
        this("");
    }
}
