package com.example.palette

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.SeekBar
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.utils.widget.ImageFilterView
import androidx.palette.graphics.Palette

class PictureActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = "Palette"

        val imageFilterView = findViewById<ImageFilterView>(R.id.imageFilterView)
        val contrast = findViewById<SeekBar>(R.id.contrastSeekBar)
        val warmth = findViewById<SeekBar>(R.id.warmthSeekBar)

        val bundle = intent.extras
        val chosenImage = bundle!!.getInt("chosenImage")
        imageFilterView.setImageResource(chosenImage)
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

            contrast?.setOnSeekBarChangeListener(object :
                SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seek: SeekBar,
                    progress: Int, fromUser: Boolean
                ) {
                    imageFilterView.contrast = contrast.progress.toFloat() / 100 + 1
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {
                    imageFilterView.contrast = contrast.progress.toFloat() / 100 + 1
                }

                override fun onStopTrackingTouch(p0: SeekBar?) {
                    imageFilterView.contrast = contrast.progress.toFloat() / 100 + 1
                }
            })

            warmth?.setOnSeekBarChangeListener(object :
                SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seek: SeekBar,
                    progress: Int, fromUser: Boolean
                ) {
                    imageFilterView.warmth = warmth.progress.toFloat() / 100 + 1
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {
                    imageFilterView.warmth = warmth.progress.toFloat() / 100 + 1
                }

                override fun onStopTrackingTouch(p0: SeekBar?) {
                    imageFilterView.warmth = warmth.progress.toFloat() / 100 + 1
                }
            })

//            imageFilterView.setOnClickListener( object : View.OnClickListener {
//                var esSaturada = true
//                override fun onClick(p0: View?) {
//                    if (esSaturada) {
//                        imageFilterView.saturation = 0.0f
//                        esSaturada = false
//                    } else {
//                        imageFilterView.saturation = 1.0f
//                        esSaturada = true
//                    }
//                }
//            })

        }
    }
}