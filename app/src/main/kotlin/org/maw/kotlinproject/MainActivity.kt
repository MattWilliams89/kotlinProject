package org.maw.kotlinproject

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.activity_main.*
import org.apache.commons.codec.binary.Hex
import org.apache.commons.codec.digest.DigestUtils
import retrofit.Callback
import retrofit.RetrofitError
import retrofit.client.Response
import java.util.ArrayList


public class MainActivity : Activity(), PageLoader{

    val mLoadingItem = LoadingItem()
    val mCharacterAdapter = CharacterAdapter(ArrayList<Displayable>())

    val listener = object : retrofit.Callback<Result> {
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super<Activity>.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerview.setLayoutManager(LinearLayoutManager(this))
        recyclerview.setAdapter(mCharacterAdapter)
        recyclerview.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        recyclerview.setOnScrollListener(ScrollListener(this, mLoadingItem))

        loadPage()
    }

    override fun loadPage() {
        mCharacterAdapter.getList().add(mLoadingItem)
        mCharacterAdapter.notifyDataSetChanged()

        val characterFetcher = CharacterFetcher()
        characterFetcher.fetchAllCharacters(mCharacterAdapter.getItemCount().toString(), listener)
    }

}
