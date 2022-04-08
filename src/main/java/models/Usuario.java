package models;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "tb_usuarios")
public class Usuario {

	@Column(name = "cod_usua")
	@Id
	private int codigo; 
	@Column(name = "nom_usua", nullable = false)
	private String nombre;
	@Column(name = "ape_usua")
	private String apellido;
	@Column(name = "usr_usua")
	private String usuario;
	@Column(name = "cla_usua")
	private String clave;
	@Column(name = "fna_usua")
	private String fechaNacimiento;
	@Column(name = "idtipo")
	private int tipo;
	@Column(name = "est_usua")
	private int estado;
	
}
