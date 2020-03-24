package com.example.appexec01.senac;


class ValorFuturo (val meses: Double,
                   val taxaJuros: Double,
                   val capitalAtual: Double) {

    fun calculaValorFuturo() : Double {
        return capitalAtual * Math.pow(1 + taxaJuros / 100, meses)
    }
}