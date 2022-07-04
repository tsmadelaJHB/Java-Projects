package za.co.wethinkcode.claims.app.model;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static com.google.common.base.Preconditions.checkNotNull;

public class Settlement extends AbstractModel {


    private final Optional<Claim> claim;
    private final LocalDate settlementDate;

    Settlement(Optional<Claim> claim, LocalDate settlementDate){
        super( UUID.randomUUID(), settlementDate );
        this.claim = claim;
        this.settlementDate = checkNotNull( settlementDate );
    }

    public LocalDate getSettlementDate() {
        return settlementDate;
    }

    public Optional<Claim> getClaim() {
        return claim;
    }
}