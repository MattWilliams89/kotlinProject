package org.maw.kotlinproject

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView

public class CharacterAdapter(characterList :MutableList<Displayable>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val mCharacterList = characterList
    var TYPE_LOADING = 0
    var TYPE_CHARACTER = 1

    override fun onCreateViewHolder(parent: ViewGroup?, itemType: Int): RecyclerView.ViewHolder? {
        if ( itemType == TYPE_LOADING ){
            val v = LayoutInflater.from(parent!!.getContext()).inflate(R.layout.loading_item, parent, false)
            return LoadingHolder(v)
        }
        else {
            val v = LayoutInflater.from(parent!!.getContext()).inflate(R.layout.character_list_item, parent, false)
            return CharacterHolder(v)
        }
    }

    override fun getItemCount(): Int {
        return mCharacterList.size()
    }

    override fun getItemViewType(position: Int): Int {
        if (mCharacterList.get(position) is LoadingItem)
            return TYPE_LOADING

        return TYPE_CHARACTER
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, index: Int) {
        if (holder is CharacterHolder) {
            val characterHolder = holder
            val character = mCharacterList.get(index) as Character
            characterHolder.nameView.setText(character.name)
            characterHolder.descriptionView.setText(character.description)
        }
        else if (holder is LoadingHolder){
            val loadingHolder = holder
            loadingHolder.progressBar.setVisibility(View.VISIBLE)
        }
    }

    fun getList() : MutableList<Displayable>{
        return mCharacterList
    }

    class CharacterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameView = itemView.findViewById(R.id.char_name) as TextView
        val descriptionView = itemView.findViewById(R.id.char_description) as TextView
    }

    class LoadingHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val progressBar = itemView.findViewById(R.id.loading) as ProgressBar
    }
}