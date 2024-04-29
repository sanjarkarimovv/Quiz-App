package uz.androbeck.quiz.quiz_screen

import android.os.Handler
import android.os.Looper

class QuizPresenter(
    private var mainView: QuizContract.View?, private var mainModel: QuizModel
) : QuizContract.Presenter {

    private val handler by lazy { Handler(Looper.getMainLooper()) }
    private var isStop: Boolean = false
    private var secondCounter = 0
    private var mTimeCount: Int? = null

    private val timerRunnable = Runnable {
        secondCounter++
        mainView?.sendQuizTimeToUI(
            getTimeByFormat(getCurrentQuizData().timeCount - secondCounter), isStop
        )
        if (mTimeCount == secondCounter) {
            mainModel.whenTimeEnded()
            if (!isFinish()) {
                stopTimer()
                sendOrStartQuizToUI()
            } else {
                mainView?.lastQuizEndedTime()
            }
        }
    }

    private fun getCurrentQuizData() =
        mainModel.getQuizListByModelLayer()[mainModel.getCurrentQuizIndex()]

    fun getUserAnswer1() = getCurrentQuizData().answer1
    fun getUserAnswer2() = getCurrentQuizData().answer2
    fun getUserAnswer3() = getCurrentQuizData().answer3
    fun getUserAnswer4() = getCurrentQuizData().answer4

    private fun getTimeByFormat(time: Int): String {
        return if (time >= 10) "00:$time" else "00:0$time"
    }

    override fun starTimer(timeCount: Int) {
        isStop = false
        this.mTimeCount = timeCount
        secondCounter = 0
        repeat(timeCount) {
            handler.postDelayed(
                timerRunnable, it * MILLI_SECOND
            )
        }
    }

    override fun stopTimer() {
        isStop = true
        handler.removeCallbacks(timerRunnable)
    }

    override fun sendOrStartQuizToUI() {
        mainView?.sendOrStartQuizToUI(mainModel.getQuizModelByIndex())
    }

    override fun isCorrectUserAnswered(answer: String): Boolean {

        return mainModel.isCorrectUserAnswered(answer)
    }

    override fun isFinish(): Boolean {
        return mainModel.isFinish()
    }

    override fun clickBtnNextButton() {
        mainModel.clickBtnNextButton()
    }

    override fun clickBtnAnsweredButton(answer: String) {
        mainModel.clickBtnAnsweredButton(answer)
    }

    override fun getAnsweredQuizList(): List<UIAnsweredQuizData> {
        return mainModel.getAnsweredQuizList()
    }

    companion object {
        const val MILLI_SECOND = 1000L
    }
}