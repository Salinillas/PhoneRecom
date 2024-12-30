package com.example.phonerecom

data class Phone(val name: String, val attributes: Map<String,
        PhoneAttribute>,val photoUrl: String = "",
                 val comments: List<Comment> = emptyList())
data class PhoneAttribute(val specification: String, val score: Float)
data class Comment( val author: String = "", val content: String = "", val score: Float)