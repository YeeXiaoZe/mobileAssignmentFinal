package com.example.mobileassignmentfinal.yxz.database.selfDevelopment

import androidx.room.PrimaryKey

data class ChapterModel (
    @PrimaryKey val chapterID: String,
    val title: String,
)