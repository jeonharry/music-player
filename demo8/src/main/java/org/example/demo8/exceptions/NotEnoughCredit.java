package org.example.demo8.exceptions;

public class NotEnoughCredit extends Exception
{
    public NotEnoughCredit(String message)
    {
        super("your credit isn't enough "+message);
    }
    public NotEnoughCredit()
    {
        this("");
    }
}
