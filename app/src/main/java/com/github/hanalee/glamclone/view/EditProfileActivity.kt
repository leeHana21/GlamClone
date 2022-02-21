package com.github.hanalee.glamclone.view

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.github.hanalee.glamclone.BuildConfig
import com.github.hanalee.glamclone.R
import com.github.hanalee.glamclone.databinding.ActivityEditProfileBinding
import com.github.hanalee.glamclone.domain.retrofit.entity.response.ProfileResponse
import com.github.hanalee.glamclone.domain.viewmodel.EditProfileViewModel
import com.github.hanalee.glamclone.utils.UtilFunction.Companion.BODY_TYPE
import com.github.hanalee.glamclone.utils.UtilFunction.Companion.EDUCATION
import com.github.hanalee.glamclone.utils.UtilFunction.Companion.TAG
import com.github.hanalee.glamclone.view.dialog.EditDialogFragment

class EditProfileActivity : AppCompatActivity() {
    private val editProfileViewModel: EditProfileViewModel by viewModels()
    private lateinit var binding: ActivityEditProfileBinding
    private lateinit var metaData: ProfileResponse.Meta
    private lateinit var editDialogFragment: EditDialogFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        initObserver()
        getProfileInfo()
    }

    private fun initView() = with(binding) {
        val resultListener = object : EditDialogFragment.CustomDialogListener {
            override fun setResult(type: String, item: String) {
                Log.d(TAG, "setResult: $type / $item")
                changeInfo(type, item)
            }
        }
        inProfileSubInfo.apply {
            tvBody.setOnClickListener {
                editDialogFragment = EditDialogFragment(BODY_TYPE, metaData)
                editDialogFragment.setDialogListener(resultListener)
                editDialogFragment.show(supportFragmentManager, BODY_TYPE)
            }
            tvGrade.setOnClickListener {
                editDialogFragment = EditDialogFragment(EDUCATION, metaData)
                editDialogFragment.setDialogListener(resultListener)
                editDialogFragment.show(supportFragmentManager, EDUCATION)
            }
        }
    }

    private fun initObserver() = with(editProfileViewModel) {
        profileInfo.observe(this@EditProfileActivity) {
            binding.apply {
                inProfileMainInfo.tvNickname.text = it.name
                inProfileMainInfo.tvBirth.text = it.birthday
                inProfileMainInfo.tvLocation.text = it.location

                inProfileSubInfo.tvHeight.text = it.height.toString()
                inProfileSubInfo.tvCompany.text = it.company
                inProfileSubInfo.tvJob.text = it.job
                inProfileSubInfo.tvSchool.text = it.school

                it.education.let {
                    inProfileSubInfo.tvGrade.text = it
                }
                val ivList =
                    arrayOf(ivProfile1, ivProfile2, ivProfile3, ivProfile4, ivProfile5, ivProfile6)
                for (i in it.pictures.indices) {
                    Glide.with(ivList[i].context)
                        .load("${BuildConfig.BASE_URL}${it.pictures[i]}")
                        .placeholder(R.drawable.person)
                        .thumbnail(0.1f)
                        .error(R.drawable.ic_launcher_foreground)
                        .into(ivList[i])
                }
            }
        }
        genderAndBodyType.observe(this@EditProfileActivity) {
            binding.apply {
                inProfileMainInfo.tvGender.text = it.first
                inProfileSubInfo.tvBody.text = it.second
            }
        }
        metaData.observe(this@EditProfileActivity) {
            this@EditProfileActivity.metaData = it
        }
        bodyType.observe(this@EditProfileActivity) {
            Log.d(TAG, "initObserver: $it")
            binding.apply {
                inProfileSubInfo.tvBody.text = it
            }
        }
        education.observe(this@EditProfileActivity) {
            Log.d(TAG, "initObserver: $it")
            binding.apply {
                inProfileSubInfo.tvGrade.text = it
            }
        }
    }

    private fun changeInfo(changeData: String, type: String) =
        editProfileViewModel.changeInfo(changeData, type)

    private fun getProfileInfo() =
        editProfileViewModel.getProfileInfo()
}
