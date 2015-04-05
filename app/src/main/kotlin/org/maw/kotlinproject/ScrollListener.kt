package org.maw.kotlinproject

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

/**
 * Created by willim94 on 05/04/2015.
 */
public class ScrollListener(pageLoader: PageLoader, loadingItem: LoadingItem) : RecyclerView.OnScrollListener() {

    var mPageLoader = pageLoader
    var mLoadingItem = loadingItem

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

        val linearLayoutManager = recyclerView.getLayoutManager() as LinearLayoutManager

        val visibleItemCount = recyclerView.getChildCount()
        val totalItemCount = linearLayoutManager.getItemCount()
        val firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition()

        if ( (firstVisibleItem + visibleItemCount > (totalItemCount - 4)) && totalItemCount != 0 ) {
            val adapter = recyclerView.getAdapter() as CharacterAdapter
            if (!adapter.getList().contains(mLoadingItem)) {
                mPageLoader.loadPage()
            }
        }
    }
}