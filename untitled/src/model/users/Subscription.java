package model.users;

public enum Subscription
{
    THIRTY_DAYS(5),SIXTY_DAYS(9),ONEHEIGHTY_DAYS(14);
    private final int price;
    Subscription(int price)
    {
        this.price=price;
    }
    public int getPrice()
    {
        return this.price;
    }
}
