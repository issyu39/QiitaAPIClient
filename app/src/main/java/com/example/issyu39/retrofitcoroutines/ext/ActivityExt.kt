package com.example.issyu39.retrofitcoroutines.ext

import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.issyu39.retrofitcoroutines.R
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

// ActivityでViewの参照を持つときにメモリリークしないように
// https://satoshun.github.io/2020/01/fragment-view-memory-leak/
fun <T : ViewDataBinding> AppCompatActivity.viewBinding(): ReadOnlyProperty<AppCompatActivity, T> =
    object : ReadOnlyProperty<AppCompatActivity, T> {
        override fun getValue(thisRef: AppCompatActivity, property: KProperty<*>): T {
            val view = thisRef.findViewById<ViewGroup>(android.R.id.content)[0]
            var binding = view.getTag(R.id.activity_binding) as? T
            if (binding == null) {
                binding = DataBindingUtil.bind(view)
                view.setTag(R.id.activity_binding, binding)
            }
            return binding!!
        }
    }