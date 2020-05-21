package comnathaliaspinula.consultacep

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.gson.Gson
import comnathaliaspinula.consultacep.Models.Endereco
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import java.net.URL

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_cep)
    }

    fun consultaCep(v: View) {
        val cep = cepText.text.toString()
        if(cep != "")
        {
            try {
                Thread(Runnable {
                    var url = "https://viacep.com.br/ws/$cep/json"
                    var response = URL(url).readText()
                    var endereco = Gson().fromJson(response, Endereco::class.java)
                    this@MainActivity.runOnUiThread {
                        resultTextView.text = endereco.logradouro
                    }
                }).start()
            } catch (e :Exception) {
                Toast.makeText(this, "Ocorreu um erro", Toast.LENGTH_SHORT).show()
            }

        } else {
            resultTextView.text = "Digite um CEP"
        }
    }

    fun isInternetOk(context: Context): Boolean {
        return true
    }
}