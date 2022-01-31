package cat.raimon.gamesclock.rummikub

import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import cat.raimon.gamesclock.MainActivity
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

        (requireActivity() as MainActivity).supportActionBar!!.hide()

        //Keep screen on
        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        //Pick up arguments
        args = RummyFragmentArgs.fromBundle(requireArguments())

        //Initialize media
        mediaPlayerBip = MediaPlayer.create(requireActivity(), R.raw.notification)
        mediaPlayer = MediaPlayer.create(requireActivity(), R.raw.end_tourn)

        //Suffle Players List
        val listPlayers = args.players.toList()

        displayPlayers(listPlayers)
        disconectUnusedButtons(listPlayers)
        viewClock(args.time.toLong() * 1000)
        timerFun(listPlayers)

        binding.bttnBack.setOnClickListener {
            findNavController().navigate(R.id.action_rummyFragment_to_menuFragment)
            mediaPlayer.stop()
            mediaPlayerBip.stop()
        }

        binding.bttnEndGame.setOnClickListener {
            findNavController().navigate(R.id.action_rummyFragment_to_menuFragment)
            mediaPlayer.stop()
            mediaPlayerBip.stop()
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
                txtPlayer1.setBackgroundResource(R.drawable.players_shape)
                txtPlayer3.setBackgroundResource(R.drawable.players_shape_red)

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
                txtPlayer3.setBackgroundResource(R.drawable.players_shape)
                txtPlayer1.setBackgroundResource(R.drawable.players_shape_red)

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
                txtPlayer1.setBackgroundResource(R.drawable.players_shape)
                txtPlayer3.setBackgroundResource(R.drawable.players_shape_red)

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
                txtPlayer3.setBackgroundResource(R.drawable.players_shape)
                txtPlayer4.setBackgroundResource(R.drawable.players_shape_red)

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
                txtPlayer4.setBackgroundResource(R.drawable.players_shape)
                txtPlayer1.setBackgroundResource(R.drawable.players_shape_red)

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
                txtPlayer1.setBackgroundResource(R.drawable.players_shape)
                txtPlayer3.setBackgroundResource(R.drawable.players_shape_red)

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
                txtPlayer3.setBackgroundResource(R.drawable.players_shape)
                txtPlayer4.setBackgroundResource(R.drawable.players_shape_red)

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
                txtPlayer4.setBackgroundResource(R.drawable.players_shape)
                txtPlayer2.setBackgroundResource(R.drawable.players_shape_red)

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
                txtPlayer2.setBackgroundResource(R.drawable.players_shape)
                txtPlayer1.setBackgroundResource(R.drawable.players_shape_red)

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

    private fun timerFun(listPlayers: List<String>) {
        val timer = object : CountDownTimer(args.time.toLong() * 1000, 1000) {


            override fun onTick(millisUntilFinished: Long) {

                viewClock(millisUntilFinished)

                if (millisUntilFinished in 37001..39999) {
                    mediaPlayerBip.start()
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

        binding.bttnRetry.setOnClickListener {
            mediaPlayer.stop()
            mediaPlayerBip.stop()

            mediaPlayer.prepare()
            mediaPlayerBip.prepare()

            timer.cancel()

            displayPlayers(listPlayers)
            disconectUnusedButtons(listPlayers)

            viewClock(args.time.toLong() * 1000)

            binding.apply {
                txtPlayer1.setBackgroundResource(R.drawable.players_shape)
                txtPlayer3.setBackgroundResource(R.drawable.players_shape)
                txtPlayer4.setBackgroundResource(R.drawable.players_shape)
                txtPlayer2.setBackgroundResource(R.drawable.players_shape)

                bttnPlayer1.isEnabled = true
                bttnPlayer3.isEnabled = true
                bttnPlayer4.isEnabled = true
                bttnPlayer2.isEnabled = true
            }
        }

    }

    private fun viewClock(millisUntilFinished: Long) {

        val minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished).toInt()
        val seconds =
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
        binding.apply {
            ttxTime1.text = timeClock
            txTime2.text = timeClock
            txTime2.rotation = 180F
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
                txtPlayer2.text = listPlayers[3]
                txtPlayer2.rotation = 180F
                txtPlayer4.text = listPlayers[2]
            }
        }
    }

    /**
     * Disable Back Button


    override fun onResume() {
        super.onResume()
        this.requireView().isFocusableInTouchMode = true
        this.requireView().requestFocus()
        this.requireView().setOnKeyListener { _, keyCode, _ ->
            keyCode == KeyEvent.KEYCODE_BACK
        }
    }
     */
}

