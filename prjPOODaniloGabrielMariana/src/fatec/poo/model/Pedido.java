package fatec.poo.model;
import java.util.ArrayList;

/**
 *
 * @author Danilo Rodrigues, Gabriel Claudino e Mariana de Brito
 */
public class Pedido {
    private String numero;
    private String dataEmissao;
    private String dataPagto;
    private int formaPagto;
    private boolean situacao;
    private ArrayList<ItemPedido> itensPedido;
    private Cliente cliente;
    private Vendedor vendedor; 
    
    public Pedido(String numero, String dataEmissao) {
        this.numero = numero;
        this.dataEmissao = dataEmissao;
        itensPedido = new ArrayList<ItemPedido>();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setDataPagto(String dataPagto) {
        this.dataPagto = dataPagto;
    }

    public void setFormaPagto(int formaPagto) {
        this.formaPagto = formaPagto;
    }

    public void setSituacao(boolean situacao) {
        this.situacao = situacao;
    }
    
    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public String getNumero() {
        return numero;
    }

    public String getDataEmissao() {
        return dataEmissao;
    }

    public String getDataPagto() {
        return dataPagto;
    }

    public int getFormaPagto() {
        return formaPagto;
    }

    public boolean getSituacao() {
        return situacao;
    }

    public ArrayList<ItemPedido> getItensPedido() {
        return itensPedido;
    }
    
    public void addItemPedido(ItemPedido itemPedido) {
        getItensPedido().add(itemPedido);
        itemPedido.setPedido(this);
        double valItemPedido = itemPedido.getQtdeVendida() * itemPedido.getProduto().getPreco();
        double qtdeEstoqueProduto = itemPedido.getProduto().getQtdeEstoque();
        itemPedido.getProduto().setQtdeEstoque(qtdeEstoqueProduto - itemPedido.getQtdeVendida());
        getCliente().setLimiteDisp(getCliente().getLimiteDisp() - valItemPedido);
    }
    
    public void removerItemPedido(int indicePedido) {
        ItemPedido itemPedido = getItensPedido().get(indicePedido);
        double valItemPedido = itemPedido.getQtdeVendida() * itemPedido.getProduto().getPreco();
        double qtdeEstoqueProduto = itemPedido.getProduto().getQtdeEstoque();
        itemPedido.getProduto().setQtdeEstoque(qtdeEstoqueProduto + itemPedido.getQtdeVendida());
        getCliente().setLimiteDisp(getCliente().getLimiteDisp() + valItemPedido);
        getItensPedido().remove(indicePedido);
    }
}
