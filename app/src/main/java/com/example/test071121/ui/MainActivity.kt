package com.example.test071121.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.*
import com.example.test071121.R
import com.example.test071121.ui.foto_list.FotoListFragment
import com.example.test071121.ui.foto_view.FotoFragment

class MainActivity : AppCompatActivity(), FotoListFragment.OnFotoClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(supportFragmentManager.fragments.size == 0){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, FotoListFragment())
                .commit()
        }

        if (supportFragmentManager.backStackEntryCount > 0){
            setFullscreen(true)
        }
    }

    override fun onClickFoto(url: String) {
        setFullscreen(true)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, FotoFragment.newInstance(url))
            .addToBackStack(null)
            .commit()
    }

    override fun onBackPressed() {
        setFullscreen(false)
        super.onBackPressed()
    }

    @Suppress("DEPRECATION")
    fun setFullscreen(isFull: Boolean){
        var flags = SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

        if (isFull)
            flags = (flags or SYSTEM_UI_FLAG_IMMERSIVE
                    or SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or SYSTEM_UI_FLAG_FULLSCREEN
                    or SYSTEM_UI_FLAG_HIDE_NAVIGATION)

        window.decorView.systemUiVisibility = (flags)
    }
}

