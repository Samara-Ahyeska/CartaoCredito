package com.example.contador
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // O contador sempre é inicializado em 0
    private var contador = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ligando os elementos do XML
        val textContador = findViewById<TextView>(R.id.textContador)
        val btnAumentar = findViewById<Button>(R.id.btnAumentar)
        val btnDiminuir = findViewById<Button>(R.id.btnDiminuir)
        val btnZerar = findViewById<Button>(R.id.btnZerar)

        // Funcionalidade para digitar o número ao clicar no texto do contador
        textContador.setOnClickListener {
            val input = EditText(this)
            input.inputType = InputType.TYPE_CLASS_NUMBER // Abre apenas o teclado numérico
            input.setText(contador.toString()) // Preenche com o valor atual
            input.setSelection(input.text.length) // Posiciona o cursor no fim

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Alterar Contador")
            builder.setMessage("Digite o novo valor:")
            builder.setView(input)

            builder.setPositiveButton("Confirmar") { dialog, _ ->
                val textoDigitado = input.text.toString()
                if (textoDigitado.isNotEmpty()) {
                    val numeroDigitado = textoDigitado.toInt()

                    // Regra: Valida se o número é negativo
                    if (numeroDigitado < 0) {
                        Toast.makeText(this, "Não é possível inserir um número negativo", Toast.LENGTH_SHORT).show()
                    } else {
                        contador = numeroDigitado
                        textContador.text = contador.toString()
                    }
                }
            }

            builder.setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }

            builder.show()
        }

        // Funcionalidade do botão Aumentar
        btnAumentar.setOnClickListener {
            contador++
            textContador.text = contador.toString()
        }

        // Funcionalidade do botão Diminuir (Não deixa baixar de 0)
        btnDiminuir.setOnClickListener {
            if (contador > 0) {
                contador--
                textContador.text = contador.toString()
            }
        }

        // Funcionalidade do botão Zerar (Com confirmação)
        btnZerar.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Confirmação")
            builder.setMessage("Tem certeza de que deseja zerar o contador?")

            builder.setPositiveButton("Sim") { _, _ ->
                contador = 0
                textContador.text = contador.toString()
            }

            builder.setNegativeButton("Não") { dialog, _ ->
                dialog.dismiss()
            }

            builder.show()
        }
    }
}