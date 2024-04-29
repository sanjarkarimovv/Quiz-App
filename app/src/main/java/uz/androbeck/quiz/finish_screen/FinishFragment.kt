package uz.androbeck.quiz.finish_screen

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import uz.androbeck.quiz.databinding.FragmentFinishBinding
import uz.androbeck.quiz.quiz_screen.QuizFragment
import uz.androbeck.quiz.quiz_screen.UIAnsweredQuizData

class FinishFragment : Fragment(), FinishContract.View {

    private lateinit var binding: FragmentFinishBinding
    private lateinit var presenter: FinishPresenter
    private val answerStringBuilder = StringBuilder()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentFinishBinding.inflate(inflater, container, false)
        presenter = FinishPresenter(this, FinishModel())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initArguments()
    }

    private fun initArguments() {
        val answeredQuizList =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                requireArguments().getParcelableArrayList(
                    QuizFragment.RESULT_DATA, UIAnsweredQuizData::class.java
                )
            } else requireArguments().getParcelableArrayList(QuizFragment.RESULT_DATA)

        answeredQuizList?.let {
            presenter.forEachAnsweredData(it)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun forEachAnsweredData(uiAnsweredQuizData: UIAnsweredQuizData) {
        answerStringBuilder.append(
            "${uiAnsweredQuizData.position + 1}) Javob berilgan : ${
                presenter.getYesOrNoText(uiAnsweredQuizData.isAnswered)
            }" + "\n    Javob : ${
                presenter.getCorrectAnswerText(
                    uiAnsweredQuizData.isCorrect,
                    uiAnsweredQuizData.isAnswered
                )
            }" + "\n    Vaqt : ${presenter.getWhenTimeEnded(uiAnsweredQuizData.isTimeEnded)}\n\n"
        )
        binding.tvResult.text = answerStringBuilder
    }
}