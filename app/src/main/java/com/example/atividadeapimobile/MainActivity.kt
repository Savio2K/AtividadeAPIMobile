package com.example.atividadeapimobile

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.util.Locale

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Referências dos componentes visuais da tela
        val etCoinInput = findViewById<EditText>(R.id.etCoinInput)
        val btnSearch = findViewById<Button>(R.id.btnSearch)
        val tvResult = findViewById<TextView>(R.id.tvResult)

        btnSearch.setOnClickListener {
            // Pega o texto e transforma tudo em letras maiúsculas
            val coinCode = etCoinInput.text.toString().trim().uppercase(Locale.getDefault())

            // REQUISITO: Validação de campo vazio
            if (coinCode.isEmpty()) {
                Toast.makeText(this, "Por favor, informe a sigla de uma moeda!", Toast.LENGTH_SHORT).show()
                tvResult.text = ""
                return@setOnClickListener
            }

            // REQUISITO: Montagem da URL dinâmica e requisição HTTP real
            // Essa API retorna a moeda desejada em relação ao Real brasileiro (BRL)
            val url = "https://economia.awesomeapi.com.br/json/last/$coinCode-BRL"
            val queue = Volley.newRequestQueue(this)

            val jsonObjectRequest = JsonObjectRequest(
                Request.Method.GET, url, null,
                { response: JSONObject ->
                    try {
                        // A API retorna um objeto com o nome da combinação, ex: "USDBRL"
                        val key = "${coinCode}BRL"

                        if (response.has(key)) {
                            val coinData = response.getJSONObject(key)

                            // REQUISITO: Leitura do JSON e extração de dados úteis
                            val name = coinData.getString("name")        // Nome completo (ex: Dólar Americano/Real Brasileiro)
                            val bid = coinData.getString("bid")          // Valor de compra
                            val ask = coinData.getString("ask")          // Valor de venda
                            val pctChange = coinData.getString("pctChange") // Variação percentual do dia

                            // REQUISITO: Exibição estruturada e legível para o usuário
                            val formattedResult = """
                                💵 Cotação do Dia Encontrada:
                                
                                • Moeda: $name
                                • Valor de Compra: R$ $bid
                                • Valor de Venda: R$ $ask
                                • Variação Diária: $pctChange%
                            """.trimIndent()

                            tvResult.text = formattedResult
                        } else {
                            tvResult.text = "Dados não encontrados para a sigla informada."
                        }
                    } catch (e: Exception) {
                        tvResult.text = "Erro ao ler as informações do servidor."
                    }
                },
                { error ->
                    // REQUISITO: Tratamento de erro amigável (como moeda inexistente ou falha de rede)
                    if (error.networkResponse != null && error.networkResponse.statusCode == 404) {
                        tvResult.text = "Sigla inválida! Use siglas conhecidas como USD, EUR, BTC, GBP ou ARS."
                    } else {
                        tvResult.text = "Não foi possível conectar ao servidor de cotações. Verifique sua rede."
                    }
                }
            )

            // Envia a requisição para a fila do Volley
            queue.add(jsonObjectRequest)
        }
    }
}