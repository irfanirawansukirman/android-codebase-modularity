package com.irfanirawansukirman.abstraction.util.view;

import android.util.Log;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

/**
 * Created by MyInnos on 28-11-2016.
 */

public class RecyclerViewInfiniteScrollProvider {

//    private OnWhenScroll onWhenScroll;

//    public InfiniteScrollProvider(OnWhenScroll onWhenScroll) {
//        this.onWhenScroll = onWhenScroll;
//    }

    public RecyclerViewInfiniteScrollProvider() {
    }

    /**
     * {@link RecyclerView} that we want to provide infinite scrolling behavior for it
     */
    private RecyclerView recyclerView;

    /**
     * used to determine currently user is waiting for loading items or not
     */
    private boolean isLoading = false;

    /**
     * listener that trigger when user reach end of list.
     */
    private OnLoadMoreListener onLoadMoreListener;

    /**
     * {@link GridLayoutManager} that is attached to {@link #recyclerView}
     * <p/>
     * used to determine {@link #lastVisibleItem},{@link #totalItemCount},{@link #previousItemCount}
     */
    private RecyclerView.LayoutManager layoutManager;

    /**
     * position of last visible item
     */
    private int lastVisibleItem;

    /**
     * total items count of {@link #recyclerView}
     */
    private int totalItemCount;

    /**
     * total items count, relate to before last {@link #onLoadMoreListener} call.
     */
    private int previousItemCount = 0;

    /**
     * {@link #onLoadMoreListener} called when {@link #recyclerView} reach to item with position {@link #totalItemCount} - {@value THRESHOLD}
     */
    private static final int THRESHOLD = 3;

    /**
     * this function attach {@link #recyclerView} to provide infinite scroll for it
     *
     * @param recyclerView       see {@link #recyclerView} for more information
     * @param onLoadMoreListener callback for notifying when user reach list ends.
     */
    public void attach(RecyclerView recyclerView, OnLoadMoreListener onLoadMoreListener) {
        this.recyclerView = recyclerView;
        this.onLoadMoreListener = onLoadMoreListener;
        layoutManager = recyclerView.getLayoutManager();
        setInfiniteScrollGrid(layoutManager);
    }

    /**
     * this function get scrolling control of {@link #recyclerView} and whenever
     * user reached list ends, {@link #onLoadMoreListener#onLoadMoreListener} will be called
     */
    private void setInfiniteScrollGrid(final RecyclerView.LayoutManager layoutManager) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                // onWhenScroll.onWhenScroll();
                totalItemCount = layoutManager.getItemCount();
                if (previousItemCount > totalItemCount) {
                    previousItemCount = totalItemCount - THRESHOLD;
                }
                if (layoutManager instanceof GridLayoutManager) {
                    lastVisibleItem = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
                } else if (layoutManager instanceof LinearLayoutManager) {
                    lastVisibleItem = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                    StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
                    int spanCount = staggeredGridLayoutManager.getSpanCount();
                    int[] ids = new int[spanCount];
                    staggeredGridLayoutManager.findLastVisibleItemPositions(ids);
                    int max = ids[0];
                    for (int i = 1; i < ids.length; i++) {
                        if (ids[1] > max) {
                            max = ids[1];
                        }
                    }
                    lastVisibleItem = max;
                }
                if (totalItemCount > THRESHOLD) {
                    if (previousItemCount <= totalItemCount && isLoading) {
                        isLoading = false;
                        Log.i("InfiniteScroll", "CartData fetched");
                    }
                    if (!isLoading && (lastVisibleItem > (totalItemCount - THRESHOLD)) && totalItemCount > previousItemCount) {
                        if (onLoadMoreListener != null) {
                            onLoadMoreListener.onLoadMore();
                        }
                        Log.i("InfiniteScroll", "End Of List");
                        isLoading = true;
                        previousItemCount = totalItemCount;
                    }
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    /**
     * Created by MyInnos on 28-11-2016.
     */
    public interface OnLoadMoreListener {
        void onLoadMore();
    }

//    public interface OnWhenScroll {
//        void onWhenScroll();
//    }

}
