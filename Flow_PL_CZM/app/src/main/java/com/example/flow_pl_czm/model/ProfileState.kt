package com.example.flow_pl_czm.model

import android.accounts.AuthenticatorDescription

data class ProfileState(
    val profilePictureUrl: String? = null,
    val username: String? = null,
    val description: String? = null,
    val posts: List<Post> = emptyList()
)
