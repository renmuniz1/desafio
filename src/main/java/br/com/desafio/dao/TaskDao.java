package br.com.desafio.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.desafio.models.Task;

/*
 * @author: Renato Muniz
 * 
 * CLASSE RESPONSÁVEL PELA PERSISTENCIA DO OBJETO TASK
 * 
 * */

@Repository
public class TaskDao {
	
	/*
	 *@PersistenceContext é onde fica armazenado as entidades manipuladas pelo EntityManager
	 * 
	 */
	
	@PersistenceContext
	private	EntityManager manager;
	
	
	/*
	 * @param task
	 * 
	 * Salva uu novo registro
	 * 
	 */
	
	public void salvar(Task task){
		manager.persist(task);
	}
	
	/*
	 * @param task
	 * 
	 * Atualiza um registro
	 * 
	 */
	
	public void alterar(Task task){
		manager.merge(task);
	}
	
	/*
	 *@return List<Task> 
	 * Recupera uma lista com todas as tasks existentes no banco 
	 * 
	 */
	
	public List<Task> todasTasks(){
		TypedQuery<Task> query = manager.createQuery("SELECT t FROM Task t ",Task.class);
		return query.getResultList();
	}
	
	/*
	 *@param id
	 *@return Task
	 * Recupera uma task especifica no banco 
	 * 
	 */
	
	public Task recuperarTask(Integer id){
		Task task = manager.find(Task.class, id);
		return task;
	}
	
	/*
	 *@param id
	 * Remove uma task especifica no banco 
	 * 
	 */
	
	public void remover(Integer id){
		Task task = manager.find(Task.class, id);
		manager.remove(task);
	}

}
