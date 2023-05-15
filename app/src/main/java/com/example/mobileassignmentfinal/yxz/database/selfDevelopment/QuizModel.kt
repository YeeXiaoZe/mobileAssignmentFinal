package com.example.mobileassignmentfinal.yxz.database.selfDevelopment

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.mobileassignmentfinal.yxz.database.selfDevelopment.ChapterModel

@Entity(tableName = "question",
    foreignKeys = [ForeignKey(
        entity = ChapterModel::class,
        parentColumns = ["chapterID"],
        childColumns = ["chapterID"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class QuizModel(
    @PrimaryKey val quizID: String,
    @ColumnInfo(name = "chapterID") val chapterID: String
)