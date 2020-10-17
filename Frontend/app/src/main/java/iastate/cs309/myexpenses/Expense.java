package iastate.cs309.myexpenses;

import java.math.BigDecimal;

/**
 * A dummy item representing a piece of content.
 */
public class Expense {
    public final String id;
    public final String content;
    public final String details;
    public final BigDecimal amount;
    public final String category;
    public final String date;

    public Expense(String id, String content, String details, BigDecimal amount, String category, String date) {
        this.id = id;
        this.content = content;
        this.details = details;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    @Override
    public String toString() {
        return content;
    }
}
