package uz.androbeck.quiz.finish_screen

import uz.androbeck.quiz.quiz_screen.UIAnsweredQuizData

class FinishModel : FinishContract.Model {
    override fun forEachAnsweredData(
        uiAnsweredQuizDataList: ArrayList<UIAnsweredQuizData>,
        action: (uiData: UIAnsweredQuizData) -> Unit
    ) {
        uiAnsweredQuizDataList.forEach {
            action(it)
        }
    }

}