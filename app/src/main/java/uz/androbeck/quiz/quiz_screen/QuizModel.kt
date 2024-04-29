package uz.androbeck.quiz.quiz_screen

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class UIQuizData(
    val quiz: String,
    val answer1: String,
    val answer2: String,
    val answer3: String,
    val answer4: String,
    val answer: String,
    val timeCount: Int
) : Parcelable

@Parcelize
data class UIAnsweredQuizData(
    val uiQuizData: UIQuizData,
    val isCorrect: Boolean,
    val isAnswered: Boolean,
    val isTimeEnded: Boolean,
    val position: Int
) : Parcelable

class QuizModel : QuizContract.Model {

    private val quizList = mutableListOf(
        UIQuizData(
            "1+1 necha bo'ladi ?", "1", "4", "5", "2", "2", 10
        ), UIQuizData(
            "5x5 necha bo'ladi ?", "21", "24", "25", "24", "25", 10
        ), UIQuizData(
            "20:4 necha bo'ladi ?", "1", "4", "5", "4", "5", 10
        )
    )

    private val answeredDataList = mutableListOf<UIAnsweredQuizData>()


    private var quizIndex = -1

    override fun getQuizListByModelLayer(): List<UIQuizData> {
        return quizList
    }

    override fun getQuizModelByIndex(): UIQuizData {
        quizIndex++
        return quizList[quizIndex]
    }

    override fun isCorrectUserAnswered(answer: String): Boolean {
        return quizList[quizIndex].answer == answer
    }

    override fun isFinish() = quizList.size == quizIndex + 1
    override fun clickBtnNextButton() {
        answeredDataList.add(
            UIAnsweredQuizData(
                uiQuizData = quizList[quizIndex],
                isCorrect = false,
                isAnswered = false,
                isTimeEnded = false,
                position = quizIndex
            )
        )
    }

    override fun clickBtnAnsweredButton(answer: String) {
        answeredDataList.add(
            UIAnsweredQuizData(
                uiQuizData = quizList[quizIndex],
                isCorrect = isCorrectUserAnswered(answer),
                isAnswered = true,
                isTimeEnded = false,
                position = quizIndex
            )
        )
    }

    override fun whenTimeEnded() {
        answeredDataList.add(
            UIAnsweredQuizData(
                uiQuizData = quizList[quizIndex],
                isCorrect = false,
                isAnswered = false,
                isTimeEnded = true,
                position = quizIndex
            )
        )
    }

    override fun getAnsweredQuizList(): List<UIAnsweredQuizData> {
        return answeredDataList
    }

    fun getCurrentQuizIndex() = quizIndex
}