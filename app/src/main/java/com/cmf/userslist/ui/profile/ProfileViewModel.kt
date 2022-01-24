package com.cmf.userslist.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmf.userslist.domain.usecase.EditUserUseCase
import com.cmf.userslist.domain.usecase.GetUserUseCase
import com.cmf.userslist.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val editUserUseCase: EditUserUseCase
) : ViewModel() {
    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    private val _saved = MutableLiveData<Boolean>()
    val saved: LiveData<Boolean> = _saved

    fun loadData(userId: String?) {
        userId?.let {
            viewModelScope.launch {
                val user = getUserUseCase.invoke(userId)
                if (user.isSuccess) {
                    _user.value = user.getOrNull()
                }
            }

        }
    }

    fun save(user: User) {
        viewModelScope.launch {
            editUserUseCase.invoke(user)
            _saved.value = true
        }
    }

}