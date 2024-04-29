package uz.androbeck.quiz.main_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.androbeck.quiz.R
import uz.androbeck.quiz.databinding.FragmentMainBinding

class MainFragment : Fragment(), MainContract.View {
    private lateinit var binding: FragmentMainBinding
    private lateinit var presenter: MainPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        presenter = MainPresenter(this, MainModel())

        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnStart.setOnClickListener {
            presenter.toQuizScreen()
        }
    }

    override fun toQuizScreen() {
        findNavController().navigate(R.id.action_mainFragment_to_quizFragment)
    }
}