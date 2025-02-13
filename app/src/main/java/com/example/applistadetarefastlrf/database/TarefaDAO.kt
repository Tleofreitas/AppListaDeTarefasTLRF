package com.example.applistadetarefastlrf.database

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.example.applistadetarefastlrf.model.Tarefa

class TarefaDAO(context: Context) : ITarefaDao {

    private val escrita = DatabaseHelper(context).writableDatabase
    private val leitura = DatabaseHelper(context).readableDatabase

    override fun salvar(tarefa: Tarefa): Boolean {
        val conteudos = ContentValues()
        conteudos.put("${DatabaseHelper.DESCRICAO}", tarefa.descricao)

        try {
            escrita.insert(
                DatabaseHelper.TABELA_TAREFAS,
                null, conteudos
                )
            Log.i("info_db", "Sucesso ao salvar tarefa")
        } catch (e: Exception){
            Log.i("info_db", "erro ao salvar tarefa")
            return false
        }
        return true
    }

    override fun atualizar(tarefa: Tarefa): Boolean {
        TODO("Not yet implemented")
    }

    override fun deletar(id: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun listar(): List<Tarefa> {
        TODO("Not yet implemented")
    }
}