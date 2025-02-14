package com.example.applistadetarefastlrf.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.applistadetarefastlrf.databinding.ItemTarefaBinding
import com.example.applistadetarefastlrf.model.Tarefa

class TarefaAdapter() : RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder>() {

    private var listaTarefas: List<Tarefa> = emptyList()

    fun adicionarLista(lista: List<Tarefa>) {
        this.listaTarefas = lista
        notifyDataSetChanged()
    }

    inner class TarefaViewHolder(itemBinding: ItemTarefaBinding)
        : RecyclerView.ViewHolder(itemBinding.root) {

        private val binding: ItemTarefaBinding

        init {
            binding = itemBinding
        }

        fun bind(tarefa : Tarefa){
            // Associação dos dados com o item da interface
            binding.textDescricao.text = tarefa.descricao
            binding.textData.text = tarefa.dataCadastro
        }

    }

    // Construir a interface
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarefaViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemTarefaBinding = ItemTarefaBinding.inflate(
            layoutInflater, parent, false
        )
        return TarefaViewHolder(itemTarefaBinding)
    }

    override fun onBindViewHolder(holder: TarefaViewHolder, position: Int) {
        // Pegar e passar a tarefa
        val tarefa = listaTarefas[position]
        holder.bind(tarefa)
    }

    override fun getItemCount(): Int {
        return listaTarefas.size
    }
}