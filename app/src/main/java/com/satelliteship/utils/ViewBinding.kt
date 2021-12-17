package com.satelliteship.utils

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

inline fun <T : ViewBinding> AppCompatActivity.viewBinding(
    crossinline viewBindingFactory: (LayoutInflater) -> T
) = lazy { viewBindingFactory(layoutInflater) }
