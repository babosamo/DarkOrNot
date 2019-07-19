package com.babosamo.myapplication

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_image_icon.*


class ImageIconActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_icon)
        initView()
    }


    private fun initView() {


        dark_image.setImageResource(R.drawable.great)
        normal_image.setImageResource(R.drawable.great)
        Logger.log("dark_image.isForceDarkAllowed ${dark_image.isForceDarkAllowed}")
        Logger.log("normal_image.isForceDarkAllowed ${normal_image.isForceDarkAllowed}")
    }

    // 동작 안함.
//    fun tintDrawable(drawable: Drawable, @ColorInt tint: Int): Drawable {
//        val wrapped = DrawableCompat.wrap(drawable).mutate()
//        Logger.log("tint alpah : ${tint.alpha}")
//        wrapped.setTint(tint)
//        return wrapped
//    }

    fun isDarkTheme(): Boolean {
        return resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
    }
}
