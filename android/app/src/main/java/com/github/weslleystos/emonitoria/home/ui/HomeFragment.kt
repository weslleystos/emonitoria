package com.github.weslleystos.emonitoria.home.ui

import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import com.github.weslleystos.emonitoria.databinding.FragmentHomeBinding
import com.github.weslleystos.emonitoria.shared.ui.BindingFragment

class HomeFragment : BindingFragment<FragmentHomeBinding>() {

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentHomeBinding::inflate
}