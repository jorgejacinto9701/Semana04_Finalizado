package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.logging.Logger;

import dao.ClienteDAO;
import entity.Cliente;
import util.MySqlDBConexion;

public class MySqlClienteDAO implements ClienteDAO{


	private static Logger log = Logger.getLogger(MySqlClienteDAO.class.getName());
	
	public int insertaCliente(Cliente  obj) {
		int salida = -1;
		
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = MySqlDBConexion.getConexion();
			
			String sql = "insert into cliente values(null,?,?,?,?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getNombre());
			pstm.setString(2, obj.getDni());
			pstm.setTimestamp(3, obj.getFechaRegistro());
			pstm.setInt(4, obj.getEstado());
			pstm.setInt(5, obj.getCategoria().getIdCategoria());
			
			log.info(">>>> " + pstm);

			salida = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) pstm.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {}
		}
		
		return salida;
	}

	
}
