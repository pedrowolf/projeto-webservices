package com.wolf.resources;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wolf.domain.Cidade;
import com.wolf.domain.Cliente;
import com.wolf.model.ClienteModel;

@Path("cliente")
public class ClienteResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Cliente> findAll() {
		return ClienteModel.clientes;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{codigo}")
	public Cliente getByCode(@PathParam("codigo") int codigo) {
		Cliente tmp = ClienteModel.getByCodigo(codigo);
		return tmp;
	}
	
	@POST
	@Path("/{codigo}/{nome}/{codCidade}/{nomeCidade}")
	public void save(@PathParam("codigo") int codigo, @PathParam("nome") String nome, @PathParam("codCidade") int codCidade, @PathParam("nomeCidade") String nomeCidade) {
		Cidade cidade = new Cidade();
		cidade.setCodigo(codCidade);
		cidade.setNome(nomeCidade);
		Cliente cli = new Cliente();
		cli.setCidade(cidade);
		cli.setCodigo(codigo);
		cli.setNome(nome);
		ClienteModel.addCliente(cli);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void save(String json) {
		ObjectMapper obMapper = new ObjectMapper();
		try {
			Cliente cli = obMapper.readValue(json, Cliente.class);
			ClienteModel.addCliente(cli);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void update(String json) {
		ObjectMapper obMapper = new ObjectMapper();
		try {
			Cliente cli = obMapper.readValue(json, Cliente.class);
			ClienteModel.updateCliente(cli);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@DELETE
	@Path("/{codigo}")
	public void deleteByCode(@PathParam("codigo") int codigo) {
		Cliente c = new Cliente();
		c.setCodigo(codigo);
		ClienteModel.removeCliente(c);
	}
}
