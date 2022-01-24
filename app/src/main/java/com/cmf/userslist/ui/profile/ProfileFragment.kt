package com.cmf.userslist.ui.profile

import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.cmf.userslist.R
import com.cmf.userslist.databinding.ProfileFragmentBinding
import com.cmf.userslist.misc.ImageLoader
import com.cmf.userslist.model.User
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: ProfileFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProfileViewModel by viewModels()
    private var newUri: String? = null

    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        if (uri != null && uri.toString().isNotEmpty()) {
            ImageLoader.loadImage(uri.toString(), binding.imgVwProfileAvatar)
            newUri = uri.toString()
        }
    }

    companion object {
        fun newInstance() = ProfileFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ProfileFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        initObservers()

        val userId = arguments?.getString("userId")
        viewModel.loadData(userId)
    }

    private fun initObservers() {
        viewModel.user.observe(viewLifecycleOwner) { user ->
            user?.let { loadViews(it) }
        }

        viewModel.saved.observe(viewLifecycleOwner) {
            if (it) {
                view?.findNavController()?.popBackStack()
            }
        }

        binding.imgVwProfileAvatar.setOnClickListener {
            getContent.launch("image/*")
        }
    }

    private fun loadViews(user: User) {
        binding.edTxtName.setText(user.name)
        binding.edTxtLastname.setText(user.lastName)
        binding.edTxtBiography.setText(user.biography)
        ImageLoader.loadImage(user.imageUri, binding.imgVwProfileAvatar)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_profile, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            R.id.itemSave -> {
                saveValues()
                true
            }
            else -> false
        }

    private fun saveValues() {
        val id = viewModel.user.value?.id
        val oldUri = viewModel.user.value?.imageUri ?: ""
        if (id != null) {
            val user = User(
                id,
                binding.edTxtName.text.toString(),
                binding.edTxtLastname.text.toString(),
                binding.edTxtBiography.text.toString(),
                newUri ?: oldUri
            )
            viewModel.save(user)
        }
    }
}