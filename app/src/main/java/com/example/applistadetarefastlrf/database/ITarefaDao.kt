package com.example.applistadetarefastlrf.database

import com.example.applistadetarefastlrf.model.Tarefa

interface ITarefaDao {
    fun salvar( tarefa: Tarefa): Boolean
    fun atualizar( tarefa: Tarefa ): Boolean
    fun deletar( id: Int ): Boolean
    fun listar(): List<Tarefa>
}