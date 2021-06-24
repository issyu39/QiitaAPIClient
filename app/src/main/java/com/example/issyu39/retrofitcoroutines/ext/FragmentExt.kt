package com.example.issyu39.retrofitcoroutines.ext

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.issyu39.retrofitcoroutines.R
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

// FragmentでViewの参照を持つときにメモリリークしないように
// https://satoshun.github.io/2020/01/fragment-view-memory-leak/
fun <T : ViewDataBinding> Fragment.viewBinding(): ReadOnlyProperty<Fragment, T> =
    ReadOnlyProperty<Fragment, T> { thisRef, property ->
        val view = thisRef.requireView()
        var binding = view.getTag(R.id.fragment_binding) as? T
        if (binding == null) {
            binding = DataBindingUtil.bind(view)
            view.setTag(R.id.fragment_binding, binding)
        }
        binding!!
    }