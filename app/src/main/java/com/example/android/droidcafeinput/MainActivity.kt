

/*
 * Copyright (C) 2018 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.droidcafeinput

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast

/**
 * This app demonstrates images used as buttons and a floating action button to
 * use an intent to launch a second activity. The app lets a user tap an image
 * to make a choice. The app displays a Toast message showing the user’s choice
 * and sends the choice as data with an intent to launch the second activity.
 */
class MainActivity : AppCompatActivity() {
    // The order message, displayed in the Toast and sent to the new Activity.
    private var mOrderMessage: String? = null

    /**
     * Creates the content view, the toolbar, and the floating action button.
     *
     * This method is provided in the Basic Activity template.
     *
     * @param savedInstanceState Saved instance state bundle.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity ,
                    OrderActivity::class.java)
            intent.putExtra(EXTRA_MESSAGE , mOrderMessage)
            startActivity(intent)
        }
    }

    /**
     * Inflates the menu, and adds items to the action bar if it is present.
     *
     * @param menu Menu to inflate.
     * @return Returns true if the menu inflated.
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main , menu)
        return true
    }

    /**
     * Handles app bar item clicks.
     *
     * @param item Item clicked.
     * @return True if one of the defined items was clicked.
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        when (item.itemId)
        {
            R.id.action_order ->
            {
                val intent = Intent(this@MainActivity ,
                        OrderActivity::class.java)
                intent.putExtra(EXTRA_MESSAGE , mOrderMessage)
                startActivity(intent)
                return true
            }
            R.id.action_status ->
            {
                displayToast(getString(R.string.action_status_message))
                return true
            }
            R.id.action_favorites ->
            {
                displayToast(getString(R.string.action_favorites_message))
                return true
            }
            R.id.action_contact ->
            {
                displayToast(getString(R.string.action_contact_message))
                return true
            }
            else ->
            {
            }
        }
        return super.onOptionsItemSelected(item)
    }
    /**
     * Displays a Toast with the message.
     *
     * @param message Message to display.
     */
    fun displayToast(message: String?) {
        Toast.makeText(applicationContext , message ,
                Toast.LENGTH_SHORT).show()
    }

    /**
     * Shows a message that the donut image was clicked.
     */
    fun showDonutOrder(view: View?) {
        mOrderMessage = getString(R.string.donut_order_message)
        displayToast(mOrderMessage)
    }

    /**
     * Shows a message that the ice cream sandwich image was clicked.
     */
    fun showIceCreamOrder(view: View?) {
        mOrderMessage = getString(R.string.ice_cream_order_message)
        displayToast(mOrderMessage)
    }

    /**
     * Shows a message that the froyo image was clicked.
     */
    fun showFroyoOrder(view: View?) {
        mOrderMessage = getString(R.string.froyo_order_message)
        displayToast(mOrderMessage)
    }

    companion object {
        // Tag for the intent extra.
        const val EXTRA_MESSAGE = "com.example.android.droidcafeinput.extra.MESSAGE"
    }
}

