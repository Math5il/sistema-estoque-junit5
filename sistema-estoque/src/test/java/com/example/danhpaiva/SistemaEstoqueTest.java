package com.example.danhpaiva;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import java.util.List;
import org.junit.Test;

public class SistemaEstoqueTest {

  private SistemaEstoque sistemaEstoque;

  @Before
  public void setUP(){
    sistemaEstoque = new SistemaEstoque();
  }
  //teste de acionar produto
  @Test
  public void testAdicionarProduto(){
    sistemaEstoque.adicionarProduto("tablet",20);
    assertEquals(20,sistemaEstoque.consultarEstoque("tablet"));
  }
  //teste de adiconar produto sem nome
  @Test(expected = IllegalAccessException.class)
  public void testAdicionarProdutoComNomeVazio(){
    sistemaEstoque.adicionarProduto("", 6);
  }
  //teste de adionar produto sem a quantidade
  @Test(expected = IllegalArgumentException.class)
  public void testAdicionarProdutoComQuantidadeVazio(){
    sistemaEstoque.adicionarProduto("Notebook", 0);
  }
  // teste de remover produto(adiciona o produto deppois o remove e verifica o status)
  @Test
  public void testRemoverProduto(){
    sistemaEstoque.adicionarProduto("teclado", 10);
    sistemaEstoque.removerProduto("teclado", 10);
    assertEquals(2, sistemaEstoque.consultarEstoque("Teclado"));
  }
  // teste de remover o produto quando a quantidade solicita e maior do que a disponivel em estoque
  @Test(expected = IllegalArgumentException.class)
  public void testRemoverProdutoEstoqueIncopleto(){
    sistemaEstoque.adicionarProduto("mouse", 1);
    sistemaEstoque.removerProduto("mouse", 4);
  }
  // teste que trata o caso de querer remover um produto nao cadastrado
  @Test(expected = IllegalArgumentException.class)
  public void testRemoverProdutoInexistente(){
    sistemaEstoque.removerProduto("samsung", 1);
  }
  // teste que trata o caso de consultar um produto nao cadastrado.
  @Test
  public void testConsultarEstoqueProdutoInexistente(){
    assertEquals(0, sistemaEstoque.consultarEstoque("samsung"));
  }
  // teste que trata o caso de consultar produto com sem nome/nulo
  @Test(expected = IllegalArgumentException.class)
  public void testConsultarEstoqueNomeProdutoNulo(){
    sistemaEstoque.consultarEstoque(null);
  }
  // teste que mostra as trasaçoes/saida do produto
  @Test
  public void testObterHistoricoTransacao(){
    sistemaEstoque.adicionarProduto("camera", 4);
    List<String> historico = sistemaEstoque.obterHistoricoTransacoes();
    assertEquals(1, historico.size());
  }
  //teste que verifica produto com a quantidade maior que a solicitada
  @Test
  public void testChecarDisponibilidadeProdutoExistente(){
    sistemaEstoque.adicionarProduto("headset", 10);
    assertFalse(sistemaEstoque.verificarDisponibilidade("headset", 6));
  }
  //teste que verifica no estoque um produto com quantidade menor que a solicitada
  @Test
  public void testChecarDisponibilidadeProdutoIndisponivel(){
    sistemaEstoque.adicionarProduto("headset", 3);
    assertFalse(sistemaEstoque.verificarDisponibilidade("headset", 5));
  }
  @Test
  public void shouldAnswerWithTrue() {
    assertTrue(true);
  }
}
