
package org.maw.kotlinproject.Android

import android.widget.ImageView
import org.maw.kotlinproject.Models.Character

public trait Container {

    fun showItem(character: Character, imageTransitionName: String, nameTransitionName: String, descTransitionName: String)

    fun onBackPressed() : Boolean
}