package uz.androbeck.quiz.main_screen

class MainPresenter(
    private var mainView: MainContract.View?,
    private var mainModel: MainModel
) : MainContract.Presenter {
    override fun toQuizScreen() {
        mainView?.toQuizScreen()
    }

    override fun onDestroy() {
        mainView = null
    }
}