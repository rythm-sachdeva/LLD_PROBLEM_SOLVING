public class AuthenticatedState implements ATMState{

    private final ATMMachine atmachine;

    @Override
    public void insertCard(Card card)
    {
        System.out.println("Card Already Inserted");
    }

    @Override
    public void enterPin(String pin)
    {
        System.out.println("Already Authenticated");
    }

    @Override
    public void selectOption(String option){
        System.out.println("Option Selected: Withdrawl");
        atmMachine.setState(new DispenseState(atmMachine));
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
        return ATMStatus.AUTHENTICATED;
    }


}