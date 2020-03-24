package com.example.appexec01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.appexec01.senac.ValorFuturo

class MainActivity : AppCompatActivity() {

    private lateinit var taxaJuros : EditText
    private lateinit var capitalAtual: EditText
    private lateinit var numeroMeses: SeekBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numeroMeses = findViewById<SeekBar>(R.id.seekBar1)
        taxaJuros = findViewById<EditText>(R.id.et_taxaJuros)
        capitalAtual = findViewById<EditText>(R.id.et_capitalAtual)

       numeroMeses.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                Toast.makeText(applicationContext,"Quantidade de Meses: $i",Toast.LENGTH_SHORT).show()
            }

           override fun onStartTrackingTouch(seekBar: SeekBar?) {
           }

           override fun onStopTrackingTouch(seekBar: SeekBar?) {
           }
        })

        val btn = findViewById<Button>(R.id.bt_calcular)
        btn.setOnClickListener {
            if (validate()) {

                val valor = ValorFuturo(
                        numeroMeses.getProgress().toDouble(),
                        taxaJuros.text.toString().toDouble(),
                        capitalAtual.text.toString().toDouble()
                )

                val valorFormatado =
                        String.format("%.2f", valor.calculaValorFuturo())

                AlertDialog.Builder(this)
                        .setTitle("Valor Futuro")
                        .setMessage("Seu Valor Futuro é R$ ${valorFormatado} ")
                        .setPositiveButton("OK") { dialog, which -> dialog.dismiss()
                        }.show()
            }
        }
    }

    private fun validate() : Boolean {
        var result = true
        if (taxaJuros.getText().trim().length == 0) {
            taxaJuros.setError("Campo é obrigatório")
            result = false
        }
        if (capitalAtual.text.trim().length == 0) {
            capitalAtual.setError("Campo é obrigatório")
            result = false
        }
        return result
    }
}
