package com.example.mobileassignmentfinal.yxz.database.selfDevelopment

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.mobileassignmentfinal.yxz.database.selfDevelopment.ChapterSQLiteHelper.Companion.CHAPTER_TABLE

class QuizSQLiteHelper(context: Context) :
    SQLiteOpenHelper(context, "Quiz.db", null, 1)
{
    companion object
    {
        const val QUIZ_TABLE = "quiz"
        private const val QUIZ_ID = "quizID"
        private const val CHAPTER_ID = "chapterID"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE " + QUIZ_TABLE + "(" + QUIZ_ID +
                " TEXT PRIMARY KEY, " + CHAPTER_ID + " TEXT, FOREIGN KEY (" + CHAPTER_ID + ") REFERENCES " +
                CHAPTER_TABLE + "(" + CHAPTER_ID + "))")
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $QUIZ_TABLE")
    }

    fun insertQuiz(quiz: QuizModel): Long
    {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(QUIZ_ID, quiz.quizID)
        contentValues.put(CHAPTER_ID, quiz.chapterID)

        val result = db.insert(QUIZ_TABLE, null, contentValues)
        db.close()

        return result
    }

    @SuppressLint("Range")
    fun getAllQuiz(): ArrayList<QuizModel>
    {
        val quizList: ArrayList<QuizModel> = ArrayList()
        val query = "SELECT * FROM $QUIZ_TABLE"
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

        var quizID: String
        var chapterID: String

        if (cursor.moveToFirst())
        {
            do
            {
                quizID = cursor.getString(cursor.getColumnIndex("quizID"))
                chapterID = cursor.getString(cursor.getColumnIndex("chapterID"))

                quizList.add(
                    QuizModel(quizID = quizID, chapterID = chapterID)
                )
            }while (cursor.moveToNext())
        }

        return quizList
    }

    @SuppressLint("Range")
    fun getAttribute(COLUMN_NAME: String): ArrayList<String>
    {
        val attributeList: ArrayList<String> = ArrayList()
        val query = "SELECT $COLUMN_NAME FROM $QUIZ_TABLE"
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
        val query = "SELECT $COLUMN_NAME FROM $QUIZ_TABLE WHERE $CONDITIONAL_COLUMN = '$CONDITIONAL_STRING'"
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