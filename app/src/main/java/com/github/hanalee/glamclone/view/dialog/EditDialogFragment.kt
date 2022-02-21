package com.github.hanalee.glamclone.view.dialog

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.github.hanalee.glamclone.R
import com.github.hanalee.glamclone.databinding.EditInfoDialogLayoutBinding
import com.github.hanalee.glamclone.domain.retrofit.entity.response.ProfileResponse
import com.github.hanalee.glamclone.utils.UtilFunction.Companion.BODY_TYPE
import com.github.hanalee.glamclone.utils.UtilFunction.Companion.EDUCATION
import com.github.hanalee.glamclone.utils.UtilFunction.Companion.TAG
import com.github.hanalee.glamclone.view.adapter.EditInfoAdapter
import org.koin.android.ext.android.inject

class EditDialogFragment(
    private val type: String,
    private val item: ProfileResponse.Meta
) :
    DialogFragment() {
    private val editInfoAdapter: EditInfoAdapter by inject()
    private lateinit var binding: EditInfoDialogLayoutBinding
    private lateinit var customDialogListener: CustomDialogListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = EditInfoDialogLayoutBinding.inflate(layoutInflater)
        val view = binding.root

        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        //dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

    }

    interface CustomDialogListener {
        fun setResult(type: String, item: String)
    }

    fun setDialogListener(dialogListener: CustomDialogListener) {
        customDialogListener = dialogListener
    }


    private fun initView() = with(binding) {
        when (type) {
            BODY_TYPE -> {
                tvTitle.text = getString(R.string.body)
            }
            EDUCATION -> {
                tvTitle.text = getString(R.string.grade)
            }
        }
        val itemClickEvent = object : EditInfoAdapter.OnItemClickListener {
            override fun onItemClick(type: String, item: String) {
                Log.d(TAG, "onItemClick: $type / $item")
                customDialogListener.setResult(type, item)
                dialog?.dismiss()
            }
        }
        editInfoAdapter.setOnItemClickListener(itemClickEvent)
        editInfoAdapter.setData(type, item)
        rvProfileInfo.adapter = editInfoAdapter
    }

}