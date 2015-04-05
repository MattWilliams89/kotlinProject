package org.maw.kotlinproject

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

public class CharacterAdapter(characterList :MutableList<Character>) : RecyclerView.Adapter<CharacterAdapter.CharacterHolder>() {

    val mCharacterList = characterList

    override fun onCreateViewHolder(p0: ViewGroup?, p1: Int): CharacterHolder? {
        val v = LayoutInflater.from(p0?.getContext()).inflate(R.layout.character_list_item, p0, false)
        return CharacterHolder(v)
    }

    override fun getItemCount(): Int {
        return mCharacterList.size()
    }

    override fun onBindViewHolder(holder: CharacterHolder?, index: Int) {

        val character = mCharacterList.get(index)
        holder?.nameView?.setText(character.name)
        holder?.descriptionView?.setText(character.description)
    }

    fun getList() : MutableList<Character>{
        return mCharacterList
    }

    class CharacterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameView = itemView.findViewById(R.id.char_name) as TextView
        val descriptionView = itemView.findViewById(R.id.char_description) as TextView
    }
}