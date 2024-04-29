package uz.androbeck.quiz.quiz_screen

interface QuizContract {

    interface View {
        fun sendQuizTimeToUI(formattedTime: String, isStop: Boolean)
        fun sendOrStartQuizToUI(uiQuizData: UIQuizData)
        fun lastQuizEndedTime()
    }

    interface Model {
        fun getQuizListByModelLayer(): List<UIQuizData>
        fun getQuizModelByIndex(): UIQuizData
        fun isCorrectUserAnswered(answer: String): Boolean
        fun isFinish(): Boolean
        fun clickBtnNextButton()
        fun clickBtnAnsweredButton(answer: String)
        fun whenTimeEnded()
        fun getAnsweredQuizList(): List<UIAnsweredQuizData>
    }

    interface Presenter {
        fun starTimer(timeCount: Int)
        fun stopTimer()
        fun sendOrStartQuizToUI()
        fun isCorrectUserAnswered(answer: String): Boolean
        fun isFinish(): Boolean
        fun clickBtnNextButton()
        fun clickBtnAnsweredButton(answer: String)
        fun getAnsweredQuizList(): List<UIAnsweredQuizData>
    }
}