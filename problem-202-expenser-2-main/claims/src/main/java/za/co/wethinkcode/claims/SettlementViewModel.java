package za.co.wethinkcode.claims;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.UUID;

public class SettlementViewModel {

    private String settlementDate;
    private UUID id;

    public LocalDate dueDateAsLocalDate() {
        try {
            return LocalDate.parse(this.settlementDate);
        } catch (DateTimeException e) {
            throw new RuntimeException("Could not parse dueDate for Claim: [" + this.settlementDate + "]");
        }
    }

    public String getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(String settlementDate) {
        this.settlementDate = settlementDate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
