package org.maw.kotlinproject.Android

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import org.maw.kotlinproject.Models.Character

import org.maw.kotlinproject.R

public class SinglePaneContainer(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs), Container {
    private var recyclerView: CharacterRecyclerView? = null

    override fun onFinishInflate() {
        super<FrameLayout>.onFinishInflate()
        recyclerView = getChildAt(0) as CharacterRecyclerView
    }

    override public fun onBackPressed(): Boolean {
        if (!listViewAttached()) {
            removeViewAt(0)
            addView(recyclerView!!)
            return true
        }
        return false
    }

    override public fun showItem(character: Character) {
        if (listViewAttached()) {
            removeViewAt(0)
            View.inflate(getContext(), R.layout.detail_view, this)
        }
        val detailView = getChildAt(0) as CharacterDetailView
        detailView.setItem(character)
    }

    private fun listViewAttached(): Boolean {
        return recyclerView!!.getParent() != null
    }
}