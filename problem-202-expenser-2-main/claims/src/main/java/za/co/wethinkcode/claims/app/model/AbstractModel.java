package za.co.wethinkcode.claims.app.model;

import java.time.LocalDate;
import java.util.UUID;

import static com.google.common.base.Preconditions.checkNotNull;

public class AbstractModel {

    protected final UUID id;
    protected final LocalDate date;

    public AbstractModel(UUID originalExpense, LocalDate date ){
        this.id = checkNotNull( originalExpense );
        this.date = date;
    }

    public UUID getId() {
        return id;
    }

    public LocalDate getDate() {return date;}
}