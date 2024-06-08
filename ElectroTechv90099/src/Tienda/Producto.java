
package Tienda;

import Datos.ConexionBD;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.sql.ResultSet;


public class Producto {
    private int id;
    private String nombre;
    private String marca;
    private String categoria;
    private int precio;
    private int stock;
    private Date fecha;

    public Producto() {
    }

    public Producto(String nombre, String marca, String categoria, int precio, int stock, Date fecha) {
        this.nombre = nombre;
        this.marca = marca;
        this.categoria = categoria;
        this.precio = precio;
        this.stock = stock;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }    
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public boolean validarProducto(String nombre, int id) {
        try {
            String sql = "SELECT COUNT(*) AS COUNT FROM product WHERE NOMBRE = ? AND ID <> ?";
            ConexionBD.conectar();
            PreparedStatement ps = ConexionBD.conn.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setInt(2, id);

            ResultSet res = ps.executeQuery();

            if (res.next()) {
                int count = res.getInt("COUNT");
                return count > 0;
        }
            ConexionBD.desconectar();
            return false;

        } catch (SQLException e) {
            System.out.println("Error al validar el producto: " + e.getMessage());
            return false;
        }
}
    
    
    
    public void agregarProducto() {
        try {
            String sql = "INSERT INTO product (nombre, marca, categoria, precio, stock, fecha) VALUES (?, ?, ?, ?, ?, ?)";
            ConexionBD.conectar();
            
            PreparedStatement ps = ConexionBD.conn.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, marca);
            ps.setString(3, categoria);
            ps.setInt(4, precio);
            ps.setInt(5, stock);
            ps.setDate(6, new java.sql.Date(fecha.getTime()));
            ps.execute();
            
            System.out.println("Producto almacenado.");
            ConexionBD.desconectar();
        } catch (SQLException e) {
            System.out.println("Error al agregar producto");
        }
    }
    
    public void buscarID(){
        
        try{
            
            ConexionBD.buscarID = false;
            String sql = "SELECT * FROM product WHERE id = ?";
            ConexionBD.conectar();
            PreparedStatement ps = ConexionBD.conn.prepareStatement(sql);
            ps.setInt(1, id);
            
            ResultSet res = ps.executeQuery();
            if(res.next()){
                ConexionBD.buscarID = true;
                id = res.getInt(1);
                nombre = res.getString(2);
                marca = res.getString(3);
                categoria = res.getString(4);
                precio = res.getInt(5);
                stock = res.getInt(6);
                fecha = res.getDate(7);

            }
            ConexionBD.desconectar();
            
        }catch (Exception e){
            System.out.println("Error al buscar id.");
        }
        
    }
    
    public void buscarNombre(){
        
        try{
            
            ConexionBD.buscarNombre = false;
            String sql = "SELECT * FROM product WHERE nombre = ?";
            ConexionBD.conectar();
            PreparedStatement ps = ConexionBD.conn.prepareStatement(sql);
            ps.setString(1, nombre);
            
            ResultSet res = ps.executeQuery();
            if(res.next()){
                ConexionBD.buscarNombre = true;
                id = res.getInt(1);
                nombre = res.getString(2);
                marca = res.getString(3);
                categoria = res.getString(4);
                precio = res.getInt(5);
                stock = res.getInt(6);
                fecha = res.getDate(7);
   
            }
            ConexionBD.desconectar();
            
        }catch (Exception e){
            System.out.println("Error al buscar nombre del producto.");
        }
        
    }
    
    public void actualizarProducto() {
    try {
        String sql = "UPDATE product SET nombre = ?, marca = ?, categoria = ?, precio = ?, stock = ?, fecha = ? WHERE id = ?";
        ConexionBD.conectar();
        PreparedStatement ps = ConexionBD.conn.prepareStatement(sql);

        ps.setString(1, nombre);
        ps.setString(2, marca);
        ps.setString(3, categoria);
        ps.setInt(4, precio);
        ps.setInt(5, stock);
        ps.setDate(6, new java.sql.Date(fecha.getTime()));
        ps.setInt(7, id);

        // Ejecutar la actualización
        ps.execute();
        System.out.println("Producto actualizado.");
        ConexionBD.desconectar();
    } catch (SQLException e) {
        System.out.println("Error al actualizar producto: " + e.getMessage());
    }
    }
    
    public void eliminar(){
        
       try{
            String sql = "DELETE FROM PRODUCT WHERE id = '"+id+"' ";
            ConexionBD.conectar();
            ConexionBD.sentencia = ConexionBD.conn.prepareStatement(sql);
            ConexionBD.sentencia.execute(sql);
            System.out.println("Producto Eliminado");
            ConexionBD.desconectar();
        }catch(Exception e){
            System.out.println("Error al eliminar el producto");
        }
        
    }

}


    
    
    
    

