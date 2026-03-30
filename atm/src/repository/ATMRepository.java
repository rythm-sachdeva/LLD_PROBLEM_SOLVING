import src.enums.ATMStatus;

import src.Model.ATM;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class ATMRepository {
    private final Map<String,ATM> atms = new HashMap<>();

    public void save(ATM atm)
    {
        atms.put(atm.getId(),atm);
    }

    public Optional<ATM> getById(String id)
    {
        return Optional.ofNullable(atms.get(id));
    }

    public void updateAtmStatusById(String id,ATMStatus newStatus)
    {
        atms.get(id).setStatus(newStatus);
    }
}
