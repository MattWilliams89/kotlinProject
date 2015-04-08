package org.maw.kotlinproject.Android

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import org.maw.kotlinproject.R
import org.maw.kotlinproject.Models.Character
import org.maw.kotlinproject.kotterknife.bindView

/**
 * Created by willim94 on 08/04/2015.
 */
public class CharacterDetailView : LinearLayout {

    val nameView : TextView by bindView(R.id.char_name)
    val descriptionView : TextView by bindView(R.id.char_description)

    public constructor(context: Context) : super(context) {
    }

    public constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        inflate(context)
    }

    public constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        inflate(context)
    }

    public constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        inflate(context)
    }

    fun setItem(character: Character) {
        nameView.setText(character.name)
        descriptionView.setText(character.description)
    }

    fun inflate(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.character_list_item, this)
    }
}
