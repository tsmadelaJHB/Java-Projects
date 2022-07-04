package za.co.wethinkcode.claims;

import com.google.common.collect.ImmutableList;
import io.javalin.http.Context;
import io.javalin.http.HttpCode;
import org.jetbrains.annotations.NotNull;
import za.co.wethinkcode.claims.app.db.DataRepository;
import za.co.wethinkcode.claims.app.model.Claim;

import java.time.LocalDate;
import org.json.JSONObject;
import java.util.*;

public class ClaimsApiController {
    //works
    public static final String CLAIM_PATH = "/claim/{id}";
    // POST works, PUT does not work yet
    public static final String CLAIM = "/claim/";
    // Works
    public static final String CLAIM_FROM_PATH = "/claims/from/{email}";
    // Works
    public static final String CLAIM_BY_PATH = "/claims/by/{email}";
    // Works
    public static final String TOTAL_CLAIM_FROM_PATH = "/totalclaimvalue/from/{email}";
    //Works
    public static final String TOTAL_CLAIM_BY_PATH = "/totalclaimvalue/by/{email}";


    public static void create(@NotNull Context context) {
        ClaimViewModel claimViewModel = context.bodyAsClass(ClaimViewModel.class);
        LocalDate dueDate = LocalDate.parse(claimViewModel.getDueDate());
        String email = "herman@student.wethinkcode.co.za";
        Claim claim = new Claim(claimViewModel.getId(),dueDate, email,claimViewModel.getClaimFromWho(),claimViewModel.getClaimAmount());
        DataRepository dataRepository = DataRepository.getInstance();
        dataRepository.addClaim(claim);

        context.status(HttpCode.OK);
        context.json(claim);
    }

    public static void getClaim(@NotNull Context context){
        UUID claimId = UUID.fromString(Objects.requireNonNull(context.pathParam("id")));
        Optional<Claim> maybeClaim = DataRepository.getInstance().getClaim(claimId);

        if (maybeClaim.isEmpty()) {
            context.status(HttpCode.NOT_FOUND);
            context.result("Invalid claim");
            return;
        }
        Claim claim = maybeClaim.get();
        context.json(claim);
    }


    public static void update(@NotNull Context context){

        UUID expenseId = UUID.fromString(context.queryParam("id"));
        JSONObject message = new JSONObject(context.body());
        UUID claimId = UUID.fromString(message.getString("id"));
        Claim claim = new Claim(expenseId,claimId,LocalDate.parse(message.getString("dueDate")),message.getString("claimedBy"),message.getString("claimedFrom"),Double.parseDouble(message.getString("amount")));
        DataRepository.getInstance().updateClaim(claim);
        context.status(HttpCode.OK);
        context.json(claim);


    }

    public static void claimsFrom(@NotNull Context context) {
        String email = context.pathParam("email");
        Boolean settled = Boolean.parseBoolean(context.queryParam("settled"));
        List<Claim> maybeClaims;
        if (settled) {
            maybeClaims = DataRepository.getInstance().findSettledClaimsClaimedFrom(email);
        }else {
            maybeClaims = DataRepository.getInstance().findUnsettledClaimsClaimedFrom(email);
        }
        context.status(HttpCode.OK);
        context.json(maybeClaims);
    }

    public static void claimsBy(@NotNull Context context) {
        String email = context.pathParam("email");
        Boolean settled = Boolean.parseBoolean(context.queryParam("settled"));
        ImmutableList<Claim> claims = DataRepository.getInstance().getClaimsFrom(email,false);
        List maybeClaims = new ArrayList();
        for (int i = 0; i < claims.size(); i++) {
            if (claims.get(i).isSettled() == settled){
                maybeClaims.add(claims.get(i));
            }
        }

        context.status(HttpCode.OK);
        context.json(maybeClaims);
    }

    public static void totalClaimsBy(@NotNull Context context) {
        String email = context.pathParam("email");
        double claims = DataRepository.getInstance().getTotalUnsettledClaimsClaimedBy(email);
        context.status(HttpCode.OK);
        context.json(claims);

    }

    public static void totalClaimsFrom(@NotNull Context context) {
        String email = context.pathParam("email");
        double claims = DataRepository.getInstance().getTotalUnsettledClaimsClaimedFrom(email);
        context.status(HttpCode.OK);
        context.json(claims);
    }


}
