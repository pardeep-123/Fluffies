package com.puppypedia.ui.main.ui.editpetprofile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.puppypedia.R
import com.puppypedia.databinding.ActivityEditPetProfileBinding

class EditPetProfileActivity : AppCompatActivity() {

    lateinit var binding: ActivityEditPetProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditPetProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}