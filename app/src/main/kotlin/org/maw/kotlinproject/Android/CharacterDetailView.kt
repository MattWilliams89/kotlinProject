package org.maw.kotlinproject.Android

import android.content.Context
import android.transition.*
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import com.squareup.picasso.Picasso
import org.maw.kotlinproject.R
import org.maw.kotlinproject.Models.Character
import org.maw.kotlinproject.kotterknife.bindView

/**
 * Created by willim94 on 08/04/2015.
 */
public class CharacterDetailView : ScrollView {

    public constructor(context: Context) : super(context) {
    }

    public constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
    }

    public constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
    }

    public constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
    }

    fun launch(character: Character, imageTransitionName: String, nameTransitionName: String, descTransitionName: String, container: ViewGroup) {
        val c = getContext() as MainActivity
        val view = c.getLayoutInflater().inflate(R.layout.character_details, container, false)
        val nameView = view.findViewById(R.id.char_name) as TextView
        val descriptionView = view.findViewById(R.id.char_description) as TextView
        val charImage = view.findViewById(R.id.char_image) as ImageView

        charImage.setTransitionName(imageTransitionName)
        nameView.setTransitionName(nameTransitionName)
        descriptionView.setTransitionName(descTransitionName)

        val shared = TransitionInflater.from(getContext()).inflateTransition(android.R.transition.move)
        shared.addTarget(imageTransitionName).addTarget(nameTransitionName).addTarget(descTransitionName)
        shared.setDuration(500)
        val set = TransitionSet()
        set.addTransition(shared)
        val scene = Scene(container, view)

        nameView.setText(character.name)
        descriptionView.setText(character.description)
        Picasso.with(getContext()).load(character.getThumbnailURL()).into(charImage);

        TransitionManager.go(scene, set)
    }

}
