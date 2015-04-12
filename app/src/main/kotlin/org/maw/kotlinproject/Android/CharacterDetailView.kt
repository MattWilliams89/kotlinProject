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

    val nameView : TextView by bindView(R.id.char_name)
    val descriptionView : TextView by bindView(R.id.char_description)
    val charImage : ImageView by bindView(R.id.char_image)

    public constructor(context: Context) : super(context) {
    }

    public constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
    }

    public constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
    }

    public constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
    }

    fun setupView(character: Character, imageTransitionName: String, nameTransitionName: String, descTransitionName: String) {
        charImage.setTransitionName(imageTransitionName)
        nameView.setTransitionName(nameTransitionName)
        descriptionView.setTransitionName(descTransitionName)

        nameView.setText(character.name)
        descriptionView.setText(character.description)
        Picasso.with(getContext()).load(character.getThumbnailURL()).into(charImage);
    }

}
