package com.babosamo.myapplication

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Color.MAGENTA
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.alpha
import androidx.core.graphics.drawable.DrawableCompat
import androidx.palette.graphics.Palette
import kotlinx.android.synthetic.main.activity_palette.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job


class PaletteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_palette)
        initView()
    }

    init {
//        AppCompatResources.getColorStateList(this, R.color.abc_background_cache_hint_selector_material_dark)
//        AppCompatResources.getThemeColor

    }

    private fun initView() {
        button_6200EE.setOnClickListener {
            makeColor(Color.parseColor("#6200ee"))
            getThemeColor(R.color.cyan)
        }

        button_3700B3.setOnClickListener {
            makeColor(Color.parseColor("#3700b3"))
            getThemeColor(it.id)
        }

        button_03DAC6.setOnClickListener {
            makeColor(Color.parseColor("#03DAC6"))
            getThemeColor(it.id)
        }

        button_018786.setOnClickListener {
            makeColor(Color.parseColor("#018786"))
            getThemeColor(it.id)
        }

        button_FFFFFF.setOnClickListener {
            makeColor(Color.parseColor("#FFFFFF"))
            getThemeColor(it.id)
        }

        button_000000.setOnClickListener {
            makeColor(Color.parseColor("#000000"))
            getThemeColor(it.id)
        }

        button_B00020.setOnClickListener {
            makeColor(Color.parseColor("#B00020"))
            getThemeColor(it.id)
        }
    }

    @ColorInt
    fun getThemeColor(context: Context, @AttrRes attrResId: Int): Int {
        val a = context.obtainStyledAttributes(null, intArrayOf(attrResId))
        try {
            return a.getColor(0, MAGENTA)
        } finally {
            a.resources
        }

    }

    fun tintDrawable(drawable: Drawable, @ColorInt tint: Int): Drawable {
        val wrapped = DrawableCompat.wrap(drawable).mutate()
        wrapped.setTint(tint)
        return wrapped
    }

    private fun getThemeColor(id: Int){
        val themeColor = getThemeColor(this@PaletteActivity, id)
        val hexColor = String.format("#%06X", 0xFFFFFF and themeColor)
        Logger.log("themeColor alpha ${themeColor.alpha} $hexColor")
        color_display.setBackgroundColor(themeColor)
    }


    private fun makeColor(color: Int) {
        val bitmap = createImage(color = color)
        color_display.setBackgroundColor(color)


        Palette.from(bitmap).generate {
            Logger.log("vibrantSwatch ${it?.vibrantSwatch}")
            it?.vibrantSwatch?.run {
                vibrant.setBackgroundColor(rgb)
                vibrant.text = rgb.toString()
            } ?: run {
                vibrant.setBackgroundColor(Color.TRANSPARENT)
            }

            Logger.log("darkVibrantSwatch ${it?.darkVibrantSwatch}")
            it?.darkVibrantSwatch?.run {
                vibrant_dark.setBackgroundColor(rgb)
                vibrant_dark.text = rgb.toString()
            } ?: run {
                vibrant_dark.setBackgroundColor(Color.TRANSPARENT)
            }

            Logger.log("lightVibrantSwatch ${it?.lightVibrantSwatch}")
            it?.lightVibrantSwatch?.run {
                vibrant_light.setBackgroundColor(rgb)
                vibrant_light.text = rgb.toString()
            } ?: run {
                vibrant_light.setBackgroundColor(Color.TRANSPARENT)
            }

            Logger.log("mutedSwatch ${it?.mutedSwatch}")
            it?.mutedSwatch?.run {
                muted.setBackgroundColor(rgb)
                muted.text = rgb.toString()
            } ?: run {
                muted.setBackgroundColor(Color.TRANSPARENT)
            }

            Logger.log("darkMutedSwatch ${it?.darkMutedSwatch}")
            it?.darkMutedSwatch?.run {
                muted_dark.setBackgroundColor(rgb)
                muted_dark.text = rgb.toString()
            } ?: run {
                muted_dark.setBackgroundColor(Color.TRANSPARENT)
            }

            Logger.log("lightMutedSwatch ${it?.lightMutedSwatch}")
            it?.lightMutedSwatch?.run {
                muted_light.setBackgroundColor(rgb)
                muted_light.text = rgb.toString()
            } ?: run {
                muted_light.setBackgroundColor(Color.TRANSPARENT)
            }
        }
    }

    private fun createImage(width: Int = 50, height: Int = 50, color: Int): Bitmap {
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val paint = Paint()
        paint.color = color
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paint)
        return bitmap
    }
}
