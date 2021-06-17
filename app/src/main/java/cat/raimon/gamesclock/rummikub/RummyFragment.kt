package cat.raimon.gamesclock.rummikub

import android.graphics.Color
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

    private lateinit var binding: FragmentRummyBinding
    private lateinit var args: RummyFragmentArgs

    private lateinit var mediaPlayerBip: MediaPlayer
    private lateinit var mediaPlayer: MediaPlayer


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_rummy, container, false)

        mediaPlayerBip = MediaPlayer.create(requireActivity(), R.raw.notification)
        mediaPlayer = MediaPlayer.create(requireActivity(), R.raw.end_tourn)

        args = RummyFragmentArgs.fromBundle(requireArguments())

        timerFun(args.time)

        val listPlayers = args.players.toList().shuffled()

        displayPlayers(listPlayers)
        playerTurn(listPlayers)

        return binding.root
    }

    private fun playerTurn(listPlayers: List<String>) {

        if (listPlayers.size == 2){
            binding.apply {
                bttnPlayer2.isEnabled = false
                bttnPlayer4.isEnabled = false
                bttnPlayer2.setColorFilter(Color.rgb(211, 209, 209))
                bttnPlayer4.setColorFilter(Color.rgb(211, 209, 209))
            }
            for ((i, x) in listPlayers.withIndex()) {
                //listPlayers[i]
                /**
                 * implement
                 */
            }
        }else if (listPlayers.size == 3){
            binding.apply {
                bttnPlayer4.isEnabled = false
                bttnPlayer4.setColorFilter(Color.rgb(211, 209, 209))
            }
        }else{

        }


    }

    private fun timerFun(time: Long) {

        val timer = object : CountDownTimer(time * 1000, 1000) {
            var minutes = 0
            var seconds = 0

            override fun onTick(millisUntilFinished: Long) {
                minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished).toInt()
                seconds =
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished).toInt() - minutes * 60

                val timeClock: String = if (minutes < 10) {
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

                if (millisUntilFinished in 37001..39999) {
                    mediaPlayerBip.start()
                }


                binding.apply {
                    ttxTime1.text = timeClock
                    ttxTime1.rotation = 180F
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
                txtPlayer2.text = listPlayers[2]
                txtPlayer2.rotation = 180F
                txtPlayer4.text = listPlayers[3]
            }
        }
    }
}