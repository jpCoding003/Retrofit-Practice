package com.tops.retrofitpractice.frgments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tops.retrofitpractice.databinding.FragmentNewProductBinding

class NewProductFragment : Fragment() {

    private lateinit var binding: FragmentNewProductBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewProductBinding.inflate(layoutInflater)
        return binding.root
    }
}