package cat.raimon.gamesclock.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import cat.raimon.gamesclock.R
import cat.raimon.gamesclock.databinding.FragmentMenuBinding
import cat.raimon.gamesclock.databinding.FragmentRummyBinding
import com.google.android.material.snackbar.Snackbar

class MenuFragment : Fragment() {

    private lateinit var binding: FragmentMenuBinding

    private var listPlayers: MutableList<String> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_menu, container, false)

        binding.imgBttnRummy.setOnClickListener {
            openRummykub()
        }

        return binding.root
    }


    /**
     * Check if players ara in range 2 to 4
     * If user don't select the correct number, the listPlayers will be clear and function recalled
     */
    private fun openRummykub() {
        val countCheckBoxes = selectedCheckBoxes()
        val edTextNumber = binding.editTextNumber.text

        if (edTextNumber.isNotEmpty()) {
            if (edTextNumber.toString().toInt() >= 60) {
                val time = edTextNumber.toString().toLong()

                if (countCheckBoxes in 2..4) {
                    findNavController().navigate(
                        MenuFragmentDirections.actionMenuFragmentToRummyFragment(time, listPlayers.toTypedArray())
                    )
                } else {
                    listPlayers.clear()
                    view?.let {
                        Snackbar.make(
                            it,
                            R.string.checkboxe_snackbar,
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                }
            } else {
                view?.let {
                    Snackbar.make(
                        it,
                        R.string.minim_time,
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        } else {
            view?.let {
                Snackbar.make(
                    it,
                    R.string.select_time,
                    Snackbar.LENGTH_LONG
                ).show()
            }

        }
    }

    /**
     * Add checkboxes ckehed in a listPlayers
     */
    private fun selectedCheckBoxes(): Int {
        var countCheckBoxes = 0

        binding.apply {
            if (chbCrisB.isChecked) {
                countCheckBoxes++
                listPlayers.add(getString(R.string.cris_b))
            }
            if (chbCrisS.isChecked) {
                countCheckBoxes++
                listPlayers.add(getString(R.string.cris_s))
            }
            if (chbMare.isChecked) {
                countCheckBoxes++
                listPlayers.add(getString(R.string.mare))
            }
            if (chbPare.isChecked) {
                countCheckBoxes++
                listPlayers.add(getString(R.string.pare))
            }
            if (chbRaimon.isChecked) {
                countCheckBoxes++
                listPlayers.add(getString(R.string.raimon))
            }
            if (chbSanti.isChecked) {
                countCheckBoxes++
                listPlayers.add(getString(R.string.santi))
            }
            if (chbInvitat1.isChecked) {
                countCheckBoxes++
                listPlayers.add(getString(R.string.invitat_1))
            }
            if (chbInvitat2.isChecked) {
                countCheckBoxes++
                listPlayers.add(getString(R.string.invitat_2))
            }
            if (chbInvitat3.isChecked) {
                countCheckBoxes++
                listPlayers.add(getString(R.string.invitat_3))
            }
        }

        return countCheckBoxes
    }

}