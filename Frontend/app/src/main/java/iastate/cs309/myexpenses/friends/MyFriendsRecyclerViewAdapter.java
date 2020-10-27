package iastate.cs309.myexpenses.friends;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import iastate.cs309.myexpenses.Expense;
import iastate.cs309.myexpenses.ItemDetailActivity;
import iastate.cs309.myexpenses.R;
import iastate.cs309.myexpenses.friends.dummy.DummyContent.DummyItem;

import java.text.BreakIterator;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyFriendsRecyclerViewAdapter extends RecyclerView.Adapter<MyFriendsRecyclerViewAdapter.ViewHolder> {

    private final List<Friend> mValues;
    private FragmentActivity activity;
    private boolean mTwoPane;


    public MyFriendsRecyclerViewAdapter(List<Friend> items, FragmentActivity activity) {
        mValues = items;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_friends, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).content);
//        holder.mAmountView.setText("$" + mValues.get(position).amount.toString());
//        holder.mCategory.setText(mValues.get(position).date);


    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView mIdView;
        final TextView mContentView;
        final TextView mAmountView;
        public TextView mCategory;

        public ViewHolder(View view) {
            super(view);
            mIdView = (TextView) view.findViewById(R.id.item_number);
            mAmountView = (TextView) view.findViewById(R.id.amount);
            mContentView = (TextView) view.findViewById(R.id.content);
        }
    }
}