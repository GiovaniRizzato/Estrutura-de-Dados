package br.edu.udc.ed.arquivos;

import org.junit.Assert;
import org.junit.Test;

public class DiretorioTest {

	// Caso de teste genérico
	// - Caso feliz -
	Data dataCriacao = new Data(0, 0, 0);

	Diretorio root = new Diretorio(dataCriacao, "root", 100);

	Diretorio files = root.adicionar(dataCriacao, "files", 100);
	Diretorio execltaveis = files.adicionar(dataCriacao, "execultaveis", 50);
	Diretorio exe = execltaveis.adicionar(dataCriacao, ".exe", 20);

	Diretorio test = root.adicionar(dataCriacao, "test", 20);

	@Test
	public void caminho_casoFeliz() {
		Assert.assertEquals(root.toString(), "root");

		Assert.assertEquals(files.toString(), "root/files");
		Assert.assertEquals(execltaveis.toString(), "root/files/execultaveis");
		Assert.assertEquals(exe.toString(), "root/files/execultaveis/.exe");

		Assert.assertEquals(test.toString(), "root/test");
	}

	@Test
	public void tamanho_casoFeliz() {
		Assert.assertEquals(root.getTamanho(), 290);

		Assert.assertEquals(files.getTamanho(), 170);
		Assert.assertEquals(execltaveis.getTamanho(), 70);
		Assert.assertEquals(exe.getTamanho(), 20);

		Assert.assertEquals(test.getTamanho(), 20);
		
		//diminuindo o tamanho em 10
		test.setTamanho(10);
		
		Assert.assertEquals(test.getTamanho(), 10);
		Assert.assertEquals(root.getTamanho(), 280);
	}
}
