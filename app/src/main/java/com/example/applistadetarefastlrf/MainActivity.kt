package com.example.applistadetarefastlrf

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.applistadetarefastlrf.adapter.TarefaAdapter
import com.example.applistadetarefastlrf.database.TarefaDAO
import com.example.applistadetarefastlrf.databinding.ActivityMainBinding
import com.example.applistadetarefastlrf.model.Tarefa

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    var listaDeTarefas = emptyList<Tarefa>()
    private var tarefaAdapter: TarefaAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.fabAdicionar.setOnClickListener {
            val intent = Intent(this, AdicionarTarefaActivity::class.java)
            startActivity(intent)
        }

        // Recyclerview
        tarefaAdapter = TarefaAdapter(
            { id -> confirmarExclusao(id) },
            {tarefa -> editar(tarefa) }
        )
        binding.rvTarefas.adapter = tarefaAdapter

        binding.rvTarefas.layoutManager = LinearLayoutManager(this)
    }

    private fun editar(tarefa: Tarefa) {
        val intent = Intent(this, AdicionarTarefaActivity::class.java)
        intent.putExtra("tarefa", tarefa)
        startActivity(intent)
    }

    private fun confirmarExclusao(id: Int) {
        val alertBuilder = AlertDialog.Builder(this)

        alertBuilder.setTitle("Confirmar exclusão")
        alertBuilder.setMessage("Deseja realmente excluir a tarefa?")

        alertBuilder.setPositiveButton("Sim"){_, _ ->
            val tarefaDAO = TarefaDAO(this)
            if(tarefaDAO.deletar(id)){
                atualizarListaDeTarefas()
                Toast.makeText(this, "Sucesso ao remover tarefa", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Erro ao remover tarefa", Toast.LENGTH_SHORT).show()
            }
        }

        alertBuilder.setNeutralButton("Não"){_, _ ->}

        alertBuilder.create().show()
    }

    private fun atualizarListaDeTarefas(){
        val tarefaDAO = TarefaDAO(this)
        listaDeTarefas = tarefaDAO.listar()
        tarefaAdapter?.adicionarLista(listaDeTarefas)
    }

    override fun onStart() {
        super.onStart()
        atualizarListaDeTarefas()
    }
}