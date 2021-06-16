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
import kotlin.collections.MutableList as MutableList


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

        mediaPlayer = MediaPlayer.create(requireActivity(), R.raw.dead)

        args = RummyFragmentArgs.fromBundle(requireArguments())

        timerFun(args.time)

        val listPlayers = args.players.toList().shuffled()

        displayPlayers(listPlayers)

        return binding.root
    }

    private fun timerFun(time: Long) {

        val timer = object : CountDownTimer(time * 1000, 1000) {
            var minutes = 0
            var seconds = 0

            override fun onTick(millisUntilFinished: Long) {
                minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished).toInt()
                seconds =
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished).toInt() - minutes * 60

                var timeClock = ""

                timeClock = if (minutes < 10) {
                    if (seconds < 10) {
                        "0$minutes : 0$seconds"
                    } else {
                        "0$minutes : $seconds"
                    }
                } else {
                    if (seconds < 10) {
                        "$minutes : 0$seconds"
                    } else {
                        "$minutes : $seconds"
                    }
                }


                binding.apply {
                    ttxTime1.text = timeClock
                    txTime2.text = timeClock
                }
            }

            override fun onFinish() {
                mediaPlayer.start()
            }
        }
        timer.start()
    }

    private fun displayPlayers(listPlayers: List<String>) {

        binding.apply {
            txtPlayer1.text = listPlayers[0]
            txtPlayer3.text = listPlayers[1]
            txtPlayer1.rotation = 180F

            if (listPlayers.size == 3) {
                txtPlayer2.text = listPlayers[2]
                txtPlayer2.rotation = 180F
            }
            if (listPlayers.size == 4) {
                txtPlayer4.text = listPlayers[3]
            }
        }
    }
}