package org.maw.kotlinproject.Models

import org.maw.kotlinproject.Models.Displayable

public class Character : Displayable {
    var id : Int = 0
    var name: String = ""
    var description: String = ""
    var thumbnail: Thumbnail?= null

    fun getThumbnailURL() : String {
        return thumbnail!!.path + "." + thumbnail!!.extension
    }

    override fun getID(): Long {
        return id.toLong()
    }
}