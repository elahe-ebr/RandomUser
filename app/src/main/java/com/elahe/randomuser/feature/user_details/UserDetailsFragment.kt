package com.elahe.randomuser.feature.user_details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.elahe.randomuser.R
import com.elahe.randomuser.common.BaseFragment
import com.elahe.randomuser.common.EXTRA_KEY_DATA
import com.elahe.randomuser.data.Result
import com.elahe.randomuser.services.ImageLoadingService
import kotlinx.android.synthetic.main.fragment_user_details.*
import kotlinx.android.synthetic.main.fragment_user_details.locationTv
import kotlinx.android.synthetic.main.fragment_user_details.userNameTv
import org.koin.android.ext.android.inject

class UserDetailsFragment : BaseFragment() {

    val imageLoadingService: ImageLoadingService by inject()
    lateinit var user: Result

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = layoutInflater.inflate(R.layout.fragment_user_details, container, false)

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        user = arguments?.getParcelable(EXTRA_KEY_DATA)!!
        imageLoadingService.load(userIV, user.picture.large)
        userNameTv.text = "${user.name.title} ${user.name.first} ${user.name.last}"
        locationTv.text =
            "${user.location.country} - ${user.location.state} - ${user.location.city}"
        emailTv.text = user.email
        phoneTv.text = user.phone

        backIv.setOnClickListener {
            requireActivity()
                .onBackPressed()
        }
    }
}