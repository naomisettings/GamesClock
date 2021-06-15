package cat.raimon.gamesclock.rummikub

import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import cat.raimon.gamesclock.R
import cat.raimon.gamesclock.databinding.FragmentLoginBinding
import cat.raimon.gamesclock.databinding.FragmentRummyBinding
import kotlin.concurrent.timer

class RummyFragment : Fragment() {

    lateinit var binding: FragmentRummyBinding
    lateinit var args: RummyFragmentArgs

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_rummy, container, false)

        args = RummyFragmentArgs.fromBundle(requireArguments())

        val listPlyers = args.listPlayers.toList()


        return binding.root
    }

    fun timerFun() {
        val timer = object: CountDownTimer(20000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.apply {
                    ttxTime1.text = millisUntilFinished.toString()
                    txTime2.text = millisUntilFinished.toString()
                }
            }

            override fun onFinish() {
                val mediaPlayer = MediaPlayer.create(requireContext(), R.drawable.Flow)
                mediaPlayer?.start()
            }
        }
        timer.start()
    }
}