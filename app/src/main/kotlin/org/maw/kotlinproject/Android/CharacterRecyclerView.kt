package org.maw.kotlinproject.Android

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import org.maw.kotlinproject.Models.Displayable
import org.maw.kotlinproject.Models.LoadingItem
import org.maw.kotlinproject.Models.Result
import org.maw.kotlinproject.RestAdapters.CharacterFetcher
import retrofit.Callback
import retrofit.RetrofitError
import retrofit.client.Response
import java.util.ArrayList

/**
 * Created by willim94 on 06/04/2015.
 */
public class CharacterRecyclerView : RecyclerView, PageLoader {
    val mLoadingItem = LoadingItem()
    val mCharacterAdapter = CharacterAdapter(ArrayList<Displayable>())


    public constructor(context: Context) : super(context) {
    }

    public constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
    }

    public constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
    }

    override protected fun onFinishInflate() {
        super<RecyclerView>.onFinishInflate()
        setLayoutManager(LinearLayoutManager(getContext()))
        setAdapter(mCharacterAdapter)
        addItemDecoration(DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
        setOnScrollListener(ScrollListener(this, mLoadingItem))

        loadPage()
    }

    override fun loadPage() {
        mCharacterAdapter.getList().add(mLoadingItem)
        mCharacterAdapter.notifyDataSetChanged()

        val characterFetcher = CharacterFetcher()
        characterFetcher.fetchAllCharacters(mCharacterAdapter.getItemCount().toString(), listener)
    }

    val listener = object : Callback<Result> {
        override fun success(t: Result?, response: Response?) {
            mCharacterAdapter.getList().remove(mLoadingItem)
            mCharacterAdapter.getList().addAll(t?.data?.results!!)
            mCharacterAdapter.notifyDataSetChanged()
        }

        override fun failure(error: RetrofitError?) {
            mCharacterAdapter.getList().remove(mLoadingItem)
            mCharacterAdapter.notifyDataSetChanged()
        }
    }
}
