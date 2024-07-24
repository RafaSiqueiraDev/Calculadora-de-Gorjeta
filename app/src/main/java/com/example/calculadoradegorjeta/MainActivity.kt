package com.example.calculadoradegorjeta

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculadoradegorjeta.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var percentage: Int = 0

        binding.rbOptionone.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                 percentage = 10
            }
        }
        binding.rbOptionTwo.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                percentage = 15
            }
        }

        binding.rbOptionThree.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                percentage = 20

            }
        }

        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.num_people,
            android.R.layout.simple_spinner_item
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        binding.spinnerNumeroDePessoas.adapter = adapter

        var numOfPeopleSelected = 0

        binding.spinnerNumeroDePessoas.onItemSelectedListener =

            object :AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    numOfPeopleSelected = position
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

            }

        binding.calcular.setOnClickListener{

            val totalTableTemp = binding.tieTotal.text


            if(totalTableTemp?.isEmpty() ==true )
                 {

                Snackbar.make(binding.tieTotal,"Preencha todos os campos",Snackbar.LENGTH_LONG)
                    .show()

            }else{
                val totalTable: Float = totalTableTemp.toString().toFloat()
                val nPeople: Int = numOfPeopleSelected

                val totalTemp = totalTable / nPeople
                val tips = totalTemp * percentage /100
                val totalWithTips = totalTemp + tips

                binding.tvResult.text = "Total com gorjeta = $totalWithTips"

            }

            binding.Limpar.setOnClickListener{
                binding.tvResult.text =""
                binding.tieTotal.setText("")
                pos = 0
                binding.rbOptionone.isChecked = false
                binding.rbOptionTwo.isChecked = false
                binding.rbOptionThree.isChecked = false



            }
        }
    }
}