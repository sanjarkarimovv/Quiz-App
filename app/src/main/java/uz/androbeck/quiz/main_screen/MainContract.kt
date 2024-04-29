package uz.androbeck.quiz.main_screen

interface MainContract {

    interface View {
        fun toQuizScreen()
    }

    interface Model {

    }

    interface Presenter {
        fun toQuizScreen()

        fun onDestroy()
    }
}