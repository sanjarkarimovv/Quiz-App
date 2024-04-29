package uz.androbeck.quiz.quiz_screen

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.androbeck.quiz.R
import uz.androbeck.quiz.databinding.FragmentQuizBinding
import uz.androbeck.quiz.extensions.getColor

class QuizFragment : Fragment(), QuizContract.View {

    private lateinit var binding: FragmentQuizBinding
    private lateinit var presenter: QuizPresenter

    private fun initPresenter() {
        presenter = QuizPresenter(this, QuizModel())
        presenter.sendOrStartQuizToUI()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuizBinding.inflate(inflater, container, false)
        initPresenter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClickViews()
    }

    override fun sendQuizTimeToUI(formattedTime: String, isStop: Boolean) {
        if (!isStop) {
            binding.tvTimer.text = formattedTime
        }
    }

    override fun sendOrStartQuizToUI(uiQuizData: UIQuizData) {
        clearButtonColor()
        presenter.stopTimer()
        with(binding) {
            with(uiQuizData) {
                tvQuiz.text = quiz
                btnAnswer1.text = answer1
                btnAnswer2.text = answer2
                btnAnswer3.text = answer3
                btnAnswer4.text = answer4
                presenter.starTimer(timeCount)
            }
        }
    }

    override fun lastQuizEndedTime() {
        findNavController().navigate(
            R.id.action_quizFragment_to_finishFragment, bundleOf(
                RESULT_DATA to presenter.getAnsweredQuizList()
            )
        )
    }

    private fun clearButtonColor() {
        with(binding) {
            btnAnswer1.setBackgroundColor(getColor(android.R.color.white))
            btnAnswer2.setBackgroundColor(getColor(android.R.color.white))
            btnAnswer3.setBackgroundColor(getColor(android.R.color.white))
            btnAnswer4.setBackgroundColor(getColor(android.R.color.white))
        }
    }

    private fun onClickViews() {
        with(binding) {
            btnFinish.setOnClickListener {
                presenter.stopTimer()
            }
            btnNext.setOnClickListener {
                presenter.clickBtnNextButton()
                sendOrStartQuiz()
            }
            btnAnswer1.setOnClickListener {
                changeButtonColorWhenAnswered(presenter.getUserAnswer1(), it as Button)
                presenter.clickBtnAnsweredButton(presenter.getUserAnswer1())
                waitTwoSecondAndNextQuiz()
            }
            btnAnswer2.setOnClickListener {
                changeButtonColorWhenAnswered(presenter.getUserAnswer2(), it as Button)
                presenter.clickBtnAnsweredButton(presenter.getUserAnswer2())
                waitTwoSecondAndNextQuiz()
            }
            btnAnswer3.setOnClickListener {
                changeButtonColorWhenAnswered(presenter.getUserAnswer3(), it as Button)
                presenter.clickBtnAnsweredButton(presenter.getUserAnswer3())
                waitTwoSecondAndNextQuiz()
            }
            btnAnswer4.setOnClickListener {
                changeButtonColorWhenAnswered(presenter.getUserAnswer4(), it as Button)
                presenter.clickBtnAnsweredButton(presenter.getUserAnswer4())
                waitTwoSecondAndNextQuiz()
            }
        }
    }

    private fun sendOrStartQuiz() {
        if (!presenter.isFinish()) {
            presenter.sendOrStartQuizToUI()
        } else {
            findNavController().navigate(
                R.id.action_quizFragment_to_finishFragment, bundleOf(
                    RESULT_DATA to presenter.getAnsweredQuizList()
                )
            )
        }
    }

    private fun waitTwoSecondAndNextQuiz() {
        Handler(Looper.getMainLooper()).postDelayed({
            sendOrStartQuiz()
        }, 2000L)
    }

    private fun changeButtonColorWhenAnswered(answer: String, button: Button) = with(binding) {
        val cardBgColor =
            if (presenter.isCorrectUserAnswered(answer)) getColor(android.R.color.holo_green_light)
            else getColor(android.R.color.holo_red_light)
        button.setBackgroundColor(cardBgColor)
    }

    companion object {
        const val RESULT_DATA = "result_data"
    }
}