package cat.raimon.gamesclock.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import cat.raimon.gamesclock.R
import cat.raimon.gamesclock.databinding.FragmentLoginBinding
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth

private const val AUTH_REQUEST_CODE = 2002

class Login : Fragment() {
    lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        login()

        return binding.root
    }

    //login firebase
    private fun login() {
        //suppliers (Google, Mail...)
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

    //Check if AUTH_REQUEST_CODE is OK
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == AUTH_REQUEST_CODE) {
            FirebaseAuth.getInstance().currentUser
        }
    }

}