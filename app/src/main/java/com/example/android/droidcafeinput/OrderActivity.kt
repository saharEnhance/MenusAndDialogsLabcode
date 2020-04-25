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

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener

/**
 * This activity handles radio buttons for choosing a delivery method for an
 * order, a spinner for setting the label for a phone number, and EditText input
 * controls.
 */
class OrderActivity : AppCompatActivity() , OnItemSelectedListener {
    /**
     * Sets the content view to activity_order, and gets the intent and its
     * data. Also creates an array adapter and layout for a spinner.
     *
     * @param savedInstanceState Saved instance state bundle.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        // Get the intent and its data.
        val intent = intent
        val message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE)
        val textView = findViewById<TextView>(R.id.order_textview)
        textView.text = message

        // Create the spinner.
        val spinner = findViewById<Spinner>(R.id.label_spinner)
        if (spinner != null) {
            spinner.onItemSelectedListener = this
        }

        // Create an ArrayAdapter using the string array and default spinner
        // layout.
        val adapter = ArrayAdapter.createFromResource(
                this ,
                R.array.labels_array ,
                android.R.layout.simple_spinner_item)

        // Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Apply the adapter to the spinner.
        if (spinner != null) {
            spinner.adapter = adapter
        }
    }

    /**
     * Checks which radio button was clicked and displays a toast message to
     * show the choice.
     *
     * @param view The radio button view.
     */
    fun onRadioButtonClicked(view: View) {
        // Is the button now checked?
        val checked = (view as RadioButton).isChecked
        when (view.getId()) {
            R.id.sameday -> if (checked) // Same day service
                displayToast(getString(
                        R.string.same_day_messenger_service))
            R.id.nextday -> if (checked) // Next day delivery
                displayToast(getString(
                        R.string.next_day_ground_delivery))
            R.id.pickup -> if (checked) // Pick up
                displayToast(getString(
                        R.string.pick_up))
            else -> {
            }
        }
    }

    /**
     * Displays the actual message in a toast message.
     *
     * @param message Message to display.
     */
    fun displayToast(message: String?) {
        Toast.makeText(applicationContext , message ,
                Toast.LENGTH_SHORT).show()
    }

    // Interface callback for when any spinner item is selected.
    override fun onItemSelected(adapterView: AdapterView<*> ,
                                view: View , i: Int , l: Long) {
        val spinnerLabel = adapterView.getItemAtPosition(i).toString()
        displayToast(spinnerLabel)
    }

    // Interface callback for when no spinner item is selected.
    override fun onNothingSelected(adapterView: AdapterView<*>?) {
        // Do nothing.
    }
}