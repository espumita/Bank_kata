package feature;

import bankkata.Account;
import bankkata.Console;
import bankkata.StatementPrinter;
import bankkata.TransactionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PrintStatementFeature {

    @Mock Console console;
    private Account account;

    @Before
    public void initialize(){
        TransactionRepository transactionRepository = new TransactionRepository();
        StatementPrinter statementPrinter = new StatementPrinter();
        account = new Account(transactionRepository, statementPrinter);
    }

    @Test public void
    print_statement_containing_all_transactions() {
        account.deposit(1000);
        account.withdraw(100);
        account.deposit(500);
        
        account.printStatement();

        InOrder inOrder = inOrder(console);
        inOrder.verify(console).printLine("DATE | AMOUNT | BALANCE");
        inOrder.verify(console).printLine("10/04/2014 | 500.00 | 1400.00");
        inOrder.verify(console).printLine("02/04/2014 | -100.00 | 900.00");
        inOrder.verify(console).printLine("01/04/2014 | 1000.00 | 1000.00");
    }


}