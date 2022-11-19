package com.example.Cliente.controller;

import com.example.Cliente.entity.Cliente;
import com.example.Cliente.repository.ClienteRepository;
import com.example.Cliente.utils.Calculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value ="/cliente/v1/")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private Calculo calcs;

    @PostMapping
    @ResponseBody
    public Cliente create(@RequestBody Cliente cliente){
        Cliente clienteSocializado = calcs.calc(cliente);
        Cliente clienteSaved = clienteRepository.save(cliente);
        return clienteSaved;
    }

    @GetMapping("{id}")
    @ResponseBody
    public Optional<Cliente> getClienteById(@PathVariable Long id) {
        Optional<Cliente> clienteReturned = clienteRepository.findById(id);
        return clienteReturned;
    }

    @GetMapping
    public List<Cliente> getAllClients(){
        return clienteRepository.findAll();
    }

    @DeleteMapping("{id}")
    public String deleteClienteById(@PathVariable Long id){
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if(clienteOptional.isPresent()){
            clienteRepository.deleteById(id);
            return "Cliente de id " + id + " foi deletado com sucesso!";
        }else{
        return "Cliente inexistente!";
        }
    }

    @PutMapping("update/{id}")
    public String updateClienteById(@PathVariable Long id, @RequestParam String novoNome){
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if(clienteOptional.isPresent()){
            Cliente c = clienteOptional.get();
            clienteRepository.save(c);
            return "Cliente de id" + id + " salvo com sucesso!";
        }else{
            return "Cliente inexistente";
        }
    }
}
