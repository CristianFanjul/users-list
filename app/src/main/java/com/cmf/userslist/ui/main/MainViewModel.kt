package com.cmf.userslist.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmf.userslist.domain.usecase.DeleteUserUseCase
import com.cmf.userslist.domain.usecase.GetUsersUseCase
import com.cmf.userslist.ui.UserMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val removeUserUseCase: DeleteUserUseCase
) : ViewModel() {

    private val _items = MutableLiveData<List<UserUiModel>>()
    val items: LiveData<List<UserUiModel>> = _items

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    fun loadUsers() {
        viewModelScope.launch {
            val mapper = UserMapper()
            val users = getUsersUseCase.invoke()
            if (users.isSuccess) {
                val uiModels = users.getOrDefault(emptyList()).map { mapper.userToUiModel(it) }
                _items.value = uiModels
            } else {
                _message.value = "Something went wrong when retrieving users."
            }
        }
    }

    fun onUserDeleted(user: UserUiModel) {
        viewModelScope.launch {
            val result = removeUserUseCase.invoke(user.id)
            if (result.isSuccess) {
                loadUsers()
            } else {
                _message.value = "Something went wrong while deleting user."
            }
        }
    }
}