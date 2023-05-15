package com.example.mobileassignmentfinal.yxz.database.selfDevelopment

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "question",
    foreignKeys = [ForeignKey(
        entity = QuizModel::class,
        parentColumns = ["quizID"],
        childColumns = ["quizID"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class QuestionModel (
    @PrimaryKey var questionID: String,
    var question: String,
    var correctAns: String,
    var wrongAns1: String,
    var wrongAns2: String,
    var wrongAns3: String,
    @ColumnInfo(name = "quizID") val quizID: String
)