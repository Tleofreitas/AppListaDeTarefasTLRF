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
        val args = arrayOf(id.toString())
        try {
            escrita.delete(
                DatabaseHelper.TABELA_TAREFAS, "${DatabaseHelper.ID_TAREFA} = ?",
                args
            )
            Log.i("info_db", "Sucesso ao remover tarefa")
        } catch (e: Exception){
            Log.i("info_db", "erro ao remover tarefa")
            return false
        }
        return true
    }

    override fun listar(): List<Tarefa> {
        val listaDeTarefas = mutableListOf<Tarefa>()

        val sql = "SELECT ${DatabaseHelper.ID_TAREFA}, " +
                "${DatabaseHelper.DESCRICAO}, " +
                "    strftime('%d/%m/%Y %H:%M', ${DatabaseHelper.DATA_CRIACAO}) ${DatabaseHelper.DATA_CRIACAO} " +
                "FROM ${DatabaseHelper.TABELA_TAREFAS}"

        val cursor = leitura.rawQuery(sql, null)

        val indiceId = cursor.getColumnIndex( DatabaseHelper.ID_TAREFA )
        val indiceDescricao = cursor.getColumnIndex( DatabaseHelper.DESCRICAO )
        val indiceData = cursor.getColumnIndex( DatabaseHelper.DATA_CRIACAO )

        while ( cursor.moveToNext() ){
            val idTarefa = cursor.getInt( indiceId )
            val descricao = cursor.getString( indiceDescricao )
            val data = cursor.getString( indiceData )

            listaDeTarefas.add(
                Tarefa(idTarefa, descricao, data)
            )
        }

        return listaDeTarefas
    }
}