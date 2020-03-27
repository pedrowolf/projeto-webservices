package com.wolf.bean;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wolf.domain.Cidade;
import com.wolf.domain.Cliente;
import com.wolf.model.ClienteModel;

@ManagedBean(name = "MBIndex")
@ViewScoped
public class IndexBean {

	private List<Cliente> clientes;

	private Cliente cliente;

	private List<Cidade> cidades;

	@PostConstruct
	public void iniciar() {
		atualizaLista();
		carregarCidades();
	}

	public void atualizaLista() {
		clientes = ClienteModel.clientes;
	}

	public void novoCliente() {
		cliente = new Cliente();
	}

	public void saveCliente() {
		ClienteModel.addCliente(cliente);
		atualizaLista();
	}

	public void carregarCidades() {
		try {
			URL url = new URL("http://maventest.herokuapp.com/mavenTest-1.0-SNAPSHOT/webresources/cidade");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setDoOutput(true);

			int status = con.getResponseCode();

			if (status == 200) {
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer content = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					content.append(inputLine);
				}
				in.close();
				String json = content.toString();

				ObjectMapper obMapper = new ObjectMapper();
				cidades = obMapper.readValue(json, new TypeReference<List<Cidade>>() {
				});

				con.disconnect();
			} else {
				cidades = new ArrayList<>();
			}
		} catch (Exception e) {
			cidades = new ArrayList<>();
		}
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}
}
