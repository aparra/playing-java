package br.com.lambda;

import static ch.lambdaj.Lambda.closure;
import static ch.lambdaj.Lambda.forEach;
import static ch.lambdaj.Lambda.of;
import static ch.lambdaj.Lambda.var;
import static java.util.Arrays.asList;

import java.util.List;

import ch.lambdaj.function.closure.Closure;

public class Interator {
	
	public static void main(String[] args) {
		List<Pessoa> familia = asList(new Pessoa("Joao"), new Pessoa("Maria"));

		forEach(familia).setSobreNome("Skywalker");
		
		Closure println = closure(); {
			of(System.out).println(var(Pessoa.class).getNomeCompleto());
		}
		
		println.each(familia);
	}
	
	public static class Pessoa {

		private String nome;
		private String sobreNome;
		
		public Pessoa(String nome) {
			this.nome = nome;
		}

		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getSobreNome() {
			return sobreNome;
		}
		public void setSobreNome(String sobreNome) {
			this.sobreNome = sobreNome;
		}
		
		public String getNomeCompleto() {
			return String.format("%s %s", nome, sobreNome);
		}
	}
}
