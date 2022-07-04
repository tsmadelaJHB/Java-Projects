package za.co.wethinkcode.claims.app.model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * I record the details of a claim of an expense against a person.
 * The claim might be for smaller amount than the expense amount, if split between people.
 * <p>
 * If a person logs an expense against themselves, then that expense is in confirmed state,
 * and a Claim needs to be created where `claimedBy` and `claimedFrom` is the same person.
 * <p>
 * Note that my instances are immutable.
 */

// For now we use (originalExpense, claimedBy, claimedFrom) as unique fields.
// TODO: also introduce ID rather for Claim?

public class Claim extends AbstractModel {

    private final UUID expenseId;
    private final LocalDate dueDate;
    private final String  claimedBy;
    private final String claimedFrom;
    private final Double amount;
    private boolean settled;


    public Claim(UUID expenseId, LocalDate dueDate, String claimedBy, String claimedFrom, Double amount) {
        super( UUID.randomUUID(), dueDate);
        this.expenseId = expenseId;
        this.dueDate = dueDate;
        this.claimedBy = claimedBy;
        this.claimedFrom = claimedFrom;
        this.amount = amount;
        this.settled = false;
    }

    public Claim(UUID expenseId, UUID claimId, LocalDate dueDate, String claimedBy, String claimedFrom, double amount) {
        super(claimId,dueDate);
        this.expenseId = expenseId;
        this.dueDate = dueDate;
        this.claimedBy = claimedBy;
        this.claimedFrom = claimedFrom;
        this.amount = amount;
        this.settled = false;
    }


    public UUID getExpenseId() {
        return expenseId;
    }

    public Double getAmount() {
        return amount;
    }

    public String getClaimedBy() {
        return claimedBy;
    }

    public String getClaimedFrom() {
        return claimedFrom;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public Settlement settleClaim(LocalDate settlementDate) {
        settled = true;
        return new Settlement(java.util.Optional.of(this), settlementDate);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Claim claim = (Claim) o;
        return getId().equals(claim.getId()) && claimedBy.equals(claim.claimedBy) && claimedFrom.equals(claim.claimedFrom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), claimedBy, claimedFrom);
    }

    @Override
    public String toString() {
        return "Claim{" +
                "originalExpense=" + expenseId +
                ", claimedBy='" + claimedBy + '\'' +
                ", claimedFrom='" + claimedFrom + '\'' +
                ", amount=" + amount +
                ", dueDate=" + dueDate +
                '}';
    }

    public boolean isSettled() {
        return settled;
    }
}