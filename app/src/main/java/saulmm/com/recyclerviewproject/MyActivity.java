package saulmm.com.recyclerviewproject;

import android.app.Activity;
import android.graphics.Outline;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        // Fab button
        int fabSize = getResources().getDimensionPixelSize(R.dimen.fab_size);
        Outline fabOutline = new Outline();
        fabOutline.setOval(0, 0, fabSize, fabSize);

        View fabView = findViewById(R.id.fab_add);
        fabView.setOutline(fabOutline);

        // RecyclerView
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        // RecyclerView layout manager
        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        // RecyclerView ItemDecoration (divider)
        final RecyclerView.ItemDecoration itemDecoration = new SampleDivider(this);
        recyclerView.addItemDecoration(itemDecoration);

        // RecyclerView adapter
        final SampleRecyclerAdapter sampleRecyclerAdapter = new SampleRecyclerAdapter();
        recyclerView.setAdapter(sampleRecyclerAdapter);

        // RecyclerView add element logic
        fabView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int positionToAdd = mLayoutManager.findFirstCompletelyVisibleItemPosition();
                sampleRecyclerAdapter.addItem(positionToAdd);
            }
        });
    }
}
