package org.maw.kotlinproject.Android

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import org.maw.kotlinproject.*
import org.maw.kotlinproject.Models.Displayable
import org.maw.kotlinproject.Models.LoadingItem
import org.maw.kotlinproject.Models.Result
import org.maw.kotlinproject.RestAdapters.CharacterFetcher
import kotlinx.android.synthetic.activity_main.*
import retrofit.Callback
import retrofit.RetrofitError
import retrofit.client.Response
import java.util.ArrayList

public class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super<Activity>.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun getContainer() : SinglePaneContainer {
        return singlePaneContainer
    }

    override fun onBackPressed() {
        val handled = singlePaneContainer.onBackPressed()
        if (!handled) {
            finish()
        }
    }
}