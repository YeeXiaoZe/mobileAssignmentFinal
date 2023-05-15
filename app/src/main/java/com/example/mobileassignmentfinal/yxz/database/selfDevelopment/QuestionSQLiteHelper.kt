package com.example.mobileassignmentfinal.yxz.database.selfDevelopment

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.mobileassignmentfinal.yxz.database.selfDevelopment.QuizSQLiteHelper.Companion.QUIZ_TABLE

class QuestionSQLiteHelper(context: Context) :
    SQLiteOpenHelper(context, "Question.db", null, 1)
{
    companion object
    {
        private const val QUESTION_TABLE = "question"
        private const val QUESTION_ID = "chapterID"
        private const val QUESTION = "question"
        private const val CORRECT_ANS = "correctAns"
        private const val WRONG_ANS_1 = "wrongAns1"
        private const val WRONG_ANS_2 = "wrongAns2"
        private const val WRONG_ANS_3 = "wrongAns3"
        private const val QUIZ_ID = "quizID"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(
            ("CREATE TABLE $QUESTION_TABLE" + "(" + QUESTION_ID +
                    " TEXT PRIMARY KEY, " + QUESTION + " TEXT, " + CORRECT_ANS + " TEXT, "
                    + WRONG_ANS_1) + " TEXT, " + WRONG_ANS_2 + " TEXT, " +
                    WRONG_ANS_3 + " TEXT, " + QUIZ_ID + " TEXT, " + "FOREIGN KEY (" + QUIZ_ID + ") REFERENCES " +
                    QUIZ_TABLE + "(" + QUIZ_ID + "))"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $QUESTION_TABLE")
    }

    fun insertQuestion(question: QuestionModel): Long
    {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(QUESTION_ID, question.questionID)
        contentValues.put(QUESTION, question.question)
        contentValues.put(CORRECT_ANS, question.correctAns)
        contentValues.put(WRONG_ANS_1, question.wrongAns1)
        contentValues.put(WRONG_ANS_2, question.wrongAns2)
        contentValues.put(WRONG_ANS_3, question.wrongAns3)
        contentValues.put(QUIZ_ID, question.quizID)

        val result = db.insert(QUESTION_TABLE, null, contentValues)
        db.close()

        return result
    }

    @SuppressLint("Range")
    fun getAllQuestion(): ArrayList<QuestionModel>
    {
        val questionList: ArrayList<QuestionModel> = ArrayList()
        val query = "SELECT * FROM $QUESTION_TABLE"
        val db = this.readableDatabase

        val cursor: Cursor?

        try
        {
            cursor = db.rawQuery(query, null)
        }
        catch (ex: Exception)
        {
            ex.printStackTrace()
            db.execSQL(query)

            return ArrayList()
        }

        var questionID: String
        var question: String
        var correctAns: String
        var wrongAns1: String
        var wrongAns2: String
        var wrongAns3: String
        var quizID: String

        if (cursor.moveToFirst())
        {
            do
            {
                questionID = cursor.getString(cursor.getColumnIndex("questionID"))
                question = cursor.getString(cursor.getColumnIndex("question"))
                correctAns = cursor.getString(cursor.getColumnIndex("correctAns"))
                wrongAns1 = cursor.getString(cursor.getColumnIndex("wrongAns1"))
                wrongAns2 = cursor.getString(cursor.getColumnIndex("wrongAns2"))
                wrongAns3 = cursor.getString(cursor.getColumnIndex("wrongAns3"))
                quizID = cursor.getString(cursor.getColumnIndex("quizID"))

                questionList.add(
                    QuestionModel(questionID = questionID, question = question,
                    correctAns = correctAns, wrongAns1 = wrongAns1, wrongAns2 = wrongAns2,
                    wrongAns3 = wrongAns3, quizID = quizID)
                )
            }while (cursor.moveToNext())
        }

        return questionList
    }

    @SuppressLint("Range")
    fun getAttribute(COLUMN_NAME: String): ArrayList<String>
    {
        val attributeList: ArrayList<String> = ArrayList()
        val query = "SELECT $COLUMN_NAME FROM $QUESTION_TABLE"
        val db = this.readableDatabase

        val cursor: Cursor?

        try
        {
            cursor = db.rawQuery(query, null)
        }
        catch (ex: Exception)
        {
            ex.printStackTrace()
            db.execSQL(query)

            return ArrayList()
        }

        var attribute: String

        if (cursor.moveToFirst())
        {
            do
            {
                attribute = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))

                attributeList.add(attribute)
            }while (cursor.moveToNext())
        }

        return attributeList
    }

    @SuppressLint("Range")
    fun conditionalGetAttribute(COLUMN_NAME: String, CONDITIONAL_COLUMN: String, CONDITIONAL_STRING: String): String
    {
        var attribute = ""
        val query = "SELECT $COLUMN_NAME FROM $QUESTION_TABLE WHERE $CONDITIONAL_COLUMN = '$CONDITIONAL_STRING'"
        val db = this.readableDatabase

        val cursor: Cursor?

        try
        {
            cursor = db.rawQuery(query, null)
        }
        catch (ex: Exception)
        {
            ex.printStackTrace()
            db.execSQL(query)

            return ""
        }

        if (cursor.moveToFirst())
        {
            do
            {
                attribute = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
                break;
            }while (cursor.moveToNext())
        }

        return attribute
    }

    fun foreignGetAttribute()
    {

    }
}