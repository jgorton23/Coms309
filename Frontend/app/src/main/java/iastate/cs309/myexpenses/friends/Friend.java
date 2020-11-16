package iastate.cs309.myexpenses.friends;

import java.math.BigDecimal;

public class Friend {
    public final String id;
    public final String content;
    public final String budget;

    public Friend(String id, String content, String budget) {
        this.id = id;
        this.content = content;
        this.budget = budget;
    }
}
