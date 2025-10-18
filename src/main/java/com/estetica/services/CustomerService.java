package com.estetica.services;

import java.util.List;

import com.estetica.model.Customer;

/*
En esta interface vamos a implementar algunos metodos importantes para las otras
clases
*/

public interface CustomerService {
	
	//Primero, metodo que devolvera una lista de objetos de tipo cliente
    public List<Customer> listCustomer();
    
    //Metodo que va a devolver un objeto tipo Cliente, para buscarlos que recibe
    //objeto de tipo Cliente
    public Customer searchCustomerId(Integer idCustomer);
    
    /*
    Metodo para guardar cliente que recibe objeto tipo Cliente. Vamos a usar el mismo
    metodo tanto para guardar un cliente nuevo como para actualizar un cliente. La
    diferencia se realiza de manera interna ya que si el valor de la PK del objeto 
    cliente que esta recibiendo el metodo es igual a null, entonces se va a hacer una
    inserccion, pero si el valor es diferente de nulo entonces se va a realizar una
    actualizacion del registro. Esto es ventaja de usar JPA
    */
    public void saveCustomer(Customer customer);
    
    //Metodo para eliminar un cliente
    public void deleteCustomer(Integer idCustomer);

}
