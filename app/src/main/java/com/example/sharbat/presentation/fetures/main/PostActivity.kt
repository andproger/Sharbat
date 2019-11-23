package com.example.sharbat.presentation.fetures.main

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.sharbat.R
import com.example.sharbat.data.db.model.EventModel
import com.squareup.picasso.Picasso

class PostActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)
        val starEvent = findViewById<ImageView>(R.id.starEvent)
        var isStar = false
        val eventModel: EventModel
        intent.extras!!.get("id").toString() //todo get from database
        (findViewById<TextView>(R.id.timeEvent)).text = eventModel.time_event
        (findViewById<TextView>(R.id.whereEvent)).text = eventModel.place
        (findViewById<TextView>(R.id.textEvent)).text = eventModel.text
        Picasso.get().load(eventModel.image).into((findViewById<ImageView>(R.id.imageEvent)))
        (findViewById<TextView>(R.id.btnRegistration)).setOnClickListener {
            if (isStar) {
                isStar = true
                starEvent.setImageResource(R.drawable.ic_star_active)
            } else {
                isStar = false
                starEvent.setImageResource(R.drawable.ic_star_active)
            }
        }
    }
}