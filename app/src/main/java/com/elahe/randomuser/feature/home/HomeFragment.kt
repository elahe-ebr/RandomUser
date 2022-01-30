package com.elahe.randomuser.feature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.elahe.randomuser.R
import com.elahe.randomuser.common.BaseFragment
import com.elahe.randomuser.common.EXTRA_KEY_DATA
import com.elahe.randomuser.data.Result
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment(), UserListAdapter.UserOnClickListener {
    val viewModel: HomeViewModel by viewModel()
    val adapter: UserListAdapter by inject()
    lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = view.findNavController()

        viewModel.progressBarLiveData.observe(viewLifecycleOwner) {
            setProgressIndicator(it)
        }

        viewModel.userListLiveData.observe(viewLifecycleOwner) {
            usersRv.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter.users = it
            adapter.userOnClickListener = this
            usersRv.adapter = adapter
        }
    }

    override fun onClick(user: Result) {
        Bundle().apply {
            putParcelable(EXTRA_KEY_DATA, user)
            navController.navigate(R.id.action_homeFragment_to_userDetailsFragment,this)
        }
    }
}