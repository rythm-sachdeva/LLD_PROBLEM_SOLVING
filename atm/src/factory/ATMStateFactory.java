public class ATMStateFactory {

    public static ATMState getState(ATMStatus status,ATMMachine machine)
    {
        return switch (status){
            case IDLE -> new IdleState(machine);
            case CARD_INSERTED -> new CardInsertedState(machine);
            case Authenticated -> new AuthenticatedState(machine);
            case DISPENSE_CASH -> new DispenseCashState(machine);
            default -> throw new IllegalStateExeption("Unknown ATM Status: " + status);
        };
        
    }

    
}
