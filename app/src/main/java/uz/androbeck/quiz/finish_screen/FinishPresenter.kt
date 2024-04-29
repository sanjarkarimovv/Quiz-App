package uz.androbeck.quiz.finish_screen

import uz.androbeck.quiz.quiz_screen.UIAnsweredQuizData

class FinishPresenter(
    private var finishView: FinishContract.View?, private val finishModel: FinishModel
) : FinishContract.Presenter {

    override fun forEachAnsweredData(uiAnsweredQuizDataList: ArrayList<UIAnsweredQuizData>) {
        finishModel.forEachAnsweredData(uiAnsweredQuizDataList) {
            finishView?.forEachAnsweredData(it)
        }
    }

    override fun getYesOrNoText(isAnswered: Boolean): String {
        return if (isAnswered) "Ha" else "Yoq"
    }

    override fun getCorrectAnswerText(isCorrect: Boolean, isAnswered: Boolean): String {
        return if (isAnswered) {
            if (isCorrect) "To'g'ri" else "Noto'g'ri"
        } else "O'tkazib yuborilgan"
    }

    override fun getWhenTimeEnded(isTimeEnded: Boolean): String {
        return if (isTimeEnded) "Vaqt tugab qolgan" else "Action bajarilgan"
    }

}