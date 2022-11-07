package com.example.flow_pl_czm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flow_pl_czm.model.Post
import com.example.flow_pl_czm.model.ProfileState
import com.example.flow_pl_czm.model.User
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

class MainViewModel : ViewModel() {

    private val isAuthenticated = MutableStateFlow<Boolean>(true)
    private val user = MutableStateFlow<User?>(null)

    private val posts = MutableStateFlow(emptyList<Post>())

    private val _profileState = MutableStateFlow<ProfileState?>(null)
    val profileState = _profileState.asStateFlow()

    // combine
    init {

        isAuthenticated.combine(user) { isAuthenticated, user ->
            if (isAuthenticated) user else null
        }
            .combine(posts) { user, posts ->
                user?.let {

                    _profileState.value = profileState.value?.copy(
                        profilePictureUrl = user?.profilePicUrl,
                        username = user?.username,
                        description = user?.description,
                        posts = posts
                    )
                }

            }
            .launchIn(viewModelScope)

    }

    // zip and merge
    private val flow1 = (1..10).asFlow().onEach { delay(1000L) }
    private val flow2 = (10..20).asFlow().onEach { delay(300L) }


    init {
        flow1.zip(flow2){ number1, number2 ->
        }
    }
}