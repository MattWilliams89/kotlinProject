package org.maw.kotlinproject.Android

import android.app.Activity
import android.os.Bundle
import kotlinx.android.synthetic.activity_main.singlePaneContainer
import org.maw.kotlinproject.R

public class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super<Activity>.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun getContainer() : SinglePaneContainer {
        return singlePaneContainer
    }

    override fun onBackPressed() {
        if (!singlePaneContainer.onBackPressed()) {
            finish()
        }
    }
}