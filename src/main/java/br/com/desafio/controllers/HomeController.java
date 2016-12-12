package br.com.desafio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.desafio.dao.TaskDao;
import br.com.desafio.models.Task;

/*
 * @author Renato Muniz
 * Controller do Spring Framework
 * Nessa classe esta contida as lógicas referente ao controle da requisições pelo SPRING MVC
 * A anotação @controller indica que a classe tem a função de controller
 * A anotação @transacional habilita o controle transacional que foi configurado na classe JPAConfiguration
 * 
 */

@Controller
@Transactional
public class HomeController {
	
	@Autowired
	TaskDao dao;
	
	@RequestMapping("/")
	public String index(){
		System.out.println("TESTANDO");
		return	"TaskManagement";
	}
	
	@RequestMapping(value="/salvar", method= RequestMethod.POST)
	public void salvar(@RequestBody Task task){
		if(task.getId() != null){
			dao.alterar(task);
		} else {
			dao.salvar(task);
		}
	}
	
	@RequestMapping(value="/carregaLista",method= RequestMethod.GET)
	public @ResponseBody List<Task> carregarLista(){
		System.out.println("PASAANDO POR AQUI");
		return dao.todasTasks();
	}
	
	@RequestMapping(value="/carregaTask/{codigo}",method= RequestMethod.GET)
	public @ResponseBody Task carregaTask(@PathVariable int codigo){
		Task task = dao.recuperarTask(codigo);
		return task;
	}
	
	@RequestMapping(value="/removerTask/{codigo}",method= RequestMethod.GET)
	public void removerTask(@PathVariable int codigo){
		dao.remover(codigo);
	}

}
