


@AllArgsConstructor
public class CardInsertedState implements ATMStatus {
private final ATMMachine atmMachine;

@Override
public void insertCard(Card card)
{
    System.out.println("Card already inserted.");
}

@Override
public void enterPin(String pin)
{
    if(atmMachine.getCurrentCard().getPin().equals(pin))
    {
        System.out.println("PIN correct. Authenticated.");
        atmMachine.setState(new AuthenticatedState(atmMachine));
    }else
    {
        System.out.println("Invalid Pin");
    }

}

@Override
public void selectOption(String option)
{
    System.out.println("Enter PIN first.");
}

@Override
public void dispenseCash(int amount)
{
    System.out.println("Enter PIN before dispensing.");
}


    @Override
    public void ejectCard()
    {
        atmMachine.setCurrentCard(null);
        System.out.println("Card ejected.");
        atmMachine.setState(new IdleState(atmMachine));
    
    }


    @Override
    public ATMStatus getStatus()
    {
        return ATMStatus.CARD_INSERTED;
    }

}