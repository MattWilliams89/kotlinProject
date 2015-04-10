package org.maw.kotlinproject.Android

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.squareup.picasso.Picasso
import org.maw.kotlinproject.Models.Displayable
import org.maw.kotlinproject.Models.LoadingItem
import org.maw.kotlinproject.Models.Character
import org.maw.kotlinproject.R
import org.maw.kotlinproject.kotterknife.bindView

public class CharacterAdapter(characterList :MutableList<Displayable>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val mCharacterList = characterList
    var TYPE_LOADING = 0
    var TYPE_CHARACTER = 1

    init {
        setHasStableIds(true)
    }

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

    override fun getItemId(position: Int) : Long {
        return mCharacterList.get(position).getID()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, index: Int) {
        if (holder is CharacterHolder) {
            val characterHolder = holder
            val character = mCharacterList.get(index) as Character
            characterHolder.nameView.setText(character.name)
            characterHolder.descriptionView.setText(character.description)

            Picasso.with(characterHolder.selectableView.getContext()).load(character.getThumbnailURL()).into(characterHolder.imageView)
            characterHolder.imageView.setTransitionName("image" + getItemId(index).toString())

            characterHolder.selectableView.setOnClickListener{
                val mainActivity = characterHolder.selectableView.getContext() as MainActivity
                mainActivity.getContainer().showItem(mCharacterList.get(index) as Character, characterHolder.imageView)
            }
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
        val nameView : TextView by bindView(R.id.char_name)
        val descriptionView : TextView by bindView(R.id.char_description)
        val imageView : ImageView by bindView(R.id.char_image)
        val selectableView : LinearLayout by bindView(R.id.character_item_view)
    }

    class LoadingHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val progressBar : ProgressBar by bindView(R.id.loading)
    }
}