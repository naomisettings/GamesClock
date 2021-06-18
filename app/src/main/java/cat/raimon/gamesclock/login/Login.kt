package cat.raimon.gamesclock.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import cat.raimon.gamesclock.R
import cat.raimon.gamesclock.databinding.FragmentLoginBinding
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth

private const val AUTH_REQUEST_CODE = 2002


@Suppress("DEPRECATION")
class Login : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        //Call function login
        login()

        //Navigate to Menu Fragment
        binding.bttnSelectGame.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_menuFragment)
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


}