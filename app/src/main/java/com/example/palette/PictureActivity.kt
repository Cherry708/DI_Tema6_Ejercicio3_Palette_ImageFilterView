package com.example.palette

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.Image
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.palette.graphics.Palette

class PictureActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = "Palette"

        val tvLight = findViewById<TextView>(R.id.tvLight)
        val tvMuted = findViewById<TextView>(R.id.tvMuted)
        val tvDarkMuted = findViewById<TextView>(R.id.tvDarkMuted)
        val tvLightMuted = findViewById<TextView>(R.id.tvLightMuted)

        val imageView = findViewById<ImageView>(R.id.imageView)

        val bundle = intent.extras
        val chosenImage = bundle!!.getInt("chosenImage")
        imageView.setImageDrawable(resources.getDrawable(chosenImage))

//        val intent = getIntent()
//        val extras = intent.getExtras()
//        val chosenImage = extras!!.getInt("chosenImage")
//        imageView.setImageDrawable(resources.getDrawable(chosenImage))

        val bitmap: Bitmap = chosenImage?.let { BitmapFactory.decodeResource(resources, it) }!!

        Palette.from(bitmap).generate { palette ->
            val vibrant: Palette.Swatch? = palette?.vibrantSwatch
            val darkvibrant: Palette.Swatch? = palette?.darkVibrantSwatch
            val lightvibrant: Palette.Swatch? = palette?.lightVibrantSwatch
            val muted: Palette.Swatch? = palette?.mutedSwatch
            val darkmuted: Palette.Swatch? = palette?.darkMutedSwatch
            val lightmuted: Palette.Swatch? = palette?.lightMutedSwatch

            if (vibrant != null){
                toolbar.setBackgroundColor(vibrant.rgb)
                toolbar.setTitleTextColor(vibrant.titleTextColor)
            }

            if (darkvibrant != null){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    this.window.statusBarColor = darkvibrant.rgb
                }
            }

            if (lightvibrant != null){
                tvLight.setBackgroundColor(lightvibrant.rgb)
                tvLight.setTextColor(lightvibrant.titleTextColor)
            }

            if (muted != null){
                tvMuted.setBackgroundColor(muted.rgb)
                tvMuted.setTextColor(muted.titleTextColor)
            }

            if (darkmuted != null){
                tvDarkMuted.setBackgroundColor(darkmuted.rgb)
                tvDarkMuted.setTextColor(darkmuted.titleTextColor)
            }

            if (lightmuted != null){
                tvLightMuted.setBackgroundColor(lightmuted.rgb)
                tvLightMuted.setTextColor(lightmuted.titleTextColor)
            }
        }
    }
}