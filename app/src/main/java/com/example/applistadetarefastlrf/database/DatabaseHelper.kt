package com.example.applistadetarefastlrf.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper(context: Context) : SQLiteOpenHelper(
    context, NOME_BANCO_DE_DADOS, null, VERSAO
) {
    companion object {
        const val NOME_BANCO_DE_DADOS = "ListaTarefas.db"
        const val VERSAO = 1

        const val TABELA_TAREFAS = "tarefas"

        //Colunas tabela tarefas
        const val ID_TAREFA = "id_tarefa"
        const val DESCRICAO = "descricao"
        const val DATA_CRIACAO = "data_criacao"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val sqlTabela = "CREATE TABLE IF NOT EXISTS $TABELA_TAREFAS (" +
                "$ID_TAREFA INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "$DESCRICAO VARCHAR(70) NOT NULL," +
                "$DATA_CRIACAO DATETIME DEFAULT CURRENT_TIMESTAMP" +
                ");"
        try {
            db?.execSQL( sqlTabela )
            Log.i("info_db", "Sucesso ao criar a tabela")
        } catch (e: Exception){
            Log.i("info_db", "erro ao criar tabela ${e.message}")
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}