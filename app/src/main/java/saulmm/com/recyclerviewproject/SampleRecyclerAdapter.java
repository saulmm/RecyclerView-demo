package saulmm.com.recyclerviewproject;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class SampleRecyclerAdapter extends RecyclerView.Adapter<SampleRecyclerAdapter.ViewHolder> {

    private final ArrayList<SampleModel> sampleData = DemoApp.getSampleData(20);

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parentViewGroup, int i) {

        View rowView = LayoutInflater.from (parentViewGroup.getContext())
            .inflate(R.layout.list_basic_item, parentViewGroup, false);

        return new ViewHolder (rowView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        final SampleModel rowData = sampleData.get(position);
        viewHolder.textViewSample.setText(rowData.getSampleText());
        viewHolder.itemView.setTag(rowData);
    }

    @Override
    public int getItemCount() {

        return sampleData.size();
    }

    public void removeData (int position) {

        sampleData.remove(position);
        notifyItemRemoved(position);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView textViewSample;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewSample = (TextView) itemView.findViewById(
                R.id.textViewSample);
        }
    }

}
