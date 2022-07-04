package za.co.wethinkcode.weshare;

import io.javalin.Javalin;

public class ExpensesServer {
    private final Javalin server;
    private final int DEFAULT_PORT = 8080;

    public ExpensesServer() {
        server = Javalin.create();
        server.post("/expenses", ExpenseController::createExpense);
        server.get("/expenses", ExpenseController::getExpense);
        server.get("/person/{email=value}", ExpenseController::getPerson);
        server.get("/expenses/{id}", ExpenseController::getExpensesById);
    }

    public void start(int port) {
        int listen_port = port > 0 ? port : DEFAULT_PORT;
        this.server.start(listen_port);
    }

    public void stop() {
        this.server.stop();
    }

    public static void main(String[] args) {
        ExpensesServer expenseServer = new ExpensesServer();
        expenseServer.start(0);
    }
}
