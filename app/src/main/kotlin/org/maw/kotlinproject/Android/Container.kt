
package org.maw.kotlinproject.Android

import org.maw.kotlinproject.Models.Character

public trait Container {

    fun showItem(character: Character)

    fun onBackPressed() : Boolean
}