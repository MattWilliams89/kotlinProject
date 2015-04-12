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
    private var container: ViewGroup? = null
    private var detailView: CharacterDetailView? = null
    private var recyclerView: CharacterRecyclerView? = null
    private var mImageTransitionName : String? = null
    private var mNameTransitionName : String? = null
    private var mDescTransitionName : String? = null

    override fun onFinishInflate() {
        super<FrameLayout>.onFinishInflate()
        container = findViewById(R.id.singlePaneContainer) as ViewGroup
        recyclerView = getChildAt(0) as CharacterRecyclerView
        detailView = (getContext() as MainActivity).getLayoutInflater().inflate(R.layout.detail_view, container, false) as CharacterDetailView
    }

    override public fun onBackPressed(): Boolean {
        if (!listViewAttached()) {
            animateSceneTransition(recyclerView!!)
            return true
        }
        return false
    }

    private fun animateSceneTransition(transitionTarget: View) {
        val shared = TransitionInflater.from(getContext()).inflateTransition(android.R.transition.move)
        shared.addTarget(mImageTransitionName).addTarget(mNameTransitionName).addTarget(mDescTransitionName)
        shared.setDuration(500)
        val set = TransitionSet()
        set.addTransition(shared)
        val scene = Scene(container, transitionTarget)
        TransitionManager.go(scene, set)
    }

    override public fun showItem(character: Character, imageTransitionName: String, nameTransitionName: String, descTransitionName: String) {
        mNameTransitionName = nameTransitionName
        mImageTransitionName = imageTransitionName
        mDescTransitionName = descTransitionName

        detailView!!.setupView(character, imageTransitionName, nameTransitionName, descTransitionName)
        animateSceneTransition(detailView!!)
    }

    private fun listViewAttached(): Boolean {
        return recyclerView!!.getParent() != null
    }
}