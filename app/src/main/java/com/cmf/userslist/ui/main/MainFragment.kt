package com.cmf.userslist.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.cmf.userslist.databinding.MainFragmentBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(), OnUserClickListener {

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private val adapter = UsersAdapter(this)
    private val viewModel: MainViewModel by viewModels()

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()

        binding.recVwUsers.adapter = adapter

        viewModel.loadUsers()
    }

    private fun initObservers() {
        viewModel.items.observe(viewLifecycleOwner) {
            adapter.setItems(it)
        }

        viewModel.message.observe(viewLifecycleOwner) {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onUserClicked(user: UserUiModel) {
        val action = MainFragmentDirections.actionHomeFragmentToDetailFragment(user.id)
        view?.findNavController()?.navigate(action)
    }

    override fun onUserDeleted(user: UserUiModel) {
        viewModel.onUserDeleted(user)
    }
}