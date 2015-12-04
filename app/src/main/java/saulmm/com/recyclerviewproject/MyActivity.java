package saulmm.com.recyclerviewproject;

import android.app.Activity;
import android.graphics.Outline;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;


public class MyActivity extends Activity {

    private FrameLayout deleteBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        // Deletebar
        deleteBar = (FrameLayout) findViewById(R.id.deleteBar);


        // Fab button
        ViewOutlineProvider viewOutlineProvider = new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                // Or read size directly from the view's width/height
                int fabSize = getResources().getDimensionPixelSize(R.dimen.fab_size);
                Outline fabOutline = new Outline();
                fabOutline.setOval(0, 0, fabSize, fabSize);
            }
        };
        View fabView = findViewById(R.id.fab_add);
        fabView.setOutlineProvider(viewOutlineProvider);

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

        deleteBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int positionToRemove = mLayoutManager.findFirstCompletelyVisibleItemPosition();
                sampleRecyclerAdapter.removeData(positionToRemove);
                hideDeleteBar();
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView v, int i) {

            }

            @Override
            public void onScrolled(RecyclerView v, int dx, int dy) {

                if (dy > 0) {

                    if (deleteBar.getVisibility() == View.VISIBLE)
                        hideDeleteBar();

                } else {

                    if (deleteBar.getVisibility() == View.GONE)
                        showDeleteBar();
                }
            }
        });
    }

    private void showDeleteBar() {

        deleteBar.startAnimation(AnimationUtils.loadAnimation(this,
                R.anim.translate_up_on));

        deleteBar.setVisibility(View.VISIBLE);
    }

    private void hideDeleteBar() {

        deleteBar.startAnimation(AnimationUtils.loadAnimation(this,
                R.anim.translate_up_off));

        deleteBar.setVisibility(View.GONE);
    }
}
