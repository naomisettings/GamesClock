package cat.raimon.gamesclock.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import cat.raimon.gamesclock.R
import cat.raimon.gamesclock.databinding.FragmentLoginBinding
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth

private const val AUTH_REQUEST_CODE = 2002


@Suppress("DEPRECATION")
class Login : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private var listPlayers: MutableList<String> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        //Call function login
        login()

        binding.imgBttnRummy.setOnClickListener {
            openRummykub()

        }

        return binding.root
    }


    /**
     * Login to firebase
     */
    private fun login() {
        //Suppliers (Google, Mail...)
        val suppliers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build(),
        )
        //Open an activityForResult
        startActivityForResult(
            AuthUI.getInstance().createSignInIntentBuilder()
                .setAvailableProviders(suppliers)
                .setLogo(R.drawable.logo)
                .build(),
            AUTH_REQUEST_CODE
        )
    }

    /**
     * Check if AUTH_REQUEST_CODE is OK
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == AUTH_REQUEST_CODE) {
            FirebaseAuth.getInstance().currentUser
        }
    }

    /**
     * Check if players ara in range 2 to 4
     * If user don't select the correct number, the listPlayers will be clear and function recalled
     */
    private fun openRummykub() {
        val countCheckBoxes = selectedCheckBoxes()
        if (binding.editTextNumber.text.isNotEmpty()) {
            val time = binding.editTextNumber.text.toString().toLong()

            if (countCheckBoxes in 2..4) {
                findNavController().navigate(
                    LoginDirections.actionLoginToRummyFragment(time, listPlayers.toTypedArray())
                )
            } else {
                listPlayers.clear()
            }
        }else{
            /**
             * SNACKBAR
             */
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