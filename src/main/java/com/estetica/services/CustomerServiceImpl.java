package com.estetica.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estetica.exceptions.DeletionNotAllowedException;
import com.estetica.exceptions.FailedSaveException;
import com.estetica.exceptions.ResourceNotFoundException;
import com.estetica.model.Customer;
import com.estetica.repository.CustomerRepository;

/*
Vamos a implementar la clase servicio la cual va a tirar de la interfaz. Importante
es añadir la anotacion @service porque es lo que hace que se conecte todo. Ya con esto
es parte de la fabrica de Spring. Una vez que tenemos la anotacion y el implements,
vamos a añadir una inyeccion de dependencia. En este caso, la inyeccion de dependencia
es que el repositorio lo vamos a necesitar en la capa de servicio para a su vez, podernos
comunicar con la base de datos, recuperar objetos de identidad y, finalmente, regresar
estos objetos de identidad a la capa de servicio. Para ello usamos la anotacion @autowired
que significa que se va a autoinyectar una referencia, en este caso, de la capa de datos
de la clase respositorio
*/

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
    //Vamos a crear un atributo privado donde el tipo de dato es CustomerRepository
	private CustomerRepository customerRepository;
	
	@Override
	public List<Customer> listCustomer() {
		/*
        Vamos a listar los cliente por lo que usamos las herramientas de Spring. Primero
        creamos una lista de objetos Cliente, y por medio del repositorio, usamos un
        metodo incluido que es findAll
        */
        List<Customer> customer = customerRepository.findAll();
        return customer; 
	}
	
	@Override
    public Customer searchCustomerId(Integer idCustomer) {
        /*
        Como no necesitamos recibir todos los datos del cliente, vamos a modificar el
        metodo en la clase CustomerServiceImpl para que ese metodo solo necesite un dato
        de tipo Integer y que sea el idCliente. Definimos una variable de cliente, y usando
        ña clase cleinteRepositorio vamos a llamar al metodo findById dandole el id
        del cliente que estamos buscando. Pero con este metodo vamos a recibir un dato
        de tipo Optional y entonces, para poder regresar un valor, mandamos llamar el
        metodo orElseo para indicar que en caso de que el cliente que estamos buscando
        no tenga registro en la BD, regrese null y sino, pues regresa el objeto cliente
        que haya encontrado
        */
        Customer customer = customerRepository.findById(idCustomer).orElseThrow(() -> new ResourceNotFoundException("Cliente "
        		+ "con id " + idCustomer + " no encontrado."));
        return customer;
    }
	
	@Override
    public void saveCustomer(Customer customer) {
		//Vamos a crear el metodo para guardar, capturando excepciones
		try {
			customerRepository.save(customer);
			System.out.println("Cliente guardado correctamente");
		} catch (FailedSaveException e) {
			System.err.println("Error al guardar el cliente: " + e.getMessage());
			throw e;
		} catch (Exception e) {
			System.err.println("Ocurrio un error inesperado al intentar guardar el cliente." + e.getMessage());
			throw new FailedSaveException("Error interno del servidor al guardar el cliente.", e);
		}
    }

    @Override
    public void deleteCustomer(Integer idCustomer) {
    	try {
			Customer customer = customerRepository.findById(idCustomer).orElseThrow(() -> new ResourceNotFoundException("Cliente"
					+ "no encontrado con ese id: " + idCustomer));
			customerRepository.delete(customer);
			System.out.println("Cliente eliminado correctamente, con id: " + idCustomer);
		} catch (ResourceNotFoundException e) {
			System.err.println("Error al eliminar: " + e.getMessage());
			throw e;
		} catch (DeletionNotAllowedException e) {
			System.err.println("Error al eliminar: " + e.getMessage());
			throw e;
		} catch (Exception e) {
			System.err.println("Ocurrio un error inesperado al intentar eliminar el cliente con id " + idCustomer + ": " + e.getMessage());
			throw new RuntimeException("Error interno del servidor al eliminar el cliente.", e);
		}
    }

}
