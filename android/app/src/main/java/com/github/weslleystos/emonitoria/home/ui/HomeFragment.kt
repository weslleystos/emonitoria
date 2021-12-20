package com.github.weslleystos.emonitoria.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.viewbinding.ViewBinding
import com.github.weslleystos.emonitoria.databinding.FragmentHomeBinding
import com.github.weslleystos.emonitoria.shared.ui.BindingFragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class HomeFragment : BindingFragment<FragmentHomeBinding>() {

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentHomeBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Firebase.auth.signOut()
    }
}