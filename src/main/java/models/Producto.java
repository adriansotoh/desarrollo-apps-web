package models;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_productos")
public class Producto {

	@Column(name = "id_prod")
	@Id
	private String codigo;
	@Column(name = "des_prod")
	private String descripcion;
	@Column(name = "stk_prod")
	private int stock;
	@Column(name = "pre_prod")
	private double precio;
	@ManyToOne
	@JoinColumn(name = "idcategoria", insertable = false, updatable = false)
	private Categoria categoria;
	
	private int idcategoria;
	@Column(name = "est_prod")
	private int estado;
	
	@ManyToOne
	@JoinColumn(name = "idprovedor", insertable = false, updatable = false)
	private Proveedor proveedor;
	
	private int idprovedor;
	
	
}
