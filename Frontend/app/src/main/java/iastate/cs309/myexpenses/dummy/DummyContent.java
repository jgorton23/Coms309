package iastate.cs309.myexpenses.dummy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import iastate.cs309.myexpenses.Category;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static DummyItem createDummyItem(int position) {
        int randomAmount = ThreadLocalRandom.current().nextInt(5, 50);
        int categoryIndex = ThreadLocalRandom.current().nextInt(0, Category.values().length);
        Category category = Category.values()[categoryIndex];
        return new DummyItem(String.valueOf(position), "Item " + position, makeDetails(position), new BigDecimal(randomAmount), category);
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Expense: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public final String id;
        public final String content;
        public final String details;
        public final BigDecimal amount;
        public final Category category;

        public DummyItem(String id, String content, String details, BigDecimal amount, Category category) {
            this.id = id;
            this.content = content;
            this.details = details;
            this.amount = amount;
            this.category = category;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}