package uz.androbeck.quiz.finish_screen

import uz.androbeck.quiz.quiz_screen.UIAnsweredQuizData

interface FinishContract {
    interface Model {
        fun forEachAnsweredData(
            uiAnsweredQuizDataList: ArrayList<UIAnsweredQuizData>,
            action: (uiData: UIAnsweredQuizData) -> Unit
        )
    }

    interface View {
        fun forEachAnsweredData(uiAnsweredQuizData: UIAnsweredQuizData)
    }

    interface Presenter {
        fun forEachAnsweredData(uiAnsweredQuizDataList: ArrayList<UIAnsweredQuizData>)
        fun getYesOrNoText(isAnswered: Boolean): String
        fun getCorrectAnswerText(isCorrect: Boolean, isAnswered: Boolean): String
        fun getWhenTimeEnded(isTimeEnded: Boolean) : String
    }
}