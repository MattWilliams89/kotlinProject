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


public class MainActivity : Activity() {

    val mCharacterAdapter = CharacterAdapter(ArrayList<Character>())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerview.setLayoutManager(LinearLayoutManager(this))
        recyclerview.setAdapter(mCharacterAdapter)
        recyclerview.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));

        val characterFetcher = CharacterFetcher()

        val listener = object : retrofit.Callback<Result> {
            override fun success(t: Result?, response: Response?) {
                mCharacterAdapter.getList().addAll(t?.data?.results!!)
                mCharacterAdapter.notifyDataSetChanged()
                progressBar.setVisibility(View.GONE)
            }

            override fun failure(error: RetrofitError?) {
                //TODO
                System.console().printf(error?.getCause().toString())
                progressBar.setVisibility(View.GONE)
            }

        }

        characterFetcher.fetchAllCharacters(listener)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item!!.getItemId()

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
