package com.example.applistadetarefastlrf

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.applistadetarefastlrf.database.TarefaDAO
import com.example.applistadetarefastlrf.databinding.ActivityAdicionarTarefaBinding
import com.example.applistadetarefastlrf.model.Tarefa

class AdicionarTarefaActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityAdicionarTarefaBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Recuperar a tarefa passada no atualizar
        var tarefa: Tarefa? = null
        val bundle = intent.extras
        if(bundle!=null) {
            tarefa = bundle.getSerializable("tarefa") as Tarefa
            binding.editTarefa.setText(tarefa.descricao)
        }
        // Fim do Recuperar

        binding.btnSalvar.setOnClickListener {
            if(binding.editTarefa.text.isNotEmpty()){
                if(tarefa != null){
                    editar(tarefa)
                } else {
                    salvar()
                }
            } else {
                Toast.makeText(this, "Preencha uma tarefa...", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun editar(tarefa: Tarefa) {
        val descricao = binding.editTarefa.text.toString()
        val tarefaAtualizar = Tarefa(
            tarefa.idTarefa, descricao, "default"
        )
        val tarefaDAO = TarefaDAO(this)
        if(tarefaDAO.atualizar(tarefaAtualizar)){
            Toast.makeText(this, "Tarefa atualizada com sucesso", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun salvar() {
        val descricao = binding.editTarefa.text.toString()
        val tarefa = Tarefa(
            -1, descricao, "default"
        )

        val tarefaDAO = TarefaDAO(this)
        if (tarefaDAO.salvar(tarefa)) {
            Toast.makeText(this, "Tarefa cadastrada com sucesso", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}