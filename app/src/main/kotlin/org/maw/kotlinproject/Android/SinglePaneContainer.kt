package org.maw.kotlinproject.Android

import android.content.Context
import android.transition.Scene
import android.transition.TransitionInflater
import android.transition.TransitionManager
import android.transition.TransitionSet
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
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

    override public fun showItem(character: Character, imageView: ImageView) {
        if (listViewAttached()) {
            removeViewAt(0)
        }
        CharacterDetailView(getContext()).launch(character, imageView.getTransitionName(), this)
    }

    private fun listViewAttached(): Boolean {
        return recyclerView!!.getParent() != null
    }
}