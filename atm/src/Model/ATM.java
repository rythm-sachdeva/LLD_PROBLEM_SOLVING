

import lombok.Getter;
import lombok.Setter;
import src.enums.ATMStatus;


@Getter
public class ATM{
    private final String id;
    @Setter private ATMStatus status;
    @Setter private double cashAvailaible;
    @Setter private int twoThousandCount;
    @Setter private int fiveHundredCount;
    @Sette private int oneHundredCount;


    public ATM(String id,int twoThousandCount,int fiveHundredCount,int oneHundredCount)
    {
        this.id = id;
        this.twoThousandCount = twoThousandCount;
        this.fiveHundredCount = fiveHundredCount;
        this.oneHundredCount = oneHundredCount;
        this.status = ATMStatus.IDLE;
        this.cashAvailaible = 2000*twoThousandCount + 500*fiveHundredCount + 100*oneHundredCount;         
    }

    
}