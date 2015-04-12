
package org.maw.kotlinproject.Android

import org.maw.kotlinproject.Models.Character

public trait Container {

    fun showItem(character: Character, imageTransitionName: String, nameTransitionName: String, descTransitionName: String)

    fun onBackPressed() : Boolean
}