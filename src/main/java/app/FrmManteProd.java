package app;

import java.awt.BorderLayout;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import models.Categoria;
import models.Producto;
import models.Proveedor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

public class FrmManteProd extends JFrame {

	private JPanel contentPane;
	
	private JTextArea txtSalida;
	private JTextField txtCodigo;
	JComboBox cboCategorias;
	JComboBox cboProveedores;
	private JTextField txtDescripcion;
	private JTextField txtStock;
	private JTextField txtPrecio;
	private JTextField txtEstado;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmManteProd frame = new FrmManteProd();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmManteProd() {
		setTitle("Mantenimiento de Productos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Registrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrar();
			}
		});
		btnNewButton.setBounds(324, 29, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnRegistrar = new JButton("Actualizar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actualizar();
			}
		});
		btnRegistrar.setBounds(324, 60, 89, 23);
		contentPane.add(btnRegistrar);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buscar();
			}
		});
		btnBuscar.setBounds(324, 5, 89, 23);
		contentPane.add(btnBuscar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 171, 414, 143);
		contentPane.add(scrollPane);
		
		txtSalida = new JTextArea();
		scrollPane.setViewportView(txtSalida);
		
		JButton btnListado = new JButton("Listado");
		btnListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listado();
			}
		});
		btnListado.setBounds(177, 322, 89, 23);
		contentPane.add(btnListado);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(122, 11, 86, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JLabel lblCodigo = new JLabel("Id. Producto :");
		lblCodigo.setBounds(10, 14, 102, 14);
		contentPane.add(lblCodigo);
		
		cboCategorias = new JComboBox();
		cboCategorias.setBounds(122, 70, 120, 22);
		contentPane.add(cboCategorias);
		
		JLabel lblCategora = new JLabel("Categor\u00EDa");
		lblCategora.setBounds(10, 74, 102, 14);
		contentPane.add(lblCategora);
		
		JLabel lblNomProducto = new JLabel("Nom. Producto :");
		lblNomProducto.setBounds(10, 45, 102, 14);
		contentPane.add(lblNomProducto);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(122, 42, 144, 20);
		contentPane.add(txtDescripcion);
		
		JLabel lblStock = new JLabel("Stock:");
		lblStock.setBounds(10, 106, 102, 14);
		contentPane.add(lblStock);
		
		txtStock = new JTextField();
		txtStock.setColumns(10);
		txtStock.setBounds(122, 103, 77, 20);
		contentPane.add(txtStock);
		
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(10, 134, 102, 14);
		contentPane.add(lblPrecio);
		
		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(122, 131, 77, 20);
		contentPane.add(txtPrecio);
		
		JLabel lblProveedores = new JLabel("Proveedor:");
		lblProveedores.setBounds(230, 106, 102, 14);
		contentPane.add(lblProveedores);
		
		cboProveedores = new JComboBox();
		cboProveedores.setBounds(300, 104, 120, 22);
		contentPane.add(cboProveedores);
		
		txtEstado = new JTextField();
		txtEstado.setColumns(10);
		txtEstado.setBounds(300, 131, 77, 20);
		contentPane.add(txtEstado);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(230, 134, 102, 14);
		contentPane.add(lblEstado);
		
		llenaCombo();
	}

	protected void buscar() {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		
		Query consulta = em.createQuery("select p from Producto p where :id=id_prod", Producto.class);
		consulta.setParameter("id", txtCodigo.getText());
		
		Producto p = (Producto)consulta.getSingleResult();
		
		txtCodigo.setText(p.getCodigo());
		txtDescripcion.setText(p.getDescripcion());
//		txtPrecio.setText(p.getPrecio());
//		txtCodigo.setText(p.getCodigo());
//		txtCodigo.setText(p.getCodigo());
		
		em.close();
		
	}

	protected void actualizar() {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		
		em.getTransaction().begin();
		
		String cod = txtCodigo.getText();
		String nombre = txtDescripcion.getText();
		int cat = cboCategorias.getSelectedIndex();
		int prov = cboProveedores.getSelectedIndex();
		int stock = Integer.parseInt(txtStock.getText());
		double precio = Double.parseDouble(txtPrecio.getText());
		int estado = Integer.parseInt(txtEstado.getText());
	

		Producto p = new Producto();
		p.setCodigo(cod);
		p.setDescripcion(nombre);
		p.setIdcategoria(cat);
		p.setIdprovedor(prov);
		p.setStock(stock);
		p.setPrecio(precio);
		p.setEstado(estado);
		
		em.merge(p);
	
		em.getTransaction().commit();
		
		em.close();
		
		JOptionPane.showMessageDialog(this, "Producto actualizado");;
		
	}

	@SuppressWarnings("unchecked")
	void llenaCombo() {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		
		List<Categoria> categorias = em.createQuery("select a from Categoria a", Categoria.class).getResultList();
		List<Proveedor> proveedores = em.createQuery("select p from Proveedor p", Proveedor.class).getResultList();
		
		cboCategorias.addItem("Seleccione");
		for(Categoria c : categorias) {
			cboCategorias.addItem(c.getDescripcion());
		}
		
		cboProveedores.addItem("Seleccione");
		for(Proveedor p : proveedores) {
			cboProveedores.addItem(p.getNombre());
		}
		
		em.close();
	}
	
	void listado() {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		
		List<Producto> productos = em.createQuery("select p from Producto p", Producto.class).getResultList();
		
		txtSalida.setText("Listado de Productos" + "\n\n");
		for(Producto p : productos) {
			txtSalida.append("Codigo: " + p.getCodigo() + "\n");
			txtSalida.append("Nombre: " + p.getDescripcion() + "\n");
			txtSalida.append("Precio: " + p.getPrecio() + "\n");
			txtSalida.append("Categoria: " + p.getCategoria().getDescripcion() + "\n");
			txtSalida.append("Proveedor: " + p.getProveedor().getNombre() + "\n");
			txtSalida.append("Stock: " + p.getStock() + "\n");
			txtSalida.append("Estado: " + p.getEstado() + "\n\n");
		}
		
		em.close();
	}
	
	void registrar() {
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		
		em.getTransaction().begin();
		
		String cod = txtCodigo.getText();
		String nombre = txtDescripcion.getText();
		int cat = cboCategorias.getSelectedIndex();
		int prov = cboProveedores.getSelectedIndex();
		int stock = Integer.parseInt(txtStock.getText());
		double precio = Double.parseDouble(txtPrecio.getText());
		int estado = Integer.parseInt(txtEstado.getText());
	

		Producto p = new Producto();
		p.setCodigo(cod);
		p.setDescripcion(nombre);
		p.setIdcategoria(cat);
		p.setIdprovedor(prov);
		p.setStock(stock);
		p.setPrecio(precio);
		p.setEstado(estado);
		
		em.persist(p);
	
		em.getTransaction().commit();
		
		em.close();
		
		JOptionPane.showMessageDialog(this, "Producto registrado");;
	}
}
