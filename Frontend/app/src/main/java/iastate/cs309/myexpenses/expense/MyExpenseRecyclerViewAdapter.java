package iastate.cs309.myexpenses.expense;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import iastate.cs309.myexpenses.Expense;
import iastate.cs309.myexpenses.R;
import iastate.cs309.myexpenses.expense.dummy.DummyContent.DummyItem;
import iastate.cs309.myexpenses.ItemDetailActivity;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyExpenseRecyclerViewAdapter extends RecyclerView.Adapter<MyExpenseRecyclerViewAdapter.ViewHolder> {

    private final List<Expense> mValues;
    private boolean mTwoPane;
    private FragmentActivity activity;

    public MyExpenseRecyclerViewAdapter(List<Expense> items, FragmentActivity activity) {
        mValues = items;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_expense, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).content);
        holder.mAmountView.setText("$" + mValues.get(position).amount.toString());
        holder.mCategory.setText(mValues.get(position).category.toString());

        holder.itemView.setTag(mValues.get(position));
        holder.itemView.setOnClickListener(mOnClickListener);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView mIdView;
        final TextView mContentView;
        final TextView mAmountView;
        final TextView mCategory;

        ViewHolder(View view) {
            super(view);
            mIdView = (TextView) view.findViewById(R.id.id_text);
            mContentView = (TextView) view.findViewById(R.id.content);
            mAmountView = (TextView) view.findViewById(R.id.amount);
            mCategory = (TextView) view.findViewById(R.id.category);
        }
    }
    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Expense item = (Expense) view.getTag();
            if (mTwoPane) {
//                Bundle arguments = new Bundle();
//                arguments.putString(ItemDetailFragment.ARG_ITEM_ID, item.id);
//                ItemDetailFragment fragment = new ItemDetailFragment();
//                fragment.setArguments(arguments);
//                mParentActivity.getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.item_detail_container, fragment)
//                        .commit();
            } else {
                Context context = view.getContext();
                Intent intent = new Intent(context, ItemDetailActivity.class);
                intent.putExtra("item_id", item.id);

                context.startActivity(intent);
            }
        }
    };
}