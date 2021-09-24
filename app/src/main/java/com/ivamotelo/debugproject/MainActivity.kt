/*
Importante ressalvar que as classes de execções devem ser compatíveis
com o tipo de execção, senão ocorrerá erro de tratamento
como trocar um erro "runtime" por um erro de "cast"
*/

package com.ivamotelo.debugproject

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setLogListeners()

        val currentThread = Thread.currentThread()
        currentThread.setUncaughtExceptionHandler { _, throwable ->
            //todo implemente aqui seu exception handler
            val x = throwable.message
            val cause = throwable.cause
        }
    }

    private fun setLogListeners() {
        var dcontador = 0
        var econtador = 0
        var icontador = 0
        var vcontador = 0
        var wcontador = 0

        //todo altere os logs e adicione mais possibilidade de exceptions
        btn_debug?.setOnClickListener {
            Log.d("DEBUG", "Teste botão debug + $dcontador")
            dcontador++
            val message = getString(R.string.message_debug)
            edt_message?.setText(message)

        }
        btn_error?.setOnClickListener {
            Log.e("ERROR", "Teste botão error + $econtador")
            econtador++
            try {
                val list = listOf(2, 1, 4)   // ìndex correto é lista com 5 elementos
                var listIndex = list[5]
            } catch (e: IndexOutOfBoundsException) {
                var x = listOf(1, 2, 3, 4, 5)
                val message: String = getString(R.string.lista_index)
                edt_message?.setText(message)
            }
        }

        btn_info?.setOnClickListener {
            Log.i("INFO", "Teste botão info + $icontador")
            icontador++
            try {
                var x: String? = null!!       // String não é nulo, é vazio
            } catch (i: NullPointerException) {
                var x = ""
                val message: String = getString(R.string.valor_nulo)
                edt_message?.setText(message)
            }
        }

        btn_warning?.setOnClickListener {
            Log.w("WARN", "Teste botão Warning + $wcontador")
            wcontador++
            try {
                val view: View = btn_warning
                var btn = view as EditText     // view é um Button
            } catch (w: ClassCastException) {
                var view: View = btn_warning
                val message: String = getString(R.string.valor_cast)
                edt_message?.setText(message)
            }
        }

        btn_verbose?.setOnClickListener {
            Log.v("VERBO", "Teste botão verbose + $vcontador")
            vcontador++
            try {
                val x = "1.0"
                x.toInt()                   // valor deve ser String e não inteiro
            } catch (v: RuntimeException) {
                var x = ""
                val message: String = getString(R.string.valor_runtime)
                edt_message?.setText(message)
            } finally {
                val message: String = getString(R.string.valor_finally)
                val x = 50
            }
        }
    }
}