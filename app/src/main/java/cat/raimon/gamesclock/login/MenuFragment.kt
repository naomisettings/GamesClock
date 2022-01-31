package cat.raimon.gamesclock.login

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import cat.raimon.gamesclock.MainActivity
import cat.raimon.gamesclock.R
import cat.raimon.gamesclock.databinding.FragmentMenuBinding
import com.google.android.material.snackbar.Snackbar

class MenuFragment : Fragment() {

    private lateinit var binding: FragmentMenuBinding

    private var listPlayers: MutableList<String> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_menu, container, false)

        binding.imgBttnRummy.setOnClickListener {

            editGet()

            openRummykub()
        }

        return binding.root
    }


    private fun editGet() {
        val player1 = binding.edply1.text.toString()
        val player2 = binding.edply2.text.toString()
        val player3 = binding.edply3.text.toString()
        val player4 = binding.edply4.text.toString()

        if (player1.isNotBlank() || player1.isNotEmpty()) {
            listPlayers.add("  $player1")
        }
        if (player2.isNotBlank() || player2.isNotEmpty()) {
            listPlayers.add("  $player2")
        }
        if (player3.isNotBlank() || player3.isNotEmpty()) {
            listPlayers.add("  $player3")
        }
        if (player4.isNotBlank() || player4.isNotEmpty()) {
            listPlayers.add("  $player4")
            hideKeyboard()
        }

    }

    /**
     * Check if players ara in range 2 to 4
     * If user don't select the correct number, the listPlayers will be clear and function recalled
     */
    private fun openRummykub() {

        var time = 0
        if (binding.editTextNumber.text.isNotEmpty()) {
            if (binding.editTextNumber.text.toString().toInt() >= 60) {

                time = binding.editTextNumber.text.toString().toInt()

                if (listPlayers.size >= 2) {
                    findNavController().navigate(
                        MenuFragmentDirections.actionMenuFragmentToRummyFragment(
                            time,
                            listPlayers.toTypedArray()
                        )
                    )
                    hideKeyboard()
                } else {
                    listPlayers.clear()
                    hideKeyboard()
                    view?.let {
                        Snackbar.make(
                            it,
                            R.string.checkboxe_snackbar,
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                }
            } else {
                listPlayers.clear()
                hideKeyboard()
                binding.editTextNumber.setText("")
                view?.let {
                    Snackbar.make(
                        it,
                        R.string.minim_time,
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        } else {
            listPlayers.clear()

            view?.let {
                Snackbar.make(
                    it,
                    R.string.select_time,
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }
}

/**
 * Hide KKeyboard
 */
private fun Context.hideKeyboard(view: View) {
    val inputMethodManager =
        getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

private fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}
