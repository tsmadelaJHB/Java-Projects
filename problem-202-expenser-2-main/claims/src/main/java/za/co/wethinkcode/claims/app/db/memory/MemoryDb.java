package za.co.wethinkcode.claims.app.db.memory;

import com.google.common.collect.ImmutableList;
import za.co.wethinkcode.claims.app.db.DataRepository;
import za.co.wethinkcode.claims.app.model.Claim;
import za.co.wethinkcode.claims.app.model.Settlement;

import java.util.*;
import java.util.stream.Collectors;

public class MemoryDb implements DataRepository {

    private final Set<Claim> claims = new HashSet<>();
    private final Set<Settlement> settlements = new HashSet<>();

    volatile long lastPersonId = 0L;

    public MemoryDb() {
        setupTestData();
    }


    //<editor-fold desc="Claims">

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableList<Claim> getClaims() {
        return ImmutableList.copyOf(claims);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImmutableList<Claim> getClaimsBy(String claimedBy, boolean onlyUnsettled) {
        return claims.stream().filter(claim -> claim.getClaimedBy().equals(claimedBy)
                        && (!onlyUnsettled || !claim.isSettled()))
                .sorted(Comparator.comparing(Claim::getDueDate))
                .collect(ImmutableList.toImmutableList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Claim> getClaim(UUID id) {
        return claims.stream()
                .filter(Claim -> Claim.getId().equals(id))
                .findFirst();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Claim addClaim(Claim claim) {
        claims.add(claim);
        return claim;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeClaim(Claim claim) {
        claims.remove(claim);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateClaim(Claim updatedClaim) {
        Optional<Claim> ClaimOpt = claims.stream().filter(Claim -> Claim.equals(updatedClaim)).findFirst();
        if (ClaimOpt.isPresent()) {
            claims.remove(ClaimOpt.get());
            claims.add(updatedClaim);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Claim> findUnsettledClaimsClaimedBy(String email) {
        return claims.stream().filter(Claim ->
                        Claim.getClaimedBy().equalsIgnoreCase(email) &&
                                !Claim.isSettled())
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Claim> findSettledClaimsClaimedBy(String email) {
        boolean onlyUnsettled = true;
         return claims.stream()
                .filter(claim -> claim.getClaimedFrom().equals(email) && (onlyUnsettled || !claim.isSettled()))
                .sorted(Comparator.comparing(Claim::getDueDate))
                .collect(ImmutableList.toImmutableList());
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public double getTotalUnsettledClaimsClaimedBy(String email) {
        return findUnsettledClaimsClaimedBy(email).stream().mapToDouble(Claim::getAmount).sum();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getTotalUnsettledClaimsClaimedFrom(String email) {
        return findUnsettledClaimsClaimedFrom(email).stream().mapToDouble(Claim::getAmount).sum();
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public ImmutableList<Claim> getClaimsFrom(String claimedFrom, boolean onlyUnsettled) {
            return claims.stream()
                    .filter(claim -> claim.getClaimedFrom().equals(claimedFrom) && (!onlyUnsettled || !claim.isSettled()))
                    .sorted(Comparator.comparing(Claim::getDueDate))
                    .collect(ImmutableList.toImmutableList());
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public List<Claim> findUnsettledClaimsClaimedFrom(String email) {
        return getClaimsFrom(email, true);
    }


    /**
     * This works do no touch
     * @param email The Person whose unsettled Claims we want. May not be {@literal null}.
     * @return
     */
    @Override
    public List<Claim> findSettledClaimsClaimedFrom(String email) {
        return claims.stream().filter(Claim ->
                        Claim.getClaimedFrom().equalsIgnoreCase(email) &&
                                Claim.isSettled())
                .collect(Collectors.toList());

    }


    //</editor-fold>

    //<editor-fold desc="Settlements">

    /**
     * {@inheritDoc}
     */
    @Override
    public Settlement addSettlement(Settlement settlement) {
        settlements.add(settlement);
//        expenses.add(settlement.getSettlementDate());
        return settlement;
    }


    @Override
    public void dropClaims() {
        claims.clear();
    }

    @Override
    public void dropSettlements() {
        settlements.clear();
    }
    //</editor-fold>

    //<editor-fold desc="Helpers">

    /**
     * Answer the next available ID for a Person.
     * <p>
     * Incrementing a {@code long} value has to be synchronized because it is not an atomic
     * operation. See the Java Language Specification (§17.7 in Java SE17 Ed.) for details.
     *
     * @return The new ID.
     */
//    private long nextPersonId() {
//        synchronized (this) {
//            return ++lastPersonId;
//        }
//    }
    //</editor-fold>

    //<editor-fold desc="Test Data">
//    private void setupTestData() {
//        Person herman = addPerson(new Person("herman@wethinkcode.co.za"));
//        Person mike = addPerson(new Person("mike@wethinkcode.co.za"));
//        addPerson(new Person("sett@wethinkcode.co.za"));
//
//        /// herman's expenses
//        addExpense(new Expense(100.00, LocalDate.of(2021, 10, 12), "Airtime", herman));
//        addExpense(new Expense(35.00, LocalDate.of(2021, 10, 15), "Uber", herman));
//        Expense braai = addExpense(new Expense(400.00, LocalDate.of(2021, 9, 28), "Braai", herman));
//
//        // herman claims from mike
//        addClaim(braai.createClaim(mike, 200.0, LocalDate.of(2021, 11, 1)));
//
//        //mikes expenses
//        Expense beers = addExpense(new Expense(420.00, LocalDate.of(2021, 9, 30), "Beers", mike));
//
//        // mike claim from herman
//        addClaim(beers.createClaim(herman, 200.00, LocalDate.of(2021, 11, 1)));
//    }

    private void setupTestData() {
        String hermanEmail = "herman@wethinkcode.co.za";
        String mikeEmail = "mike@wethinkcode.co.za";
        String settEmail = "sett@wethinkcode.co.za";

        UUID expenseUuid = UUID.randomUUID();


        // herman claims from mike

        //addClaim(hermanEmail, createClaim);

        //mikes expenses
//        Expense beers = addExpense(new Expense(420.00, LocalDate.of(2021, 9, 30), "Beers", mike));

        // mike claim from herman
//        addClaim(createClaim(herman, 200.00, LocalDate.of(2021, 11, 1)));
    }
    //</editor-fold>
}