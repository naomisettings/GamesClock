package cat.raimon.gamesclock.rummikub

import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
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

        args = RummyFragmentArgs.fromBundle(requireArguments())

        mediaPlayerBip = MediaPlayer.create(requireActivity(), R.raw.notification)
        mediaPlayer = MediaPlayer.create(requireActivity(), R.raw.end_tourn)

        val listPlayers = args.players.toList().shuffled()

        displayPlayers(listPlayers)
        disconectUnusedButtons(listPlayers)

        timerFun()

        binding.bttnEndGame.setOnClickListener {

        }

        return binding.root
    }

    private fun disconectUnusedButtons(listPlayers: List<String>) {

        if (listPlayers.size == 2) {
            binding.apply {
                bttnPlayer2.isEnabled = false
                bttnPlayer4.isEnabled = false
                bttnPlayer2.setColorFilter(Color.rgb(211, 209, 209))
                bttnPlayer4.setColorFilter(Color.rgb(211, 209, 209))
            }
        } else if (listPlayers.size == 3) {
            binding.apply {
                bttnPlayer2.isEnabled = false
                bttnPlayer2.setColorFilter(Color.rgb(211, 209, 209))
            }
        }

    }

    private fun playerTurnTwo(timer: CountDownTimer) {

        binding.bttnPlayer1.setOnClickListener {
            binding.apply {
                txtPlayer1.setBackgroundColor(Color.rgb(0, 74, 124))
                txtPlayer3.setBackgroundColor(Color.rgb(245, 92, 71))

                bttnPlayer3.isEnabled = true

                bttnPlayer1.isEnabled = false
                bttnPlayer2.isEnabled = false
                bttnPlayer4.isEnabled = false
            }
            timer.cancel()
            if (mediaPlayer.isPlaying) {
                mediaPlayer.stop()
                mediaPlayer.prepare()
            }
            timer.start()
        }
        binding.bttnPlayer3.setOnClickListener {
            binding.apply {
                txtPlayer3.setBackgroundColor(Color.rgb(0, 74, 124))
                txtPlayer1.setBackgroundColor(Color.rgb(245, 92, 71))

                bttnPlayer1.isEnabled = true

                bttnPlayer4.isEnabled = false
                bttnPlayer2.isEnabled = false
                bttnPlayer3.isEnabled = false
            }
            timer.cancel()
            if (mediaPlayer.isPlaying) {
                mediaPlayer.stop()
                mediaPlayer.prepare()
            }
            timer.start()
        }

    }

    private fun playerTurnThree(timer: CountDownTimer) {

        binding.bttnPlayer1.setOnClickListener {
            binding.apply {
                txtPlayer1.setBackgroundColor(Color.rgb(0, 74, 124))
                txtPlayer3.setBackgroundColor(Color.rgb(245, 92, 71))

                bttnPlayer3.isEnabled = true

                bttnPlayer1.isEnabled = false
                bttnPlayer2.isEnabled = false
                bttnPlayer4.isEnabled = false
            }
            timer.cancel()
            if (mediaPlayer.isPlaying) {
                mediaPlayer.stop()
                mediaPlayer.prepare()
            }
            timer.start()
        }
        binding.bttnPlayer3.setOnClickListener {
            binding.apply {
                txtPlayer3.setBackgroundColor(Color.rgb(0, 74, 124))
                txtPlayer4.setBackgroundColor(Color.rgb(245, 92, 71))

                bttnPlayer4.isEnabled = true

                bttnPlayer1.isEnabled = false
                bttnPlayer2.isEnabled = false
                bttnPlayer3.isEnabled = false
            }
            timer.cancel()
            if (mediaPlayer.isPlaying) {
                mediaPlayer.stop()
                mediaPlayer.prepare()
            }
            timer.start()
        }

        binding.bttnPlayer4.setOnClickListener {
            binding.apply {
                txtPlayer4.setBackgroundColor(Color.rgb(0, 74, 124))
                txtPlayer1.setBackgroundColor(Color.rgb(245, 92, 71))

                bttnPlayer1.isEnabled = true

                bttnPlayer3.isEnabled = false
                bttnPlayer2.isEnabled = false
                bttnPlayer4.isEnabled = false
            }
            timer.cancel()
            if (mediaPlayer.isPlaying) {
                mediaPlayer.stop()
                mediaPlayer.prepare()
            }
            timer.start()
        }

    }
    private fun playerTurnFour(timer: CountDownTimer) {

        binding.bttnPlayer1.setOnClickListener {
            binding.apply {
                txtPlayer1.setBackgroundColor(Color.rgb(0, 74, 124))
                txtPlayer3.setBackgroundColor(Color.rgb(245, 92, 71))

                bttnPlayer3.isEnabled = true

                bttnPlayer1.isEnabled = false
                bttnPlayer2.isEnabled = false
                bttnPlayer4.isEnabled = false
            }
            timer.cancel()
            if (mediaPlayer.isPlaying) {
                mediaPlayer.stop()
                mediaPlayer.prepare()
            }
            timer.start()
        }
        binding.bttnPlayer3.setOnClickListener {
            binding.apply {
                txtPlayer3.setBackgroundColor(Color.rgb(0, 74, 124))
                txtPlayer4.setBackgroundColor(Color.rgb(245, 92, 71))

                bttnPlayer4.isEnabled = true

                bttnPlayer1.isEnabled = false
                bttnPlayer2.isEnabled = false
                bttnPlayer3.isEnabled = false
            }
            timer.cancel()
            if (mediaPlayer.isPlaying) {
                mediaPlayer.stop()
                mediaPlayer.prepare()
            }
            timer.start()
        }

        binding.bttnPlayer4.setOnClickListener {
            binding.apply {
                txtPlayer4.setBackgroundColor(Color.rgb(0, 74, 124))
                txtPlayer2.setBackgroundColor(Color.rgb(245, 92, 71))

                bttnPlayer2.isEnabled = true

                bttnPlayer3.isEnabled = false
                bttnPlayer1.isEnabled = false
                bttnPlayer4.isEnabled = false
            }
            timer.cancel()
            if (mediaPlayer.isPlaying) {
                mediaPlayer.stop()
                mediaPlayer.prepare()
            }
            timer.start()
        }
        binding.bttnPlayer2.setOnClickListener {
            binding.apply {
                txtPlayer2.setBackgroundColor(Color.rgb(0, 74, 124))
                txtPlayer1.setBackgroundColor(Color.rgb(245, 92, 71))

                bttnPlayer1.isEnabled = true

                bttnPlayer2.isEnabled = false
                bttnPlayer3.isEnabled = false
                bttnPlayer4.isEnabled = false
            }
            timer.cancel()
            if (mediaPlayer.isPlaying) {
                mediaPlayer.stop()
                mediaPlayer.prepare()
            }
            timer.start()
        }
    }
    private fun timerFun() {
        val timer = object : CountDownTimer(2 * 1000, 1000) {
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
                    txTime2.text = timeClock
                    txTime2.rotation = 180F
                }

            }

            override fun onFinish() {
                mediaPlayer.start()
            }
        }
        when (args.players.size) {
            2 -> playerTurnTwo(timer)
            3 -> playerTurnThree(timer)
            4 -> playerTurnFour(timer)
        }

    }

    private fun displayPlayers(listPlayers: List<String>) {

        binding.apply {
            txtPlayer1.text = listPlayers[0]
            txtPlayer3.text = listPlayers[1]
            txtPlayer1.rotation = 180F

            if (listPlayers.size == 3) {
                txtPlayer4.text = listPlayers[2]
                txtPlayer4.rotation = 180F
            }
            if (listPlayers.size == 4) {
                txtPlayer2.text = listPlayers[2]
                txtPlayer2.rotation = 180F
                txtPlayer4.text = listPlayers[3]
            }
        }
    }
}