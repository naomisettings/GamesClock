package cat.raimon.gamesclock.rummikub

import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import cat.raimon.gamesclock.R
import cat.raimon.gamesclock.databinding.FragmentRummyBinding
import java.util.concurrent.TimeUnit


class RummyFragment : Fragment() {

    lateinit var binding: FragmentRummyBinding
    lateinit var args: RummyFragmentArgs

    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_rummy, container, false)

        args = RummyFragmentArgs.fromBundle(requireArguments())

        val listPlyers = args.listPlayers.toList()

        mediaPlayer = MediaPlayer.create(requireActivity(), R.raw.dead)

        timerFun()

        return binding.root
    }

    fun timerFun() {
        val timer = object: CountDownTimer(70000, 1000) {
            var minutes = 0
            var seconds = 0
            override fun onTick(millisUntilFinished: Long) {
                minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished).toInt()
                seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished).toInt() - minutes * 60

                binding.apply {
                    ttxTime1.text = "$minutes : $seconds"
                    txTime2.text = "$minutes : $seconds"
                }
            }

            override fun onFinish() {
                mediaPlayer.start()
            }
        }
        timer.start()
    }
}