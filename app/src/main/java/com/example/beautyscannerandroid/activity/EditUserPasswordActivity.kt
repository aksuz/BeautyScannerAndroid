package com.example.beautyscannerandroid.activity

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.beautyscannerandroid.R
import com.example.beautyscannerandroid.helper.Constants
import com.example.beautyscannerandroid.viewmodel.EditUserPasswordViewModel
import kotlinx.android.synthetic.main.activity_edit_user_password.*

class EditUserPasswordActivity : AppCompatActivity() {
    private val viewModel: EditUserPasswordViewModel by lazy {
        ViewModelProvider(this).get(EditUserPasswordViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_user_password)
        setupObservers()

        showHideButton.setOnClickListener {
            if (showHideButton.text.toString().equals(getString(R.string.show))) {
                editPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
                showHideButton.text = getString(R.string.hide)
            } else {
                editPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                showHideButton.text = getString(R.string.show)
            }
        }

        showHideButton2.setOnClickListener {
            if (showHideButton2.text.toString().equals(getString(R.string.show))) {
                editPassword2.transformationMethod = HideReturnsTransformationMethod.getInstance()
                showHideButton2.text = getString(R.string.hide)
            } else {
                editPassword2.transformationMethod = PasswordTransformationMethod.getInstance()
                showHideButton2.text = getString(R.string.show)
            }
        }

        saveUserPasswordButton.setOnClickListener {
            if (editPassword.text.toString() == editPassword2.text.toString()) {
                val sharedPreferences: SharedPreferences? = this.getSharedPreferences(
                    Constants.SHARED_INFO,
                    Context.MODE_PRIVATE
                )
                viewModel.updateUserPassword(
                    sharedPreferences!!.getLong(Constants.USER_ID, 0L),
                    editPassword.text.toString()
                )
                this.finish()
            } else {
                Toast.makeText(
                    this,
                    getString(R.string.validation_match_password),
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }
    }

    private fun setupObservers() {
        viewModel.finishActivity.observe(
            this,
            Observer {
                finish()
            })
    }
}