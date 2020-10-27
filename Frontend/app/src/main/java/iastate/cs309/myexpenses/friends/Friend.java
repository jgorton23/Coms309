package iastate.cs309.myexpenses.friends;

import java.math.BigDecimal;

public class Friend {
    public final String id;
    public final BigDecimal amount;
    public final String content;
    public final String date;

    public Friend(String id, String content, BigDecimal amount, String date) {
        this.id = id;
        this.content = content;
        this.amount = amount;
        this.date = date;
    }
}
